# Cheat sheet

## Login to DB:

~~~
sqlplus /nolog
SQL> connect / as sysdba
~~~


## DB

Get DB Objects status:

~~~
SQL> SELECT count(*) FROM dba_objects where STATUS='INVALID';
SQL> SELECT count(*),status FROM dba_objects group by status;
~~~
