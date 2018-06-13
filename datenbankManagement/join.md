Einfaches Join (Full Join) <br>
bsp:
```sql
select e.first_name, e.last_name, s.salary
from salaries s
  join employees e on s.emp_no = e.emp_no
  where s.to_date > sysdate() and s.salary > '150000'
```
>selektiere first_name, last_name, salary von Tabelle emplyoees genannt e und Tabelle salaries genannt s.<br>
Verknüpfe Tabelle e und s auf Spalte emp_no der Tabelle e mit Spalte emp_no der Tabelle s (fremdschlüssel[fk]).<br>
Gib Datensätze aus die dem wert to_date der tabelle s grösser als dem jetzigen Systemdatum entsprechen und Salary der Tabelle s grösser als 150000 ist.

---

Inner Join<br>

---

Left Join<br>

---

Outter Join <br>


