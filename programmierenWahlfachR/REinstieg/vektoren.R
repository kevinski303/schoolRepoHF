#Vektor mit körpergrösse erstellen
groesse=c(1.55,1.60,1.90,1.62);

#Vektor mit Namen erstellen
name=c("Julia","Fritz","Paul","Mia");

#Vektor ausgeben
name[1];
name[c(2,3)]; #c;combine

#Vektor ändern
name[1] = "Hanna";

#Länge eines Vektor ausgeben
length(groesse);

#Mittelwert groesse
mean(groesse);

#Standartabweichung
sd(groesse);

#Minimum/Maximum
min(groesse);
max(groesse);

#Datentyp abfragen
is.numeric(groesse);
is.character(groesse);

#Datentyp umwandeln
groesse=as.character(groesse);
groesse=as.numeric(groesse);