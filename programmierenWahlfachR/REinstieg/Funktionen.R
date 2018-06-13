#Erstelle Vektor
groesse = c(1.55,1.60,1.90,1.67)
gewicht = c(50,50,80,70)

#Einfache Funktion
bmiFunktion = function(x,y) {
  x/y^2
}

bmi = bmiFunktion(x=gewicht,y=groesse)

#Funktion mit if
bmiFunktion = NULL
bmi = NULL
#groesse=groesse[-3]

bmiFunktion = function(x,y) {
  if (length(x) == length(y)) {
    x/y^2
  } else {
    print("Die Vektoren sind nicht gleicher Laenge!")
    bmi = NULL
  }
}

bmi = bmiFunktion(x=gewicht,y=groesse)