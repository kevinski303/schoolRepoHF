#### ORACLE Umgebungsvariablen
*Unter UNIX case sensitiv!*

---

##### ${ORACLE_BASE}
- Basis Directory
- Unter Anderem
  - Software
  - Admin Verzeichnis
  - ${TNS_ADMIN} Verzeichnis

---

##### ${ORACLE_HOME}
Als ORACLE_HOME wird das Installationsverzeichnis der ORACLE
Software einer bestimmten Art- und Version bezeichnet </br>
Es ist üblich, dass mindestens zeitweise verschiedene ORACLE
Software Versionen auf einem System installiert sind.</br>
> ORACLE hat mit ORACLE 11g die “Out Of Place” Upgrades eingeführt.
Bei einem Upgrade z.B. von 12.1.0.1 auf 12.1.0.2 muss der komplette Softwarestack
parallel neu installiert werden

Das Verzeichnis ``${ORACLE_HOME}/bin`` ist üblicherweise im Pfad

Beispiele für egeine ORACLE_HOME's:
- ORACLE RDBMS Version 12.1.0.1
- ORACLE RDBMS Version 12.1.0.2
- ORACLE WLS Version 12.1.0.3
- ORACLE Enterprise Manager Version 12.1.0.4
- etc.

Da das ORACLE_HOME im Leben einer Datenbank ändern wird, sollten
keine Konfigurationen darin abgelegt werden.

Das ORACLE_HOME wird unter ``${ORACLE_BASE}/product`` installiert

---

##### ${ORACLE_SID}
- SID steht für System ID und definiert den eindeutigen Namen einer Instanz einer Datenbank auf dem Host. </br>
- Es muss im oratab korrekt in Verbindung mit dem korrekten ORACLE_HOME eingetragen werden.
- Wird in Verbindung mit dem gesetzten ORACLE_HOME verwendet
um lokale Verbindungen zur Datenbank zu machen.

>Ist die Kombination ORACLE_HOME/ORACLE_SID nicht korrekt
findet ORACLE die Datenbank nicht. Die ORACLE_SID sollte in UPPERCASE angegeben werden

---

##### ${TNS_ADMIN}
• ${TNS_ADMIN} bezeichnet den Ort, wo die ORACLE
Netzwerkkonfiguration abgelegt ist
• Default = ${ORACLE_HOME}/network/admin

> Sollte pro Host zentral abgelegt sein, damit es nicht mehrfach gepflegt
werden muss

Official Oracle Guides: </br>
[RDBMS 12cR1 Full Documentation](http://docs.oracle.com/database/121/nav/portal_booklist.htm) </br>
[RDBMS 12cR1 Installation Guide under Linux](http://docs.oracle.com/database/121/LADBI/toc.htm)
