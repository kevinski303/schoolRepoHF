#Datenquelle: Kriminalstatistik Bern 2008-2015, Statistik Stadt Bern, Abgerufen am 25.05.2018
#http://www.bern.ch/themen/stadt-recht-und-politik/bern-in-zahlen/katost/19kristr/19kristr-xls
###############



############# Datenaufbereitung ############# 

#Vektoren
jahr <- c(2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015)

StGBStraftaten <- c(19485, 21198, 18426, 21388, 22652, 21383, 17936, 16746)
BetmGStraftaten <- c(5357, 6866, 6967, 7359, 6756, 6433, 6284, 6127)
AuGStraftaten <- c(491, 617, 937, 917, 1113, 1155, 1236, 1081)
BundesNebenGesesetzeStraftaten <- c(178, 172, 209, 381, 335, 274, 223, 279)
KantonaleGesetzeStraftaten <- c(189, 302, 462, 597, 532, 501, 601, 482)

LeibLeben <- c(706, 661, 689, 758, 785, 696, 600, 569)
Vermoegen <- c(15526, 17164, 14456, 16343, 17044, 16806, 14195, 13201)
Sexuell <- c(148, 125, 114, 99, 137, 123, 120, 84)
UebrigeStgb <- c(1201, 1516, 1596, 1841, 2637, 1792, 1484, 1097)



#Total Straftaen
total <- function(st,be,au,bu,kt) {
  if (length(st)==length(be) || (be)==length(au) || length(au)==length(bu) || length(bu)==length(kt)) {
    st+be+au+bu+kt
  } else {
  "Die Vektoren sind nicht gleicher länge"
  }
}

totalStraftaten <- total(st=StGBStraftaten, be=BetmGStraftaten, au=AuGStraftaten, 
                         bu=BundesNebenGesesetzeStraftaten, kt=KantonaleGesetzeStraftaten)

straftatenMatrix <- cbind(jahr, totalStraftaten, StGBStraftaten, BetmGStraftaten, AuGStraftaten, 
                          BundesNebenGesesetzeStraftaten, KantonaleGesetzeStraftaten)

straftatenMatrix



#Total StgB Straftaten
totalStgb <- function(l, v, s, u) {
  if (length(l)==length(v) || length(v)==length(s) || length(s)==length(u)) {
    l+v+s+u
  } else {
    "Die Vektoren sind nicht gleicher länge"
  }
}

totalStgbStraftaten <- totalStgb(l=LeibLeben, v=Vermoegen, s=Sexuell, u=UebrigeStgb)

stgbMatrix <- cbind(jahr, totalStgbStraftaten, LeibLeben, Vermoegen, Sexuell, UebrigeStgb)

stgbMatrix




############# Statistiken ############# 

#Total Straftaten nach Jahr
barplot(totalStraftaten, names.arg = jahr, 
        space = 0, main = 'Anzahl Straftaten Bern 2008 - 2015',
        xlab='Jahr', ylab='Anzahl Straftaten'
        )


text(0.5, totalStraftaten[1],totalStraftaten[1],col = 20)


ii <- 0.5
for (i in totalStraftaten) {
  paste(c('text(',ii,', totalStraftaten[',i,'],totalStraftaten[',i,'],col = 20)'),collapse="")
  ii <- ii + 1
}



#Plotly
library(plotly)
straftatenDF = data.frame(jahr, totalStraftaten, StGBStraftaten, BetmGStraftaten, AuGStraftaten, 
                          BundesNebenGesesetzeStraftaten, KantonaleGesetzeStraftaten)


p1 <- plot_ly(straftatenDF, x= jahr, y = totalStraftaten)


p2 <- plot_ly(straftatenDF, x = jahr, y = totalStraftaten, name = 'Total', type = 'bar') %>%
  add_trace(y = StGBStraftaten, name = 'davon StGB') %>%
  add_trace(y = BetmGStraftaten, name = 'davon BetmG') %>%
  layout(barmode = 'group')



p3 <- plot_ly(straftatenDF, x = totalStraftaten, y = totalStgbStraftaten , name ='StgB', type = 'scatter', text = jahr,
              mode ='markers', size = ~totalStgbStraftaten, marker = list(opacity = 0.5, sizemode = 'diameter')) %>%
  add_trace(y = BetmGStraftaten, size = BetmGStraftaten, name = 'BetmG') %>%
  add_trace(y = AuGStraftaten, size = AuGStraftaten, name = 'AuG') %>%
  add_trace(y = BundesNebenGesesetzeStraftaten, size = BundesNebenGesesetzeStraftaten, name = 'Bundesnebengesetze') %>%
  add_trace(y = KantonaleGesetzeStraftaten, size = KantonaleGesetzeStraftaten, name = 'Kantonale Gesetze') %>%
  layout(xaxis = list(showgrid=FALSE),
         yaxis = list(showgrid=FALSE),
         showlegend = TRUE)
