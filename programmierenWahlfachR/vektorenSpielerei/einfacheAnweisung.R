#Vektoren anlegen
groesse<-c(1.55,1.60,1.90,1.67)
gewicht<-c(50,50,80,70)

#Wert aus Vektor entfernen
groesse<-groesse[-3]

#Anweisung
bmi <- if(length(groesse)==length(gewicht)) {
  gewicht/groesse^2
} else if(length(groesse)<length(gewicht)) {
  "Vektor groesse ist keliner als Vektor gewicht"
} else {
  "Vektor gewicht ist kleiner als Vektor groesse"
}