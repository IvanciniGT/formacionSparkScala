La librearia sparkSQL nos permite trabajar con rdds, "como si fueran"
tablas de una bbdd relacional

Lo que hacemos es transfromar un rdd es un dataframe,
que básicamente es un rdd con tuplas, cuyos valores se corresponden a columnas

SparkContext (rdds)         -> SQLContext (dataframes)
SparkSession (dataframes)   -> SparkContext(rdds)

El SparkSession, implicatemente genera un SQLContext



...

Quiero cargar una tabla en un fichero con Datos de clientes

Los ficheros que estructura tienen? Tabla excel
    Columnas
        DNI Nombre  Apellido    FechaNacimiento     Email.....
        
Leer ese fichero, ...., agrupaciones, y cargarlo   >>>>                        ETL
                    v
                    transformaciones/manipulaciones del dato

Objeto: Persona
    Funciones
    Variables
...

Procesos de carga en batch: Me llegan el dia n unos datos y los cargo

Procesos de carga en tiempo real: No paran de llegar datos, que quiero disponibles inmediatamente

---

# Spark Streamming

Abrir conexión con spark y configurar unas funciones de transformacion y reducción de datos.
La diferencia es que esas funciones no se ejecutan una sola vez, sino un montón de ellas.

De entrada nos cambia la forma de importar nuevos datos.

Al trabajar con streaming... nosotros vamos a procesar datos hasta el infinito y más allá!

Llegan datos....1.2.3.4.5.6.7.8

Procesando datos 1.2.3.4.5.6.exploto

Arreglar el programa, si es necesario y ejecutarlo de nuevo... desde donde? Desde donde estaba
                            7,8

La libreria SparkStreaming trabaja con el concepto de CHECK_POINT.
Básicamente es una especie de BBDD donde Spark va guardando lo que hace y por donde va.


Si al arrancar un programa de streaming, existe esa BBDD.... se toma como punto de partida lo que ahí
hubiera.
Si al arrancar un programa de streaming NO EXISTE esa BBDD... generamos un contexto nuevo para trabajar.
Esa BBDD Se genenra en un directorio

----

Cuantas IPs tiene mi host? Una por interfaz de red
Cuantas interfaces de red tiene mi maquina?
- Loopback (red virtual, interna al ordenador)  - 127.0.0.1     < localhost
- Red cableada - Ethernet                       - 172.31.45.104


---


Recepción de mensajes
    1   2   3   456 7   8   9 10 11
--------------------------------------------------------> TIEMPO
  |     5 seg      |     5 seg      |
                   ^ Inicia mi procesamiento 1 2 3 4 5 6
                   ....... libre :)
                                    ^ Inicia mi procesamiento 7 8 9 10 11
                                    .... libre :) 