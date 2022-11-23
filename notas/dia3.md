TDD

Test Driven development: Desarrollo primero las pruebas


12345678-T


T       √
-       √
8
7
6
5
4
3
2
1


numero_del_dni  12345678
letra_del_dni   T

REFACTORIZAR EL CODIGO:

Dedicarle un ratito al programa después de que lo tenemos funcionando para 
dejarlo de la mejor manera posible

---
Intro a Bigdata, Hadoop, Spark
Aprendiendo / repasando SCALA
    Diferencias entre Scala 2 y Scala 3
Aprendiendo a manejar SBT para la gestión, compilación, pruebas y ejecución de proyectos SCALA -------   MAVEN (JAVA)
Aprendiendo a implementar test unitarios en SCALA y a seguir buenas prácticas de desarrollo
    Modular
    Encapsulado
    TDD
    Refactorización
MapReduce
    Programación funcional
    Map, Filter, FlatMap
    Funciones anónimas
    
---

SPARK

Framework para el tratamiento de datos que opera sobre HADOOP

    - Core
        Procesar conjuntos de datos mediante técnicas MapReduce optimizadas
    - Librerias adicionales
        - SparkSQL
            Dentro de esos map/reduce... usar nomenclatura similar a la que tenemos en SQL
        - Spark Streaming
            Nos ofrece utilidades para poder aplicar esas transformaciones MapReduce
            NO una sola vez, sino DURANTE un periodo de tiempo... En bucle
                Ayudándome incluso a ir consolidadndo los resultados entre iteraciones
        - Spark Mlib - Machine Learning
            Implementa algunos (pocos, muy pocos) algoritmos de Machine Learning
        - Spark GraphX
            Trabajo con grafos


Nos puede ayudar a simular comportamientos/pensamientos/acciones que hacemos los humanos -> Inteligencia Artificial

Machine learning - Data mining
- Predicciones basadas en datos históricos
- Clasificaciones
- Agrupamiento
- Reducción de dimensiones

# Spark core

Es una libreria, que nos da utilidades para, sobre un cluster de hadoop aplicar técnicas map-reduce optimizadas.
Spark hace toda la distribución de datos en mi cluster usando solo memoria RAM.


## Cluster de Spark

Maquina 1   - Capacidad de cómputo
    Hadoop
    Spark           MAESTRO ! Coordinar todos los trabajos. A él le pido las cosas... Él me las devuelve
Maquina 2   - Capacidad de cómputo
    Hadoop
    Spark
Maquina 3   - Capacidad de cómputo
    Hadoop
    Spark
Maquina 4   - Capacidad de cómputo
    Hadoop
    Spark
Maquina 5   - Capacidad de cómputo
    Hadoop
    Spark
...
Maquina N   - Capacidad de cómputo
    Hadoop
    Spark

## Cómo me hago con un cluster de Spark?

Me puedo montar yo uno propio
Los clouds me ofrecen también la posibilidad de montar clusters de Spark... incluso de 

## Spark

Opensource: Que puedo ver el código...
    Depende de la licencia, puede que no me dejen ni tocarlo... o que si lo toco tenga unas imposiciones muy grandes
    
    Apache Public License
    MIT
    GPL GNU public license es ultrarestrictiva. Si quieres usar, modificar y distribuir mi software... 
        o lo estás usando dentro del tuyo, TU SOFTWARE también debe llevar la licencia GPL
    
    Linux: Kernel de SO más usado del mundo? Android . Lleva licencia GPL
        Redhat: Que montan su SO sobre el Kernel Linux, tiene obligación de dar ese código fuente también
        
        Redhat Enterprise Linux, que es Opensource, y tiene licencia GPL es gratis? NO
    
    Opensource != GRATIS

Es gratuito? Apache Spark si....
Hay licencia de pago? Databricks  < - Azure, AWS


# Cuando aplicamos técnicas MapReduce, partimos siempre de ? una coleccion de datos:

- Lista de textos
- Fichero de datos -> Lista de textos
- HashMap - Diccionario - ArrayAsociativo

# En Spark, esa colección de datos se denomina: RDD

## RDD

Resilent Distributed Dataset

- Distributed:  Los datos de la colección PUEDEN estar distribuidos en diferentes máquians (particiones)
- Resilent:     Si uno de los subconjuntos (particiones) de datos se pierde, Spark puede regenerarlo.
                Dicho de otra forma, Spark me asegura (o lo intenta) que no voy a perder datos.

# Qué gano ejecutando cosas en un cluster de Spark?

- Tolerancia a fallos, con los datos
- Usar la capacidad de cómpotu de todas las máquinas (o algunas de ellas) -> mejor rendimiento? NO NECESARIAMENTE

# Factores que van en contra del rendimiento al paralelizar entre distintas máquinas

- El acceso a los datos: Tráfico de RED
- La creación de procesos en cada máquina
- Ordenar en cada máquina... Y después consolidar todos los resultados en una tercera máquina
   ... allí necesitaré de nuevo ORDENAR

10.000.000 dardos para calcular PI: 1 maquina
                                    4 máquinas - Spark ? 
                                    
# Peor operación que puedo pedir a una computadora desde el punto de vista computacional al jugar con datos?

ORDENAR! Nunca ordeno a no ser que sea extremadamente necesario

CUIDADO, que hay operaciones encubiertas que ordenan: DISTINCT:
    1º ORDENA
    2º Limpia duplicados
        Un groupBY
    1º ORDENA
    2º Agrupar

Los RDD los partimos en trozos, y al hacerlo, 
tengo oportunidad de que cada trozo se procese por un individuo distinto.

Si tengo un RDD con un trozo(partición).... donde se acaba ejecutando todo? En una única máquina
Más me vale, partir las cosas como mejor me convenga en cada caso.
Si me paso partiendo, irá muy lento, ya que hbará que consolidar muchos subconjuntos de datos (y eso es pesado)
Si parto poco... no llego a paralelizar el trabajo todo lo que podría... y va más lento de lo que podría llegar a ir.

Tiene un impacto directo, la arquitectura del cluster! (la infraestructura)
Al final, importa el número de CORES que haya por ahi en el cluster (ejecutores)

Cluster:
    5 máquinas x 2 cores = 10 cores
    
    Me llegan 100M datos... y los divido en 20 trozos... qué pasa?
    
    Cada conjunto tiene: 5M
    
    C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15, C16, C17, C18, C19, C20
                                            _________________________________________________
                                                A esperar
    Que hace el maestro de spark?
    Cuantos ejecutores puede usar el maestro? Hasta 10
    
    Ej1 - Core1- Maquina 1      < C1 (ACABADO) < C11
    Ej2 - Core2- Maquina 1      < C2
    Ej3 - Core1- Maquina 2      < C3
    Ej4 - Core2- Maquina 2
    ....    
    Ej20 - Core2- Maquina 5     < C10
    
    Quizás partir en 10 trozos podría parecer una decisión más inteligente... estais de acuerdo? 
    Siempre tomaría esa decisión? 
        - Tengo que analizar cómo de complejo es integrar esos datos a posteriori



Tweeter - TRENDING TOPIC !!!!!

Maquina 1 - Core 1
    Tweet1 --> mensajito ---> Hashtags -> H1 H2 H3 H4 
    Tweet2 --> mensajito ---> Hashtags -> H5 H6 H1 H7
    Tweet3 --> mensajito ---> Hashtags -> H1 H2 H7
    ---------------------------------------------------->
                                                            H1      3
                                                            H2      2
                                                            H7      2
                                                            H3      1
                                                            H4      1
                                                            H5      1
                                                            H6      1
Maquina 7 - Core 2
    Tweet111 --> mensajito ---> Hashtags -> HA HB H3 H4 
    Tweet112 --> mensajito ---> Hashtags -> HA HC H1 H7
    Tweet113 --> mensajito ---> Hashtags -> HD HE HF
    ---------------------------------------------------->
                                                            HA      2
                                                            HB      1
                                                            H3      1
                                                            H4      1
                                                            HC      1
                                                            H1      1
                                                            H7      1
                                                            HD      1
                                                            HE      1
                                                            HF      1
Ahora me toca, juntar, ordenar y contabilizar(agrupados)...
Pues a lo mejor habría sido buena idea... procesarlos todos en una máquina !
En vez de 10 -> 5

    Acabas de descubrir Apache Storm !
    
Para trabajar con Spark, lo primero que necesitamos es un cluster...
    por suerte, para jugar y desarrollar, spark, si no tengo un cluster, me lo monta él automaticamente en mi maquina:
        Donde sea que esté programando.
        
# Cuando estoy en el mundo BIG DATA no me puedo centrar solo en la funcionalidad:

- Velocidad de procesamiento que voy a conseguir... es suficientemente buena !
- Necesidades de almacenamiento de información


ELASTICSEARCH
MONITORIZAR SISTEMAS
BEATS- MetricBeat -> 10 segundos -> en bruto -> 10 Mb cada minuto por máquina -> 1 año
100 maquinas -> Pbs

El almacenamiento es barato a día de hoy? ES LO MAS CARO EN UN ENTORNO DE PRODUCCION !
10Tbs en 1000€
10Tbs -> 150€

# De cara a comenzar a trabajar con Spark:

1º Cluster: Si no lo tengo, puedo usar uno de juguete, que me regala spark
2º Una conexion con el cluster                                  - SparkContext | SparkSession
    (Si tuviera SparkStreaming, esto lo hago en bucle)
    3º Meter mis datos en spark: RDD o un DataFrame
        RDD: Lista
        Dataframe: Lista de listas
                     ^          ^
                     ^       Columnas de mi tabla
                     ^
                     Filas de mi tabla
    4º Al RDD (Dataframe) le voy a aplicar MAP REDUCE POR UN TUBO !!!!!
    5º Volcar los resultados donde sea
    
# En SparkStreamming trabajamos con el concepto DStream

Que es un DStream? Una lista de RDDs

# Al trabajar con Spark... quién va a ejecutar mi código?
    
    Mi máquina                                              |          Cluster Spark
    ----------------------------------------------------------------------------------------------------
     Programa                                               |     Maestro                   Ejecutor
     1º abrir conexión con el cluster
     2º Le mando al cluster las "funciones scala"
                                    normalizarDNI
                                    estimarPI
                                                                 Reenvia/Distribuye
                                                                 las funciones y los datos
                                                                 a los ejecutores   
                                                                                        
                                                                                            Ejecutan las funciones
                                                                                            sobre los datos
                                                                                            
                                                                                            Devuelven resultados
                                                                                            
                                                                 Consolida resultados
                                                                 Devuelve /almacena
     Hago lo que quiero después
     
# NOTA:

Spark nosofrece una interfaz gráfica, con la que entender/analizar/monitorizar lo que está ocurriendo en el cluster


# Diseño del programa que estima PI usando Spark... un cluster distribuido

# Como distibuyo. De qué datos parto... y que transformaciones hago?

    DATOS DE PARTIDA    
        1
        2           -> tiroDardo -> calcularDistancia -> Está Dentro -> Sumo
        3
        ...
        1M
