### Was sind Datenmodelle?
- Datenmodell ERM = Entity Relationship Model
- Beinhaltet ein ERD = Entity Relationship Diagram
- Bildet Tabellen (Entitäten) und deren Beziehnung (Relationen) zu anderen Tabellen ab
- Ein Datenmodell ist DBMS unabhängig und dient nur zur bisuellen/vereinfachten verständlichen darstellung
- Es beschreibt "was" genau zu einander in Abhängigkeit steht
- Das Ziel ist es möglichst keine Redundanten Daten zu haben (Effizienzsteigerung)

#### ERD
- Ein ERD ist die Basis jedes Datenmodells
- Das ERD ist die grafische Darstellung eines Datenmodells
- Es bildet die Beziehungen der Entitäten untereinander ab

#### ERM
- Ein ERM beinhaltet ein ERD (grafische Darstellung) sowie die
darin enthaltenen Elemente und deren Bedeutung und
Beziehung zueinander
- Ein ERM wird in der konzeptionellen Phase der SW
Entwicklung verwendet. Es dient als Verständigung zwischen
Anwender und Entwickler
- In der Implementierungsphase dient es als Grundlage für das
Datenbankdesign
- ERM’s sind quasi der Standard zur Datenmodellierung
- Es gibt unterschiedliche Darstellungsformen von ERM’s

#### Einsatzgebiete
- Refactoring
  - Verstehen eines bestehenden Datenmodells
  - Erweitern eines bestehenden Datenmodells
- Grundkonzept
  - Strukturierter Neuaufbau eines Datenmodells
- Logische Organisation der Daten
  - Konzeptionelles Schema
- Physische Organisation der Daten
  - Internes Schema
- Beschreiben der Datenarchitektur eines Systems

### Terminologie/Definition
- Entity, Entität, Entitätsmenge, Tupel, Row, Datensatz
  - Ein in sich geschlossenes Objekt
  - Eindeutig identifizierbar
  - In der Datenmodellierung wird mit Entitätsmengen geearbeitet
  - Eintitätsmengen =/= Entität
---
- Entitätsmenge
  - Ist eine Menge Mehrerer gleicher Entitäten
  - Entspricht einer Tabelle in der Datenbank
---
- Beziehung, Relation
  - Die Beziehung mehrerer Entitäten zu einander
  - Beziehungen zwischen Entitäten sollten immer sprechend, in beide Richungen, bezeichnet werden:
   - Mitarbeiter > {leitet} > Abtelung
   - Abteilung > {wird geleitet von} > Abteilung
---
- Attribut
  - Eine Entität besteht aus mehreren Attributen
  - Die Attribute einer Entität bestimmen ihre Eigenschaften
    - Eintität Person:
    - Attribute:
      - Name
      - Vorname
      - Adresse
      - PLZ
      - Ort
---
- Kardinalität
  - Die Kardinalität der Beziehungen zwischen Entitäten sind unterschiedlich
    - Eine PErson wohnt in genau einem Ort
    - In einem Ort wohnen mehrere Personen
  - Möglichkeiten:
    - 1:1 "Jede Row der Tabelle A genau einer Row der Tabelle B"
      - Bsp: Jede Home Cinema Anlage hat genau einen Subwoofer
      - Ev macht es mehr sinn alle Attribute in eine Tabelle zu schreiben
      - Gründe für mehrere Tabellen:
        - Unterschiedliche Zugriffsrechte
        - Reduzieren der Komplexität
    - 1:0/1 "Jede Row der Tabelle A ist mit genau einer oder keiner Row von Tabelle B verbunden"
      - Bsp: Jede Person hat einen- oder keinen Eheparter
    - 1:n "Jede Row der Tabelle A ist mit einer oder mehrerer Row der Tabelle B verbunden"
      - Bsp: Jeder Lehrer unterrichtet ein- oder mehrere Fächer
    - 1:0/n "Jede Row der Tabelle A ist mit keiner, einer oder mehreren Rows der Tabelle B verbunden"
      - Bsp: Jeder Kunde hat keine-, eine- oder mehrere Rechnungen
      - Bsp2: Jede Person hat kein, eins- oder mehrere Fahrzeuge
  - m/n "Viele zu viele"
    - werden duche eine Zwischentabelle in zwei 1:n - n:1 Beziehungen aufgelöst
    - Bsp: Jede Rechnung enthält mehrer Artikel, jeder Artikel kann auf mehreren Rechnungen stehen
---
- Konzeptionelles Modell, Logisches Modell
- Internes Modell, Physisches Modell
- Beschreiben der Datenarchitektur eines Systems

---

### Notation
![Notation1](notation1)<br>
![Notation Kardinalität](notation2)<br>
![Diverse andere Notationsmöglichkeiten](notation3)<br>

---
### Physische Modell
- Um das Datenmodell in der Datenbank abbilden zu können,
müssen Tabellen erstellt werden
- Tabellen sind das physische Abbild einer Entitätsmenge
- Tabellen bestehen aus mehreren Attributen mit eigenen
Datentypen
- Gängige Datentypen:
  - NUMBER (Int)
    - Positive Zahlen zwischen 1 x 10-130 und 9.99...9 x 10125 mit bis
zu 38 unterschiedlichen Zahlen
    - Negative Zahlen zwischen -1 x 10-130 und 9.99...99 x 10125 mit
bis zu 38 unterschiedlichen Zahlen
    - NUMBER(2) -> -99 bis 99
    - NUMBER(3.2) ->  -9.99 bis 9.99
  - CHAR
    - Alphanummerische Zeichen
    - Alloziert genau den Platz der definiert wurde
    - Ist der String kürzer wird der rest mit Leerzeichen abgefüllt
    - Möglicher Einsatzbereich:
      - Boolesche Werte (T/F)
      - Attribute, die immer mit denau dieser Anzahl Zeichen gefüllt werden
    - Bsp:
      - CHAR(10) -> String mit 10 Zeichen nichtbenutzte Zeichenhalter werden mit Leerzeichen abgefüllt
  - VARCHAR2 (Varchar)
    - Alpahnumerische Zeichen
    - alloziert nur den Platz derr verwendet wird
    - wird verwendet um Strings abzuspeichern
    - Bsp:
      - VARCHAR2(50) -> String mit maximal 50 Zeichen
      - VARCHAR2(40) -> String mit maximal 40 Zeichen
  - DATE
    - Datum
    - Tag, Monat, Jahr (4 Stellig), Stunde, Minute, Sekunde
  - TIMESTAMP
    - Datum
    - Präziser als DATE
    -  Tag, Monat, Jahr (4 Stellig), Stunde, Minute, Sekunde, Sekundenbruchteile, Bei Bedarf auch die Zeitzone (TIMESTAMP WITH TIMEZONE)
  - CLOB (Character Large Object)
  - BLOB (Binary Large Object)

  ---

#### ID's & Keys
Problem: Eine Entität muss eindeutig Identifizierbar sein. Wie wird das erreicht? Nicht durch EntitätsAttribute sondern durch Nummern -> ID
ERGO: Jeder Datensatz/Row besteht aus einer in der Tabelle eindeutig Identifizierbarer Nummer. Die sogenante ID.
Diese ID muss nicht zwingend einsehbar sein. Unter MSSQL wird die ID ausgegeben. Oracle jedoch verwaltet diese eigenständig

Bsp:

  ID|Vorname|Nachname
  ----|----|----
  1|Kevin|Stäger
  2|Peter|Muster

In einer Mastertablle wird oft eine Nummer als Primary Key(PK) angelegt. Eine Mastertabelle ist sind nicht abgeleitete Tabellen (Zwischentabellen).
In den Zwischentabellen werden die Primary Keys meist aus den Primary Keys der Mastertabellen abgeleitet und Zusammengesetzt.<br>
Jede Tabelle Braucht einen Primary Key!
<br><br>
Foreign Keys (FK) referenzieren auf einen Datensatz einer anderen Tabelle.
Durch das definieren eines FK Constraints stellt die DB sicher dass keine falschen Werte in die Tabellen geschrieben werden können.<br>
Bsp: Es wird geprüft ob der Kunde mit der Kundennummer 99 existiert bevor eine Rechnung für ihn erstellt werden kann.

Bsp:

Tabelle Anrede:<br>

ID(PK)|Anrede
----|----
1|Herr
2|Frau


Tabelle Person:<br>

ID(PK)|AnredeID(FK)|Vorname|Nachname
----|----|----|----
1|1|Kevin|Stäger
2|1|Max|Muster
3|2|Ruth|Steiner

---
Tipps:
- Datenbank Benutzer
  - Eigene Tabellen sollten nie in DB internen Schemen angelegt werden
  - Um Tabellen erstellen zu können, muss also ein Datenbankbenutzer
angelegt werden
- Tablespaces
  - Bei ORACLE werden die Tabellen in «Tablespaces» abgelegt
  - Tablespaces sind logische Konstrukte, welche auf physischen Files,
den Datenfiles, basieren
