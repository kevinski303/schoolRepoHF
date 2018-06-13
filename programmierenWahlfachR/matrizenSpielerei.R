#Vektoren erstellen
modell <- c('A3', 'X5', 'Golf', 'Panda')
ps <- c(140,250,160,80)

#Vektoren zu Matrize kombinieren
auto <- cbind(modell,ps)

#Dimensionen ausgeben
dim(auto) #Dimension der Matrizen
nrow(auto) #Anzahl Zeilen 
ncol(auto) #Anzahl Zeilen

#oder Kombination
nrow(auto); ncol(auto)

#Zeilennummern durch Namen ersetzen
name <- c("Audi","BMW","VW","Fiat") #vektor Anlegen
rownames(auto) <- name #Name der Matrizenzeilen zuweisen
auto;

#Spaltennamen Ändern
spaltenname <- c("Modell", "Max. PS")
colnames(auto) <- spaltenname
auto;


#Einzelne, Zeilen oder Spalten auftufen
auto[1,1] #Erste Zeile, Erste Spalte von Auto
auto["BMW","Max. PS"] #Zeile mit Namen BMW, Spalte mit Name Max. PS
auto["Audi",] #Komplette Zeile ausgeben

#Sortieren nach Zeilennamen
auto2 <- auto[order(rownames(auto)),] #Neue Matrize mit Order nach rownames von alter Matrize
auto2;

