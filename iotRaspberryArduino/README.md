# Raspberry Pi & HASS

## Raspberry Prerequisites
- SSH: https://www.raspberrypi.org/documentation/remote-access/ssh/

## HASS Setup
* Updates:<br>
``$ sudo apt-get update``<br>
``$ sudo apt-get upgrade -y``<br>

* Python 3 <br>
``$ sudo apt-get install python3 python3-venv python3-pip``<br>

* User für HASS erstellen<br>
``$ sudo useradd -rm homeassistant``<br>

* Virtuelles Verzeichnis erstellen und User berechtigen<br>
``$ cd /srv``<br>
``$ sudo mkdir homeassistant``<br>
``$ sudo chown homeassistant:homeassistant homeassistant``<br>

* Installation Ausführen<br>
``(homeassistant) homeassistant@raspberrypi:/srv/homeassistant $ pip3 install homeassistant``<br>

* HASS Starten<br>
``(homeassistant) $ hass``<br>
Home assistant ist nun via http://[IP]:8123 erreichbar.

## Samba Setup
* Install Samba<br>
``$ sudo apt-get install samba``

* Konfiguration anpassen<br>
``$ sudo nano /etc/samba/smb.conf`` >> See [smb.conf](smb.conf)

* Samba dienst neustarten<br>
``$ sudo service smbd restart``

## Konfiguration
[configuration.yaml](configuration.yaml)
* Meistverwendete befehle während der Konfiguration

 * reboot hass service: <br>
 ``$ sudo systemctl restart home-assistant@homeassistant.service``

 * raspi-config: ``$ sudo raspi-config``

 * update hass:<br>
  ``$ sudo systemctl stop home-assistant@homeassistant.service``<br>
  ``$ sudo su -s /bin/bash homeassistant``<br>
  ``$ source /srv/homeassistant/bin/activate``<br>
  ``$ pip3 install --upgrade homeassistant``<br>
  ``$ exit $ sudo systemctl start home-assistant@homeassistant.service``<br>

  * hass logfile: <br>
  ``$ sudo su -s /bin/bash homeassistant``<br>
  ``$ cd /home/homeassistant/.homeassistant``<br>
  ``$ nano homeassistant.log``<br>

  * config datei prüfen lassen:<br>
  ``$ sudo su -s /bin/bash homeassistant``<br>
  ``$ source /srv/homeassistant/bin/activate``<br>
  ``$ hass --script check_config``<br>

  * restart smb: ``$ sudo service smbd restart``

  * reboot rpi: ``$ sudo shutdown -r now`` or ``$ sudo reboot`` or ``$ sudo init6``
