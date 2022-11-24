# Generador aleatorio de DNIs válidos

Objeto GeneradorDNI
    generarDNI

## Spark

coleccion de datos de partida: 1...n -> dni aleatorio





P1  1    2   3   4   5   6   7   8   9

Repartition 3       Si altera el orden  Con más o menos particiones de las que tenia antes

P1  1   4   7
P2  2   5   8
P3  3   6   9

Repartition 5

P1  1   2
P2  3   4
P3  5   6
P4  7   8
P5  9

Coalesce    2       No altera el orden  Siempre disminuye el numero de particiones
                    Y al hacerlo, procesa los datos secuencialmente, 
                    lo cual suele dar un mejor rendimiento

P1  1   2   3   4   5
P2  6   7   8   9