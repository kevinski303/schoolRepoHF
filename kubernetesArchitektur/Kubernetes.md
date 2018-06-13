# Kubernetes

## Setup:
>Hoster: google cloud <br> 
 Jumphost: 35.205.224.103 <br>
 User: student8 <br>
 Node1: s8-node1 <br>
 Node2: s8-node2 <br>
 Node3: s8-node3 <br>
 Port: 30008<br>

---

## Einrichten der Infrastruktur
### Jumphost
SSH Verbndung zum Jumphost aufbauen:
```
ssh student8@35.205.225.103
```

Starten einer VM (Muss für alle Nodes ausgeführt werden):
```
gcloud compute instances create s8-node1 --image-family ubuntu-1604-lts --image-project ubuntu-os-cloud --machine-type n1-standard-2 --zone europe-west1-d
#instances create <node hostname>
```

Nice to know (bsp. s8-node1):
```
# Stoppen der VM s8-node1.
$ gcloud compute instances stop --zone europe-west1-d s8-node1
# Starten der VM s8-node1.
$ gcloud compute instances start --zone europe-west1-d s8-node1
# Löschen der VM s8-node1.
$ gcloud compute instances delete --zone europe-west1-d s8-node1
```

SSH Key einrichten:
```
ssh-keygen
```
> Inhalt des public keys zwischnspeichern
---
### Cephd Benutzer auf Nodes:
> Nachfolgende schritte für jede VM ausführen

SSH Verbindung zu Node aufbauen:
```
gcloud compute ssh <nodename>
```
Updates ausführen (-y yes to all):
```
sudo apt-get update && sudo apt-get upgrade -y && sudo apt-get dist-upgrade -y
```
Erstelle User cphd (create home, pfad zu home, shelllogin) & setze Passwort:
```
sudo useradd -d /home/cephd -m cephd -s /bin/bash && sudo passwd cephd
```

```
echo "cephd ALL = (root) NOPASSWD:ALL" | sudo tee /etc/sudoers.d/cephd
```

```
sudo chmod 0440 /etc/sudoers.d/cephd
```

Auf den cephd Benutzer wechseln und public key hinterlegen:
```
#wechsle benutzer
$ sudo su - cephd

#erstelle ordner .ssh
$ mkdir .ssh 

#editiere/erstelle datei
$ vim .ssh/authorized_keys

$ exit

$ exit
```

---

### SSH Config (Jumphost)
Auf dem Jumphost wird nun das ssh config file hinterlegt damit für die Verbindung zu den Nodes immer der cephd user verwendet wird.

Anlegen der configdatei:
```
nano .ssh/config
```
Inhalt der configdatei:
```
Host s8-node1 s8-node2 s8-node3 User cephd
IdentityFile ~/.ssh/ceph 
```

Testen ob befehle ohne Anmeldung auf nodes ausgeführt werden:
>gibt Wochentag, Datum, Zeit, Zeitzone & Jahr pro Node aus
```
ssh s8-node1 date && ssh s8-node2 date && ssh s8-node3 date
```
---

## Installation & Config ceph:
Auf dem Jumphost wird nun ein Verzeichnis erstellt, welches alle ceph Daten enthaltet:

Erstelle Verzeichnis und wechsle in verzeichnis:
```
mkdir ceph && cd ceph
```
Installation ceph:
```
$ ceph-deploy install s8-node1 s8-node2 s8-node3
$ ceph-deploy new s8-node1 s8-node2 s8-node3
```
Editiere ceph.conf:
```
nano ceph.conf
```
Inhalt in ceph.conf hinzufügen:
```
osd max object name len = 256
osd max object namespace len = 64
```

```
ceph-deploy mon create-initial
```

Erstelle osd Verzeichnis auf allen Nodes:
```
$ ssh s8-node1 sudo mkdir /var/local/osd0
$ ssh s8-node2 sudo mkdir /var/local/osd1
$ ssh s8-node3 sudo mkdir /var/local/osd2
```

Osd zur Verwendung im Cluster vorbereiten:
```
ceph-deploy osd prepare s8-node1:/var/local/osd0 s8-node2:/var/local/osd1 s8-node3:/var/local/osd2
```

Fix for Permission Issue:
```
$ ssh s8-node1 sudo chmod -R 777 /var/local/osd0
$ ssh s8-node2 sudo chmod -R 777 /var/local/osd1
$ ssh s8-node3 sudo chmod -R 777 /var/local/osd2
```

OSD Disks aktivieren:
```
ceph-deploy osd activate s8-node1:/var/local/osd0 s8-node2:/var/local/osd1 s8-node3:/var/local/osd2
```

Installiere Kluster Keys:
```
ceph-deploy admin s8-node1 s8-node2 s8-node3
```
Autostart für ceph osds
```
$ ssh s8-node3 sudo systemctl enable ceph-osd@2
$ ssh s8-node2 sudo systemctl enable ceph-osd@1
$ ssh s8-node1 sudo systemctl enable ceph-osd@0
```

Test für ceph Installation:
```
ssh s8-node1 sudo ceph -w
```

Test Output:
```
student8@jumphost:~/ceph$ ssh s8-node1 sudo ceph -w
    cluster 7e7f94fe-630e-43eb-99b0-d092dbb74ed7
     health HEALTH_OK
     monmap e2: 3 mons at {s8-node1=10.132.0.18:6789/0,s8-node2=10.132.0.19:6789/0,s8-node3=10.132.0.20:6789/0}
            election epoch 10, quorum 0,1,2 s8-node1,s8-node2,s8-node3
     osdmap e15: 3 osds: 3 up, 3 in
            flags sortbitwise,require_jewel_osds
      pgmap v156: 64 pgs, 1 pools, 0 bytes data, 0 objects
            19939 MB used, 9597 MB / 29585 MB avail
                  64 active+clean

2018-02-14 10:20:02.619257 mon.0 [INF] pgmap v156: 64 pgs: 64 active+clean; 0 bytes data, 19939 MB used, 9597 MB / 29585 MB avail
2018-02-14 10:20:04.644438 mon.0 [INF] pgmap v157: 64 pgs: 64 active+clean; 0 bytes data, 19939 MB used, 9597 MB / 29585 MB avail
2018-02-14 10:20:05.685133 mon.0 [INF] pgmap v158: 64 pgs: 64 active+clean; 0 bytes data, 19940 MB used, 9597 MB / 29585 MB avail
2018-02-14 10:20:07.709089 mon.0 [INF] pgmap v159: 64 pgs: 64 active+clean; 0 bytes data, 19940 MB used, 9597 MB / 29585 MB avail
2018-02-14 10:20:09.731476 mon.0 [INF] pgmap v160: 64 pgs: 64 active+clean; 0 bytes data, 19940 MB used, 9597 MB / 29585 MB avail
2018-02-14 10:20:10.755372 mon.0 [INF] pgmap v161: 64 pgs: 64 active+clean; 0 bytes data, 19940 MB used, 9596 MB / 29585 MB avail
2018-02-14 10:20:12.784325 mon.0 [INF] pgmap v162: 64 pgs: 64 active+clean; 0 bytes data, 19940 MB used, 9596 MB / 29585 MB avail
2018-02-14 10:20:14.807712 mon.0 [INF] pgmap v163: 64 pgs: 64 active+clean; 0 bytes data, 19940 MB used, 9596 MB / 29585 MB avail
2018-02-14 10:20:15.850361 mon.0 [INF] pgmap v164: 64 pgs: 64 active+clean; 0 bytes data, 19940 MB used, 9596 MB / 29585 MB avail
2018-02-14 10:20:17.876755 mon.0 [INF] pgmap v165: 64 pgs: 64 active+clean; 0 bytes data, 19940 MB used, 9596 MB / 29585 MB avail
2018-02-14 10:20:19.899091 mon.0 [INF] pgmap v166: 64 pgs: 64 active+clean; 0 bytes data, 19941 MB used, 9596 MB / 29585 MB avail
2018-02-14 10:20:20.923983 mon.0 [INF] pgmap v167: 64 pgs: 64 active+clean; 0 bytes data, 19941 MB used, 9596 MB / 29585 MB avail
2018-02-14 10:20:21.948740 mon.0 [INF] pgmap v168: 64 pgs: 64 active+clean; 0 bytes data, 19941 MB used, 9596 MB / 29585 MB avail

```
---

## Installation Kubernetes
Auf dem Jumphost Verzeichnis anlegen und in Verzeichnis wechseln: 
```
mkdir k8s && cd k8s
```
### Software Installation Nodes
> Folgende Befehle auf allen Nodes(als root) ausführen (ssh s8-node#):
```
$ sudo -i #wechsle auf root
$ curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | apt-key add -
$ cat <<EOF > /etc/apt/sources.list.d/kubernetes.list
deb http://apt.kubernetes.io/ kubernetes-xenial main
EOF
$ apt-get update
$ apt-get install -y docker.io
$ apt-get install -y kubelet kubeadm kubectl kubernetes-cni
```

### Cluster Initialisieren
Zuerst wird der erste Node initialisiert später werden die anderen Nodes via ``kubeadmin init`` hinzugefügt.
> Folgende Befehle als root(sudo -i) nur auf dem ersten Node ausfüren:

```
kubeadm init --pod-network-cidr 10.244.0.0/16
```
>Output:
```
root@s8-node1:~# kubeadm init --pod-network-cidr 10.244.0.0/16
[init] Using Kubernetes version: v1.9.3
[init] Using Authorization modes: [Node RBAC]
[preflight] Running pre-flight checks.
        [WARNING FileExisting-crictl]: crictl not found in system path
[certificates] Generated ca certificate and key.
[certificates] Generated apiserver certificate and key.
[certificates] apiserver serving cert is signed for DNS names [s8-node1 kubernetes kubernetes.default kubernetes.default.svc kubernetes.default.svc.cluster.local] and IPs [10.96.0.1 10.132.0.18]
[certificates] Generated apiserver-kubelet-client certificate and key.
[certificates] Generated sa key and public key.
[certificates] Generated front-proxy-ca certificate and key.
[certificates] Generated front-proxy-client certificate and key.
[certificates] Valid certificates and keys now exist in "/etc/kubernetes/pki"
[kubeconfig] Wrote KubeConfig file to disk: "admin.conf"
[kubeconfig] Wrote KubeConfig file to disk: "kubelet.conf"
[kubeconfig] Wrote KubeConfig file to disk: "controller-manager.conf"
[kubeconfig] Wrote KubeConfig file to disk: "scheduler.conf"
[controlplane] Wrote Static Pod manifest for component kube-apiserver to "/etc/kubernetes/manifests/kube-apiserver.yaml"
[controlplane] Wrote Static Pod manifest for component kube-controller-manager to "/etc/kubernetes/manifests/kube-controller-manager.yaml"
[controlplane] Wrote Static Pod manifest for component kube-scheduler to "/etc/kubernetes/manifests/kube-scheduler.yaml"
[etcd] Wrote Static Pod manifest for a local etcd instance to "/etc/kubernetes/manifests/etcd.yaml"
[init] Waiting for the kubelet to boot up the control plane as Static Pods from directory "/etc/kubernetes/manifests".
[init] This might take a minute or longer if the control plane images have to be pulled.
[apiclient] All control plane components are healthy after 41.501692 seconds
[uploadconfig] Storing the configuration used in ConfigMap "kubeadm-config" in the "kube-system" Namespace
[markmaster] Will mark node s8-node1 as master by adding a label and a taint
[markmaster] Master s8-node1 tainted and labelled with key/value: node-role.kubernetes.io/master=""
[bootstraptoken] Using token: 3fc7a0.eedc0ebacb1f3b0b
[bootstraptoken] Configured RBAC rules to allow Node Bootstrap tokens to post CSRs in order for nodes to get long term certificate credentials
[bootstraptoken] Configured RBAC rules to allow the csrapprover controller automatically approve CSRs from a Node Bootstrap Token
[bootstraptoken] Configured RBAC rules to allow certificate rotation for all node client certificates in the cluster
[bootstraptoken] Creating the "cluster-info" ConfigMap in the "kube-public" namespace
[addons] Applied essential addon: kube-dns
[addons] Applied essential addon: kube-proxy

Your Kubernetes master has initialized successfully!

To start using your cluster, you need to run the following as a regular user:

  mkdir -p $HOME/.kube
  sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
  sudo chown $(id -u):$(id -g) $HOME/.kube/config

You should now deploy a pod network to the cluster.
Run "kubectl apply -f [podnetwork].yaml" with one of the options listed at:
  https://kubernetes.io/docs/concepts/cluster-administration/addons/

You can now join any number of machines by running the following on each node
as root:

  kubeadm join --token 3fc7a0.eedc0ebacb1f3b0b 10.132.0.18:6443 --discovery-token-ca-cert-hash sha256:88aff2befbd9034f41a88ac1514aa58f244bf91b68df002f9fd574f23c2823a4
```
>NOTE:   kubeadm join --token 3fc7a0.eedc0ebacb1f3b0b 10.132.0.18:6443 --discovery-token-ca-cert-hash sha256:88aff2befbd9034f41a88ac1514aa58f244bf91b68df002f9fd574f23c2823a4

```
cephd@s8-node1:~$ mkdir -p $HOME/.kube
cephd@s8-node1:~$ sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
cephd@s8-node1:~$ sudo chown $(id -u):$(id -g) $HOME/.kube/config
cephd@s8-node1:~$ kubectl taint node --all node-role.kubernetes.io/master:NoSchedule-
node "s8-node1" untainted
```

Flannel:
```

cephd@s8-node1:~$ wget https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml
--2018-02-14 11:10:29--  https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml
Resolving raw.githubusercontent.com (raw.githubusercontent.com)... 151.101.0.133, 151.101.64.133, 151.101.128.133, ...
Connecting to raw.githubusercontent.com (raw.githubusercontent.com)|151.101.0.133|:443... connected.
HTTP request sent, awaiting response... 200 OK
Length: 3217 (3.1K) [text/plain]
Saving to: ‘kube-flannel.yml’

kube-flannel.yml                                            100%[========================================================================================================================================>]   3.14K  --.-KB/s    in 0s

2018-02-14 11:10:29 (16.1 MB/s) - ‘kube-flannel.yml’ saved [3217/3217]

cephd@s8-node1:~$ kubectl apply -f kube-flannel.yml --namespace=kube-system
clusterrole "flannel" created
clusterrolebinding "flannel" created
serviceaccount "flannel" created
configmap "kube-flannel-cfg" created
daemonset "kube-flannel-ds" created
cephd@s8-node1:~$

```

Test ob alle Pods laufen:
```
cephd@s8-node1:~$ kubectl get pods --all-namespaces
NAMESPACE     NAME                               READY     STATUS    RESTARTS   AGE
kube-system   etcd-s8-node1                      1/1       Running   0          9m
kube-system   kube-apiserver-s8-node1            1/1       Running   0          9m
kube-system   kube-controller-manager-s8-node1   1/1       Running   0          9m
kube-system   kube-dns-6f4fd4bdf-bntct           3/3       Running   0          10m
kube-system   kube-flannel-ds-ckpb5              1/1       Running   0          1m
kube-system   kube-proxy-zbgx5                   1/1       Running   0          10m
kube-system   kube-scheduler-s8-node1            1/1       Running   0          9m
```

### Die anderen zwei Nodes in den Cluster joinen:
Node2:
```
cephd@s8-node2:~$ sudo -i
root@s8-node2:~# kubeadm join --token 3fc7a0.eedc0ebacb1f3b0b 10.132.0.18:6443 --discovery-token-ca-cert-hash sha256:88aff2befbd9034f41a88ac1514aa58f244bf91b68df002f9fd574f23c2823a4
[preflight] Running pre-flight checks.
        [WARNING FileExisting-crictl]: crictl not found in system path
[discovery] Trying to connect to API Server "10.132.0.18:6443"
[discovery] Created cluster-info discovery client, requesting info from "https://10.132.0.18:6443"
[discovery] Requesting info from "https://10.132.0.18:6443" again to validate TLS against the pinned public key
[discovery] Cluster info signature and contents are valid and TLS certificate validates against pinned roots, will use API Server "10.132.0.18:6443"
[discovery] Successfully established connection with API Server "10.132.0.18:6443"

This node has joined the cluster:
* Certificate signing request was sent to master and a response
  was received.
* The Kubelet was informed of the new secure connection details.

Run 'kubectl get nodes' on the master to see this node join the cluster.
root@s8-node2:~#
```

Node3:
```
cephd@s8-node3:~$ sudo -i
root@s8-node3:~# kubeadm join --token 3fc7a0.eedc0ebacb1f3b0b 10.132.0.18:6443 --discovery-token-ca-cert-hash sha256:88aff2befbd9034f41a88ac1514aa58f244bf91b68df002f9fd574f23c2823a4
[preflight] Running pre-flight checks.
        [WARNING FileExisting-crictl]: crictl not found in system path
[discovery] Trying to connect to API Server "10.132.0.18:6443"
[discovery] Created cluster-info discovery client, requesting info from "https://10.132.0.18:6443"
[discovery] Requesting info from "https://10.132.0.18:6443" again to validate TLS against the pinned public key
[discovery] Cluster info signature and contents are valid and TLS certificate validates against pinned roots, will use API Server "10.132.0.18:6443"
[discovery] Successfully established connection with API Server "10.132.0.18:6443"

This node has joined the cluster:
* Certificate signing request was sent to master and a response
  was received.
* The Kubelet was informed of the new secure connection details.

Run 'kubectl get nodes' on the master to see this node join the cluster.
```

Test:
```
cephd@s8-node1:~$ kubectl get nodes
NAME       STATUS    ROLES     AGE       VERSION
s8-node1   Ready     master    21m       v1.9.3
s8-node2   Ready     <none>    3m        v1.9.3
s8-node3   Ready     <none>    1m        v1.9.3
cephd@s8-node1:~$
```
---

## Zugriff auf ceph
Workaraound für Bug im Kubernetes Release.
> Es fehlt im Cluster Manager Container das Programm um ceph Disks zu erstellen.
Auf dem Ersten Node wird als Root ein Ersatzcontainer verwendet:

```
sed -i "s|gcr.io/google_containers/kube-controller-manager-amd64:v1.5.2|docker.io/icedaq/kcm:1.5.2|g"
/etc/kubernetes/manifests/kube-controller-manager.yaml
```

Autostart ceph:
```
$ sudo systemctl start ceph-mon
$ sudo systemctl start ceph-osd@0
```

### Ceph admin Key
>base64

Gibt den Admin key als base64 aus, da dieser in base64 hinterlegt werden muss:
```
grep key /etc/ceph/ceph.client.admin.keyring |awk '{printf "%s", $NF}'|base64
```

Auf dem Jumphost das "secret.yaml" file anlegen:
```
vim secret.yaml
```
mit folgendem inhalt:
```yaml
apiVersion: v1
kind: Secret
metadata:
name: ceph-secret
type: "kubernetes.io/rbd"
data:
#Admin Key aus dem grep Befehl oben
key: QVFEVzJINWFIVVUvS3hBQXRhTXBZeHdDNk9WS1JBUzF3SzMvNHc9PQ==
```

Auf dem jumphost storage.yaml mit folgendem inhalt anlegen:
```yaml
piVersion: storage.k8s.io/v1beta1
kind: StorageClass
metadata:
name: ceph
provisioner: kubernetes.io/rbd
parameters:
monitors: s8-node1,s8-node2,s8-node3
adminId: admin
adminSecretName: ceph-secret
adminSecretNamespace: kube-system
pool: rbd
userId: admin
userSecretName: ceph-secret
~
```

Erstellen der Secrets und StorageClass:
```
$ kc create -f secret.yaml
$ kc create -f secret.yaml --namespace=kube-system
$ kc create -f storage.yaml
```

pvc.yaml
```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
name: pvc-guestbook
annotations:
volume.beta.kubernetes.io/storage-class: ceph
spec:
accessModes:
- ReadWriteMany
resources:
requests:
storage: 1Gi
```

pvc.yaml Objekt erstellen und prüfen:
```
$ kc create -f pvc.yaml
$ kc get pvc
```

---

## Guestbook
>Demo Applikation
```
$ cd
$ cd guestbook-go
$ vim guestbook-service.json
```
Applikation erstellen:
```
$ kc create -f redis-master-controller.json
$ kc create -f redis-master-service.json
$ kc create -f redis-slave-controller.json
$ kc create -f redis-slave-service.json
$ kc create -f guestbook-controller.json
$ kc create -f guestbook-service.json
```
> Via externe IP des Master Nodes sollte die Applikation vom Internet aus nun erreichbar sein.

Änderungen am redis-master-controller.json die vozunehmen sind:
```json
{
    "kind": "ReplicationController",
    "apiVersion": "v1",
    "metadata": {
        "name": "redis-master",
        "labels": {
            "app": "redis",
            "role": "master"
        }
    },
    "spec": {
        "replicas": 1,
        "selector": {
            "app": "redis",
            "role": "master"
        },
        "template": {
            "metadata": {
                "labels": {
                    "app": "redis",
                    "role": "master"
                }
            },
            "spec": {
                "volumes": [
                    {
                        "name": "guestbook",
                        "persistentVolumeClaim": {
                            "claimName": "pvc-guestbook"
                        }
                    }
                ],
                "containers": [
                    {
                        "name": "redis-master",
                        "image": "redis:2.8.23",
                        "ports": [
                            {
                                "name": "redis-server",
                                "containerPort": 6379
                            }
                        ],
                        "volumeMounts": [
                            {
                                "mountPath": "/data",
                                "name": "guestbook"
                            }
                        ],
                        "lifecycle": {
                            "postStart": {
                                "exec": {
                                    "command": [
                                        "/usr/local/bin/redis-cli",
                                        "config",
                                        "set",
                                        "SAVE",
                                        "10 1"
                                    ]
                                }
                            }
                        }
                    }
                ]
            }
        }
    }
}
```

Befor das Kubernetes Update gemacht wird, muss noch der Master Pod gelöscht werden.<br>
Dazu können folgende Befehle benötigt werden:
```
$ kc update -f redis-master-controller.json
```
```
$ kc delete pod <name des redis master>
```
```
$ kc delete pods <redis master> <redis slave> <redis slave>
```
--- 
###Reflexion
- Was haben sie gelernt?<br>
Das erste mal Kubernetes eingesetzt.<br>

Wo hatten sie am meisten Schwierigkeiten?<br>
- Die Anleitung musste überarbeitet werden. Die ursprünglichen Befehle zum Ceph haben leider nicht funktioniert.<br>
- Anscheinend wurde das User Passwort teils von der Excel Tabelle falsch kopiert. -_-<br>
- Da ich zuest den Nano editior verwendet habe, gab es beim Kopieren des Public keys einige klassische copy paste fehler.<br>

Was denken sie, sind die Vorteile von einer solchen Infrastruktur gegenüber traditioneller Infrastruktur?<br>
- Es ist Kostensparend, Skalierbar und einfach verwaltbar. Und bietet zugleich eine gute Grundlage um Cloud basierte Applikationen bereitszustellen.<br>

<br>
---

#Ceph Storage

### Block Volume
Erstellen eines Block Volume mit 1GB und testen:
```
$ sudo rbd create blocktest --size 1G
$ sudo rbd ls blocktest
```

Ceph Features deaktivieren die auf Ubuntu 4.4 nicht unterstützt sind und Volume mit Node verbinden:
```
$ sudo rbd feature disable blocktest deep-flatten fast-diff object-map exclusive-lock
$ sudo rbd map blocktest /dev/rbd0
```

Block device befindet sich nun auf '/dev/rbd0':

Als nächstes das Block device Formatieren und Mounten:
```
$ sudo mkfs.btrfs /dev/rbd0
$ sudo mkdir /mnt/blocktest
$ sudo mount /dev/rbd0 /mnt/blocktest/
$ sudo touch /mnt/blocktest/test.txt
$ sudo ls /mnt/blocktest/test.txt /mnt/blocktest/test.txt
```

### File Storage
>Ähnlich wie Fileshare (NFS, SMB, etc)
Vorhandener cephFS verbinden mit Adminkey:
```
$ sudo mount -t ceph <ip>:/ /mnt/filetest/ -o name=admin,secret=<secretkey>
```
Test:
```
$ sudo touch /mnt/filetest/test.txt
$ sudo ls /mnt/filetest/test.txt /mnt/filetest/test.txt
```

### Object Storage
Object storage via AWS S3:

Zuerst muss  via API ein User angelegt werden:
```
$ sudo radosgw-admin user create --uid=admin --display-na
me="admin"

"user": "admin",
"access_key": "<access key>",
"secret_key": "<secret key>"
```

Um auf das Objekt Storage zuzugreifen wird s3cfg verwendet:
```
[default]
access_key = <access key>
secret_key = <secret key>
bucket_location = US
cloudfront_host = cloudfront.amazonaws.com
default_mime_type = binary/octet-stream
delete_removed = False
dry_run = False
enable_multipart = True
encoding = ANSI_X3.4-1968
encrypt = False
follow_symlinks = False
force = False
get_continue = False
gpg_command = /usr/bin/gpg
gpg_decrypt = %(gpg_command)s -d --verbose --no-use-agent --batch --yes --passphrase-fd %(passphrase_fd)s -o %(output_file)s %(input_file)s
gpg_encrypt = %(gpg_command)s -c --verbose --no-use-agent --batch --yes --passphrase-fd %(passphrase_fd)s -o %(output_file)s %(input_file)s
gpg_passphrase =
guess_mime_type = True
host_base = localhost
host_bucket = localhost
human_readable_sizes = False
invalidate_on_cf = False
list_md5 = False
log_target_prefix =
mime_type =
multipart_chunk_size_mb = 15
preserve_attrs = True
progress_meter = True
proxy_host =
proxy_port = 0
recursive = False
recv_chunk = 4096
reduced_redundancy = False
send_chunk = 4096
simpledb_host = sdb.amazonaws.com
skip_existing = False
socket_timeout = 300
urlencoding_mode = normal
use_https = False
verbosity = WARNING
website_endpoint = http://%(bucket)s.s3-website-%(location)s.amazonaws.com/
website_error =
website_index = index.html
```

Bucket erstellen und File hochladen:
```
$ s3cmd mb s3://objecttest -c s3cfg Bucket 's3://objecttest/' created
$ echo 'Hello S3' > test.txt
$ s3cmd put test.txt s3://objecttest -c s3cfg
upload: 'test.txt' -> 's3://objecttest/test.txt' [1 of 1
]
9 of 9 100% in 1s 6.57 B/s done
$ rm test.txt
$ s3cmd get s3://objecttest/test.txt test2.txt -c s3cfg
download: 's3://objecttest/test.txt' -> 'test2.txt' [1 of 1] 9 of 9 100% in 0s 296.46 B/s done
$ cat test2.txt
Hello S3
```

Zugriff auf die Datei via Bash script:
```sh
#!/bin/bash
# Variables
s3Key=<admin key>
s3Secret=<secret key>
host=10.142.0.8
file=test.txt
bucket=objecttest
resource="/${bucket}/${file}"
contentType="text/plain"
dateValue=`date -R`
stringToSign="GET\n\n${contentType}\n${dateValue}\n${reso
urce}"
signature=`echo -en ${stringToSign} | openssl sha1 -hmac
${s3Secret} -binary | base64`
curl -v -H "Host: ${host}" \
-H "Date: ${dateValue}" \
-H "Content-Type: ${contentType}" \
-H "Authorization: AWS ${s3Key}:${signature}" \
http://${host}${resource}
```