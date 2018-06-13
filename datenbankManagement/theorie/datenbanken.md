>Datenbanken entstanden in den 60er Jahren aus der Notwendigkeit die Zugriffe auf die immer grösser
werdenden Datenmengen der Grossrechner zu vereinfachen und zu vereinheitlichen. In den 70er Jahren
wurden die ersten relationalen Datenbankmodelle entwickelt (Codd). Mit diesen RDBMS (Relational
Database Management System) Systemen wurde das Erstellen und der Zugriff auf die Daten massiv
vereinfacht.
Nur ein wohlüberlegtes Datenbankmodell garantiert Flexibilität, problemlose Erweiterung, fehlerfreie
Datenablage und schnelle Zugriffszeiten auf die Daten. Das Erstellen eines korrekten Datenmodells ist also
die Basis für erfolgreichesa Arbeiten mit Datenbanken.
Dieses Modul basiert auf dem am weitesten verbreiteten Datenbankmodell „Relationale Datenbank“.
Übungen und Beispiele werden an Hand des RDBMS Produkts Oracle Database unter Linux gemacht.

##### Definition von Datenbanken
> Eine Datenbank ist ein System zur Beschreibung, Speicherung und Wiedergewinnung von
umfangreichen Datenmengen, die von mehreren Anwendungen (– gleichzeitig –) benutzt werden
können. Sie besteht aus zwei Hauptteilen:
1. Den eigentlichen Daten und
2. dem Datenbank-Management-System (DBMS), das gemäss einer vorgegebenen
Beschreibung Daten speichern, suchen, löschen oder ändern kann.

##### Eigenschaften von Datenbanken
>• Die Daten werden klar strukturiert. Es gibt keine Redundanzen.<br>
• Die Datenstruktur wird zentral gespeichert und ist unabhängig von der Applikation.<br>
• Die Anwendungsprogramme sind “datenunabhängig”, d.h. interne Reorganisationen innerhalb des
Datenbanksystems tangieren die Anwendungsprogramme nicht.<br>
• Die Datenbank ist leicht erweiterbar.<br>
• Die Integrität der Daten wird von der Datenbank garantiert.<br>
• Die Daten sind zeitlich persistent und nicht an die Lebensdauer eines Programms gebunden.<br>
• Den verschiedenen Benutzern stehen spezifische Datensichten zur Verfügung.

##### Anforderung an eine Datenbank
>An Datenbanken werden hohe Anforderungen gestellt.
Unterschiedliche Personengruppen haben unterschiedliche Anforderungen an Datenbanken.<br>
Hier ein paar Beispiele:
- Anwender
  - Die Antwortzeit der Datenbank muss so schnell wie möglich sein
  - Die Datenbank muss konsistent sein
  - Die Daten müssen zu einem genau definierten Zeitpunkt gespeichert- oder rückgängig gemacht
werden können (save- oder cancel Button)
  - Die Datenbank muss multiuserfähig sein
- Management
  - Die Datenbank muss zu den definierten Zeiten verfügbar sein.
    - z.B. 7/24h oder zu Bürozeiten
  - Die Datenbank muss nach einem Systemausfall „ohne“ Datenverlust wieder hergestellt werden
können
  - Der Zugriff auf die Daten muss eingeschränkt werden können, damit nicht alle alles sehen
  - Die Datenbank soll wirtschaftlich mit dem Speicherplatz umgehen um nicht unnötig hohe Kosten zu
verursachen
- Entwickler
  - Die Datenbank soll einfach erweiterbar sein
  - Der Zugriff auf die Daten soll so durchsichtig wie möglich sein
  - Die Datenbank soll auch bei einer grossen Datenmenge noch performant Antworten zurück liefern
Datenbank Administratoren
  - Die Datenbank soll einfach zu verwalten sein
  - Die DB soll sprechende Fehlermeldungen ausgeben
  - Die Datenbank muss vollumfänglich überwacht werden können um mögliche Fehler zu erkennen
  - Die DB soll einfach und zuverlässig zu sichern sein
