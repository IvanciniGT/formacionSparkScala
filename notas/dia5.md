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
