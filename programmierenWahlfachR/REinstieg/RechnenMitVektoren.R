
groesse = c(1.55,1.60,1.90,1.67);
gewicht = c(50,50,80,70);

#Wert aus Vektor löschen
#groesse = groesse[-3];

if (length(groesse) == length(gewicht)) {
  bmi = gewicht/groesse^2;
} else if (length(groesse) > length(gewicht)){ 
  bmi = NULL;
  "Der Vektor 'groesse' ist länger als der Vektor 'gewicht'!";
} else {
  bmi = NULL;
  "Der Vektor 'gewicht' ist länger als der Vektor 'groesse'!";
}
