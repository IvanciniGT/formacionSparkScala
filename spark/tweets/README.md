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