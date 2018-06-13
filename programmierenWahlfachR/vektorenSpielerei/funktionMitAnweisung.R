#Vektoren anlegen
groesse <- c(1.55,1.60,1.90,1.67)
gewicht <- c(50,50,80,70)

#Wert aus Vektor entfernen
groesse <- groesse[-3]

#Funktionen mit anweisungen
bmifunktion <- 
function(x,y){
  if (length(x)==length(y)) {
    x/y^2
  } else if (length(x)<length(y)) {
    "Vektorelänge von X ist kleiner"
  } else {
    "Vektorenlänge von y ist kleiner"
  }
}

#abrufen
bmi<-bmifunktion(x=gewicht, y=groesse)