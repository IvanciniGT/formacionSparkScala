Imaginad una tabla de datos: 100 datos              EXCEL
                             200.000 datos          ACCESS
                             1.000.000 datos        MySQL/MariaDB/PostgreSQL
                             8.000.000 datos        SQL Server
                             80.000.000 datos       Oracle DB
                             8.000.000.000.000



# Bigdata

- Lidiar con conjuntos de datos:
    - muy grandes volumenes de datos
    - que se generan a gran velocidad
    - muy complejos

Qué quiero hacer con los datos?
    - Almacenarlos
    - Transmitirlos
    - Analizarlos

NOTA: CUANTO DE GRANDES? Como para no poder aplicar técnicas tradicionales.

Hasta este momento, cuando necesitabamos más potencia de procesamiento de información, usábamos máquinas más potentes.
Esto surge con GOOGLE: BigTable -> Apache HADOOP

# Apache Hadoop

- Implementación de MapReduce
    - Aprovechar la potencia de calculo de diferentes nodos
- Distribución de archivos/datos
    - Sistema para almacenamiento distribuido y masivo de datos: HDFS: Hadoop FileSystem
        - Volumenes de almacenamiento enormes
        - Velocidad de lectura y escritura de la infroamción 150 Mbs/seg, 600, 2000
        - Replicación: 1 datos, no lo guardo solo en 1 HDD. Estándar en un entorno de producción: 3x
    - Facilita el acceso a datos que tenemos almacenados de forma distribuida

# Apache Spark

- Framework para procesamiento masivo de datos
    - Librerias que nos permiten usar esa implementación MAP-REDUCE:
        - Java
        - Python
        - Scala <<<<<<<<

    if {            SCALA 2
        
    }
    
    if :            SCALA 3 = Fuerte influencia de python


- Basado en Hadoop
    Mejora sobre la implemetación MAP-REDUCE que ofrece Apache Hadoop, trabajando solo en RAM

# Por qué scala?

## Java

- Lenguaje compilado: Cierto... pero no sólo    |
- Lenguaje interpetado                          | Es los 2 a la vez

                                                    JVM
                javac                             Java Virtual Machine
    .java -> compilación -> .class              + Interprete => Lenguaje si ejecuta el por el SO de una computadora
                v            (bytecode)
                v            Ningún SO entiende este lenguaje
                detectar potenciales problemas del código antes de su ejecución

- Lenguaje de tipado estático

Esas 2 características son utltradeseadas en un entorno de producción. En ese sentido JAVA es guay.
Pero... es asquerosamente laborioso escribir código JAVA . LA SINTAXIS DE JAVA está muy mal diseñada. Tiene muchos fallos de diseño.

## Python

- Lenguaje interpretado: Al ejecutarse necesita un intérprete: cython, pypy, jython
- De tipado dinámico

Estas dos cosas, especialmente JUNTAS, convierten a python en un lenguaje muy peligroso, y no deseado en entornos de producción.
Aunque por otro lado, es un lenguaje muy sencillo de aprender y manejar (para personas con bajos conocimientos de desarrollo de software)
, especialmente para programitas sencillos.
---

## De tipado estático / tipado dinámico

### Variable?

Una variable es una referencia a un dato que tengo guardado en la memoria RAM.

Imaginad la memoria RAM como un cuaderno de Cuadricula. Quien la gobierna? SO
Una variable me ayuda a referirme a algo que tengo apuntado en la memoria RAM: Postit

- Un espacio en memoria preparado para almacenar algo. NOP !
- Donde se almacenan los datos. NOP ! Eso es la memoria RAM !

    var numero = 7      (3 cosas)
    
        1:  Coloca el 7 en la memoria RAM   El equivalente a abrir el cuaderno de cuadricula y escribir en algún sitio X
                                            un "7"
        2:  Definir la variable "numero"    El equivalente a sacar un postit del taco, y escribir en él la palabra "numero"
        3:  Asignar la variable al dato     El equivalente a pegar el postit al lado del 7 en el cuaderno
        
    numero = 14         (2 cosas)
        1:  coloca el 14 en la RAM... dónde? en el mismo sitio donde estaba el 7? NO, en otro lado
            Llegados a este punto: cuantas cosas tengo en RAM? 2, El 7 y el 14
        2:  Asigna la variable al nuevo dato.... Muevo el postit, Vario su posición en el cuaderno (variable)
        
        NOTA: El 7.... Es recuperable, si no tiene positit pegao al lado? 
            Depende del lenguaje de programación: 
                C: Permite trabajar con direcciones de memoria: SI
                JAVA, PYTHON: NO -> Es BASURA ! Garbage Collector
            
            Java y Python hacen un uso extraordinamiente INEFICIENTE de la memoria RAM... Por criterio de decisión.
                C Hace un uso exquisito de la memoria RAM... ultraeficiente... SUPERCOMPLEJO DE PROGRAMAR
    
    Los datos que guardo en la RAM, serán de un tipo concreto: NUMERO ENTERO, DECIMAL, VALOR LOGICO, TEXTO
        Al final en la RAM, lo que guardamos son bytes.
        Cómo interpreta la computadora una secuencia de 0 y 1 (magnetizado/no magnetizado) que tengo guardado.
            En base al tipo:
                00010011 11000100 01010101 11110000 4 bytes: Int:   127362347
                                                             Texto: ä
    LENGUAJE DE TIPADO DINAMICO                                   
        Hay lenguajes que permiten que una variable referencia a un dato de un tipo A , y posterioremente a un dato de tipo B.
            variable1 = 4
            variable1 = "Hola"
    LENGUAJE DE TIPADO ESTATICO
        Hay lenguajes que no.
            variable1 = 4
            variable1 = 3
            variable1 = "Hola"  ! OSTION !!!! ERROR NO ES POSIBLE . 
                                Variable 1 solo puede referenciar a números enteros

NOTA: OJO con respecto a la definición de tipos de datos:
LENGUAJE DE TIPADO ESTATICO
    - Explicita:                        JAVA
                                                int numero = 7;
                                                numero = "Hola"                 EXPLOSION
    - Implicita:                        SCALA
                                                var numero:Int = 7;             // Scala explicitamente coge el tipo: ENTERO
                                                var numero = 7;                 // Scala infiere el tipo: ENTERO
                                                numero = "HOLA"                 EXPLOSION
LENGUAJE DE TIPADO DINAMICO
    - No tener definición de tipos:     PYTHON
                                                numero = 7   
                                                numero = "Hola"                 √
---

## Interpretados vs compilados

Entienden las computadoras de JAVA o PYTHON, o C, o SCALA? NADA
- Sistema operativo, que controla el HW
    - cada SO, habla su propio lenguaje.

        Programa (un lenguaje más o menos accesible para mi, como ser humano) -> Llevar ésto a un lenguaje que comprenda el SO
            
            Pretraducción del programa.
                CODIGO DE MI PROGRAMA -> compilación/Ensamblado -> CODIGO compilado (lenguaje del SO)
                                                                     v
                                                                     SO -> Ejecute las instrucciones que hay definidas en ese cídogo
                                                                     + Intérprete
                CODIGO DE MI PROGRAMA --------------------------->  En tiempo real, traduce las expresiones al idioma del SO
    
    Ventajas de la compilación: 
        - Velocidad en la ejecución
        - Al hacer la compilación: Aprovecho para revisar todo bien.
        - Follón, ya que necesito compilar contra cualquier SO destinatario de mi programa
    Ventajas de la interpretación: 
        - Yo solo gestiono una copia de mi programa, que mando a todo el mundo
        
        
        
HADOOP:                 Es el equivalente a un SO en el mundo Bigdata:
                        Me permite, controlar la RAM, CPU, HDD de un monton de computadoras en red
    Spark - scala
    Storm
    
---

Los fallos de diseño de la sintaxis de JAVA han dado lugar a una serie de lenguajes (sintaxis) alternativas a la tradicional de JAVA
que podemos usar para montar aplciaciones JAVA:
- Kotlin (montar app en telefonos Android)
- Scala  (el mundo bigdata)

    .scala ---> .class  ----> Se ejecuta a través de la JVM
            scalac      scala
En scala ha habido un cambio de paradigma muy grande entre la versión 2 y la [3] ESTA ULTIMA ES LA QUE USAMOS A DIA DE HOY

----

En Spark, Cuando se aplica un proceso de datos:

    Datos ---> Proc 1 ----> Proc 2 ----> Proc 3
        
        Particiona el conjunto de datos de entrada
        1       Cada nodo del cluster realiza todas las transformaciones sobre una parte de los datos
        2
        3
        ...
        n
        
            Maquina 1 : datos - partición 1 ---> Proc 1 ----> Proc 2 ----> Proc 3
            Maquina 2 : datos - partición 2 ---> Proc 1 ----> Proc 2 ----> Proc 3

En Storm trabajamos de otra forma

    Datos ---> Proc 1 ----> Proc 2 ----> Proc 3
               Maquina 1    Maquina 2    Maquina 3
               
----

A la hora de escribir un programa, usaremos un lenguaje. Los lenguajes los podemos usar de varias formas, incluso el castellano.
En el mundo de la programación, esas formas en las que puedo usar un lenguaje las llamamos paradigmas de programación:
- Imperativo: Escribir en mi coódigo una secuencia de ordenes que se ejecutan en ORDEN.
                if, else, for, while
- Orientación a Objetos
                class, private, INTERFAZ, OBJETO, HERENCIA, POLIMORFISMO, SOBREESCRITURA, SOBRECARGA
- Programación procedural
                La habilidad que nos proporciona un lenguaje para generar e invocar métodos, procedimientos, rutinas, funciones...
                Esto es muy cómodo: Me permite escribir una porción de código que es reutilizada a futuro
- Programación funcional        **********
                Cuando el lenguaje me permite que una variable apunte (referencia) a una función.
                Una función es un dato más que tengo en RAM.
                El concepto es simple. El impacto gigante:
                    - Desde el momento en que puedo asignar una variable a una función: 
                        - Podemos pasar a una función otra función como un argumento
                        - Desde una función puedo devolver otra función.

TODO EL MUNDO MAP REDUCE SE BASA EN PROGRAMACION FUNCIONAL

# Map ---> reduce

CONJUNTO DE DATOS
    Dato 1          ----> Función de transformación
    Dato 2
    Dato 3
    ....
    Dato n

Teoria de conjuntos en matemáticas

                        MAP conjunto de entrada de n valores, obtengo un conjunto de saliuda de n valores
CONJUNTO DE DATOS:                              CONJUNTO DE DATOS 
                                                (cada valor es el resultado de aplicar la función a cada dato del conjunto inicial)
    1                                               2
    2                                               4
    3               ---> Función doblar --->        6
    4                                               8
    5                                              10

                        FILTER conjunto de entrada de n valores, obtengo un conjunto de salida de m valores, siendo m<=n
CONJUNTO DE DATOS:                              CONJUNTO DE DATOS 
                                                (cada valor es el dato correspondiente del conjunto inicial, si la función de filtro devuelve true)
    1                                               1
    2                                               3
    3               ---> Función esImpar -->        5
    4                                               
    5                                              

                        FLATMAP conjunto de entrada de n valores, obtengo un conjunto de salida de k valores, con k>=0
CONJUNTO DE DATOS:                              CONJUNTO DE DATOS 
                                                (para cada valor del conjunto de datos de entrada obtenemos m valores con m>=0, que se añaden al conjunto de salida)
    1                                               1
    2                                               1
    3               ---> Función factores ->        2
    4                                               1
    5                                               3
                                                    1
                                                    2
                                                    2
                                                    1
                                                    5

Las funciones de tipo map, se ejecutan en modo LAZY
    (diferido en el tiempo, cuando es necesario, antes no)
Este concepto es BASICO (imprescindible), en cuanto a funcionaldiad para poder 
    implementar una arquitectura map-reduce con hadoop
    

Programa en Scala / Java / Python sobre Spark.

Sea un conjunto de datos, que se sacan de tal sitio.....
Quiero aplicar sobre ese conjunto de datos:         <<<<< PROCEDIMIENTO QUE QUIERO APLICAR SOBRE MIS DATOS
    - Una función de mapeo básica
    - Una función de filtrado
    - Una función de tipo flatmap
    - Una map más 
... Y por último, haremos algo con el resultado:
    - Mandar el resultado a una BBDD
    - Guardarlo en un fichero
    - ...

Donde se ejecuta este programa? 
             en mi computadora? NO
             en el cluster de Spark. En qué nodo? En todos los que sean designados

Misión de Spark será:
    Partiendo de la definición que nosotros hemos dado:
        Dividir el conjunto de datos inicial en varias partes
        Mandar ese conjunto de datos a distintas máquinas
        En cada una aplciar ese procesamiento
    Para eso, Spark necesita ENVIAR a cada una de las máquinas, ese PROCEDIMIENTO
    Ese PROCEDIMIENTO, tal y como lo hemos definido, qué es?
        Una serie de funciones de transformación (maps)
    
    Esas funciones tienen que viajar por la red, desde una máquina a otra!
        Para esto necesito el concepto de Programación funcional!
    
Además, yo voy a ir definiendo muchas transformaciones (muchos maps)
Quizás, lo más eficiente no es ir ejecutando uno, y luego el otro, y luego el otro... y asi sucesivamente.

Quizás lo más eficiente puede ser, sobre cada dato, aplicar todas sus transformaciones... y luego a por otro dato.

Entrada:
    1   x2      x3      +5      Sacar los fatores       Quedarme con los impares sin repetición
    2
    3

Datos:  x2  x3  +5  Factores    Quedarme con los impares sin repetición
    1   2   6   11      1           1
                        11          11
    2   4   12  17                  17
                        1           23
                        17
    3   6   18  23
                        1
                        23

Datos:  x2 x3 +5    Factores
    1       11          1   
                        11
    2       17          1
                        17
    3       23          1
                        23

Al jugar con SPARK, él va a decidir la mejor forma (la más optima) de aplicar esas transformaciones.
Las transformaciones no se aplcian según las decidimos (se evaluan en modo lazy)... Y entre que se definen
    y que se evaluan, Spark tiene tiempo para decidir cómo aplicarlas...

Las funciones REDUCE, son las que fuerzan que las transformaciones (MAP) se ejecuten.
Funciones de ejemplo REDUCE:
    contar
    show


Las funciones REDUCE "habitualmente" devuelven un valor, o unos pocos valores, aunque no siempre.

Versiones de un software:
Scala 3. 2. 1               Inspirada en la sintaxis de python
    if :
        SANGRADO BIEN ESTRUCTURADO
    * NOTA: Se pueden seguir usando las llaves, pero si la indentación no es adecuada, se genera un warning
Scala 2.13.10               Inspirada en la sintaxis de JAVA
    if {}

      ^ MAYOR
        ^ MINOR
            ^ MICRO

Aumentamos micro cuando resolvemos bugs
Aumentamos Minor, cuando añadimos funcionalidad
Aumentamos Mayor, cuando hay cambios de diseño importantes, 
    que pueden implicar incluso NO COMPATIBILIDAD HACIA ATRAS

Python 2.7      print texto
       3.11     print(texto)
       
java < - MAVEN          GRADLE (kotlin)         SBT (scala)
        compilar código java
        gestionar dependencias
        Ejecutar test

python < -  pip - Gestión de dependencias
            poetry