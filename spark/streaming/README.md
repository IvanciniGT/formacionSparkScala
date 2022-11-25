Nos van a llegar unos tweets, que tengo que procesar.
    
                        Sistema de Mensajería
                        KAFKA, ActiveMQ, RabbitMQ
TWEETER                 
App Telefono     --->   TWEETER         -----> Procesarla   ----> Guardar en una "BBDD"
(formulario)            (cola de tweets)        (spark)     ----> Contar los hashtags ---> TrendingTopic
    Tweet                                                   ----> Sacar PERSONAS MENCIONADAS ---> Mandar una notificación ----> Email
                                                            ----> Analisis más avanzado de los datos
                                                            
                                                            
Lo primero asignar un ID a cada tweet, secuencial
De unos tweets que nos llegarán... y que por ahora los vais a leer de un fichero de texto, queremos
    - Las menciones     Llevan una arroba!      -> Persona, ID
    - Los hashtags      Llevan una almohadilla  -> Hashtag, Número de ocurrencias total
    Dentro de los hashtags, vamos a querer quitar "sandeces".
        Hay ciertos hastags que considero sandeces... y esos paso de ellos.

    #España70 #ESPAÑA70
    #España70#goodvibes
    
// Lista de hashtags ignorados < - Gestionarla como un dato (RDD)

Esta lista creeis que debe ser una lista estática... que no va a cambiar a lo largo del tiempo?

regex -> regex101


Clave               Id del tweet
España70            1
MundialRuinoso      1
PutosAmos           1
NoMención           1
Genial              1
Goodvibes           1
Participación       1
PutosAmos           1
Genial              1

groupByKey([numPartitions])	When called on a dataset of (K, V) pairs, returns a dataset of (K, Iterable<V>) pairs.

Clave               tweets
España70            [1]
MundialRuinoso      [1]
PutosAmos           [1,5]
NoMención           [2]
Genial              [4,5]
Goodvibes           [4]
Participación       [4]



reduceByKey(func, [numPartitions])	When called on a dataset of (K, V) pairs, returns a dataset of (K, V) pairs where the values for each key are aggregated using the given reduce function func, which must be of type (V,V) => V. Like in groupByKey, the number of reduce tasks is configurable through an optional second argument.

Clave               concatenacion   recuento
España70            1               1
MundialRuinoso      1               1
PutosAmos           1_5             2
NoMención           2               1
Genial              4_5             2
Goodvibes           4               1
Participación       4               1

Ordenada
Quiero los priemros 5


2   PutosAmos           
2   Genial              
1   España70            
1   MundialRuinoso      
1   NoMención           
1   Goodvibes           
1   Participación       
