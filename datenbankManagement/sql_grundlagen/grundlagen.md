### SQL Grundlagen

#### Kommentare: <br>
1. ``-- einzeilig`` <br>
2. ``/* Mehrzeilig */`` <br>

---

#### DML: Datenabfrage, Daten speichern, Daten behandeln.<br>
DML Beschäftigt sich mit dem Inhalt des Datenbestandes. "Manipulation -> In die Hand nehmen"<br>
DML Statements sind: CALL, DELETE, EXPLAIN PLAN, INSERT, LOCK TABLE, MERGE, SELECT, UPDATE

Beispieltabelle (abteilung): <br>

|id|bezeichnung|kuerzel|Ort|
|--|---|---|---|
|1|Admin|adm|Bochum|
|2|Informatik|IT|Essen|


##### Abfragen: ``SELECT``<br>
``SELECT * from tablename``

Wie finde ich alle Informationen zu einer Tabelle? (z.B. Abteilung)<br>
- unter OracleDB: ``SELECT table_name from user_tables``
    - Die Tabelle aus der Ausgabe suchen und merken (Abteilung).
- ``SELECT * from Abteilung``
    - ``*`` gib alle Attribute aus
    -  ``SELECT Ort from Abteilung`` gib nur das Attribut Ort aus <br>

Gib alle Abteilungen aus, deren Standort Bochum ist.<br>
 - ``SELECT * from abteilung where ort = 'Bochum'``

Gib alle Abteilungen mit Standort Bochum oder Essen aus, hierbei soll nur der Abteilungsnamen ausgegeben werden.
- ``SELECT bezeichnung from abteilung where ort = 'Bochum' OR ort = 'Essen'``
- ``SELECT  bezeichnung from abteilung where ort in ('Bochum','Essen')``
- ``SELECT bezeichnung from abteilung where lower(ort) in ('bochum','essen')``
- ``SELECT bezeichnung from abteilung where upper(ort) in ('BOCHUM','ESSEN')``

Welche Attribute hat die Tabelle Abteilung?
- ``desc Abteilung``
- ``SELECT * from Abteilung``

Gib alle Kuerzel sortiert nach Ort aus
- ``SELECT kuerzel from abteilung order by ort``

##### Schreibmethoden ``INSERT``,``UPDATE``,``DELETE`` (beispiele zu Abteilungstabelle)
Neuer Datensatz für das HR in die Tabelle Abteilung <br>
``INSERT into abteilung(bezeichnung, kuerzel, ort) values('Personalverwaltung', 'HR', 'Bern')``

Ändere die Ortschaft der IT Abteilung auf Berlin <br>
``UPDATE abteilung set ort = 'Berlin' WHERE kuerzel = 'IT'``

Lösche alle Abteilungen in Essen<br>
``DELETE from abteilung where ort = 'Essen'``

---

#### DDL: Datenbankstruktur behandeln
DDL Statements sind:<br> ALTER, ANALYZE, ASSOCIATE STATISTICS, AUDIT, COMMENT, CREATE, DISASSOCIATE STATISTICS, DROP, FLASHBACK, GRANT, NOAUDIT, PURGE, RENAME, REVOKE, TRUNCATE, UNDROP <br>

Syntaxaufbau: ``BEFEHL OBJEKTTYP <Objektname> [<weitere Angaben>]``<br>
Objekttypen: ``DATABASE``,``TABLE``

- Datenobjekt erstellen: ``CREATE``
    - DB Firma Erstellen:<br> 
    ``CREATE DATABASE Firma``
    - Tabelle Erstellen:<br>
    ```
    CREATE TABLE person ( 
            ID NUMBER(10) not null auto_increment,
            Vorname VARCHAR2(50) not null,
            Nachname VARCHAR2(5) not null,
            Personalnummer VARCHAR2(10) not null,
        );
    ```
    Syntaxerklärung<br>
    ``CREATE`` -> Erstelle<br>
    ``TABLE`` -> Tabelle<br>
    ``person`` -> name der tabelle<br>
    Innerhalb der ersten Klammer -> Attributendeklaration <br>
    ``ID NUMBER(10) not null auto_increment,``:
    Erstes attribut mit name "ID" und Datentyp "NUMBER" maximal "(10)" Zeichen. Darf nicht lehr sein "not null" und wird forlaufend hochnummeriert "auto_increment". Ab "," -> Deklaration für weiteres Attribut.



- Datenobjekt ändern: ``ALTER``
    - Neues Attribut (ahv) hinzfügen: ``ALTER TABLE person ADD ahv VARCHAR(15)``<br>
    - Datentyp einer spalte ändern: ``ALTER TABLE person MODIFY ahv VARCHAR2(20)`` 
    - Hinzufügen eines Primary Keys:<br> 
    ``ALTER TABLE person ADD CONSTRAINT person_PK PRIMARY KEY (ID)``
    - Die Personalnummer soll eindeutig sein:<br>
    ``ALTER TABLE person ADD CONSTRAINT Mitarbeiter_Personalnummer UNIQUE (Personalnummer)``
        - Syntaxerklärung:<br>
        ``ALTER`` -> Verändere<br>
        ``TABLE`` -> Eine Tabelle<br>
        ``person`` -> name der Tabelle<br>
        ``ADD CONSTRAINT``-> füge Einschränkung hinzu<br>
        ``Mitarbeiter_Personalnummer`` -> name der Einschränkung<br>
        ``UNIQE``,(``PRIMARY KEY``) -> Typ der einschränkung<br>
        ``Personalnummer`` -> betroffene Attribut<br>

###### Weiter Bispiele:<br>
Benutzer / Shema erstellen: ``CREATE User Kevin identified by S1ch3r35passwort``<br>


Erstelle beispieltabelle "Abteilung":
```SQL
CREATE TABLE abteilung ( 
    id NUMBER(10) not null auto_increment,
    bezeichnung VARCHAR2(50) not null,
    kuerzel VARCHAR2(5) not null,
    Ort VARCHAR2(50) not null,
);

ALTER TABLE abteilung ADD (
    CONSTRAINT abteilung_pk PRIMARY KEY(id),
    CONSTRAINT abteilung_krz UNIQUE(kuerzel)
);
```



Datenobjekt löschen: ``DROP``



###### Weiter Bispiele (DDL/DML) Übungen vom 17 Nov 2017:<br>
Benutzer / Shema erstellen: ``SQL create user Kevin identified by S1ch3r35passwort``<br>
Erlaube Benutzer zu verbinden: ``grant connect, resource to Kevin``<br>
Spaltennamen in Tabelle für Benutzer finden: ``desc dba_users``<br>
Benutzername suchen: ``select username from dba_users where username='Kevin'``<br>
Auf DB verbinden: ``connect Kevin/S1ch3r35passwort``<br>
Tabelle Mitarbeiter erstellen: ``create table mitarbeiter(vname varchar2(20), nname varchar2(20) not null)``<br>
Tabellenstruktur Anzeigen: ``desc mitarbeiter``<br>
Eintrag in Tabelle: ``insert into mitarbeiter(vname,nname) values('Kevin','Stäger')``<br>
Attribut hinzufügen: ``alter table mitarbeiter add(ma_nr number(10))``<br>
Attribut abfüllen, Eintrag in Feld ändern: ``update mitarbeiter set ma_nr = 1 where vname = 'Kevin'``<br>
Attributwerke anzeige und mit filtern: ``select vname, nname from mitarbeiter where nname like 'Stä%' order by 1``<br>
Primary Key definieren: ``alter table mitarbeiter add primary key (ma_nr)`` <br>
Primary Key mit constraint definieren: ``alter table mitarbeiter add constraint ma_nr_pk primary key (ma_nr)``<br>
Views mit INDEX als begriff suchen (als sys user): ``select view_name from dba_views where view_name like '%INDEX%'``<br>
als shema-user indexname des angemeldeten benutzer suchen: ``select index_name from user_indexes``<br>
Tabelle in neue Tabelle kopieren (ohne keys):``create table ma as select * from mitarbeiter``<br>
Datensätze duplizieren in einer Tabelle: ``insert into ma select * from ma``<br>
Zeilen in tabelle zählen: ``select count(*) from ma``<br>
Datumsattribut hinzufügen: ``alter table ma add (Anstellungsdatum date)``<br>
Aktuelles Datum abfüllen: ``update mitarbeiter set Anstellungsdatum = sysdate where ma_nr = 1``<br>
Vortagsdatum abfüllen: ``update mitarbeiter set Anstellungsdatum = sysdate -1 where ma_nr = 2;	Vortagsdatum abfüllen``<br>
Spezifisches Datum eintragen: ``update mitarbeiter set Anstellungsdatum = to_date(‘16.09.2015’,’dd.mm.yyyy’) where ma_nr = 3;``<br>
Transaktion speichern: ``commit``<br>
Datum in Format ausgeben und sortieren: ``select * from name, to_char(Anstellungsdatum, ‘dd.mm.yyyy hh24:mi:ss’) from mitarbeiter order by Anstellungsdatum``<br>
Tabelle erstellen und PK definieren: ``create table abteilung(abt_id number(10), abt_name varchar2(20), primary key(abt_id))``<br>
Namen und Typ von constraints des Shemas finden: als sys Benutzer:
```SQL
select view_name from dba_views where view_name like ‘%CONSTRAINT%’; 
ergibt user_constraints
als Schema Benutzer
desc user_constraints;
select constraint_name, constraint_type from user_constraints;	
```
Transaktion rückgängig machen (nur bis Letzten DDL): ``rollback``<br>
Eintrag löschen: ``delete from mitarbeiter where Name = ‘Amrein’``<br>
tabelle leeren: ``truncate table ma``<br>
tabelle löschen: ``drop table ma``<br>

