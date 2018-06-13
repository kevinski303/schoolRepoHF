#Erzeuge Sequenzen
seq0=seq(1,10,0.01) #von,bis,in schritten

#Erzeugen von Zufallszahlen
set.seed(1)

normal1000 = rnorm(1000,mean=0,sd=1) #generiert 1000 normale Zufallszahlen mit der Standartabweichung 0s

normal1000

#Streudiagramm erstellen
plot(normal1000)

#Dichtenfunktion
plot(function(normal1000) dnorm(normal1000), xlim = c(-5,5), ylab = "f(x)")

#Verteilungsfunktion
plot(function(normal1000) pnorm(normal1000), xlim = c(-5,5), ylab = "F(x)")

#Quantilsfunktion
plot(function(normal1000) qnorm(normal1000), ylim = c(-3,3), ylab = "F^{-1}(x)")
