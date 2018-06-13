#Vektoren anlegen
groesse<-c(1.55,1.60,1.90,1.67)
gewicht<-c(50,50,80,70)

#Funktion anlegen
bmifunktion<-function(x,y){
  x/y^2
}

#Funktion aufufen
bmi<-bmifunktion(x=gewicht,y=groesse)