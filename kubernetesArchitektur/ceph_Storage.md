#Ceph Storage
Alle bestehenden bekannten Nodes löschen:
```
$ gcloud compute instances delete s8-node1 s8-node2 s8-no
de3
```

Neue instanz erstellen (kleinste möglichkeit):
```
$ gcloud compute instances create s1-node1 --image-family
ubuntu-1604-lts --image-project ubuntu-os-cloud --machin
e-type f1-micro --zone europe-west1-d
$ gcloud compute ssh s1-node1
```
