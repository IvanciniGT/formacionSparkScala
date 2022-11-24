import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import scala.util.Random

object Main extends App {
    //  Paso 1: Abro conexción con un cluster(en este caso lo estamos creando)
    val configuracion         = new SparkConf()
                                             // Con qué cluster me conecto?
                                             .setMaster("local[2]") // Numero de cores que puede usar de mi host
                                             // Un ID de mi programa/proceso de datos
                                             .setAppName("MiEstimadoDePi")
    val contexto_de_spark     = new SparkContext(configuracion)
      
    // Paso 2: Meter mis datos en spark: RDD o un DataFrame
    val partes = 2
    val numero_de_dnis_a_generar = 10
    val lista_de_numeros = 1 to numero_de_dnis_a_generar

    // Generando DNIS guays    
    
    contexto_de_spark.parallelize(lista_de_numeros, partes)
        .map(   numero      => Random.between(1,99999999)   )   // genero numero aleatorio entre
                                                                                    // 1 y 99999999
        .map(   numero      => s"${numero}${DNI.letraDelNumero(numero)}" )

        .map(   numero      => {
                                    println(numero)
                                    numero
                                }                       ) // No devuelve nada
        .repartition(1)
        .saveAsTextFile("/home/ubuntu/environment/dnis/guays.txt")
    
    // Generando DNIs ruina !
    
    contexto_de_spark.parallelize(lista_de_numeros, partes)
        .map(   numero      => Random.between(1,99999999)   )   // genero numero aleatorio entre
                                                                                    // 1 y 99999999
        .map(   numero      => s"${numero}X" )

        .map(   numero      => {
                                    println(numero)
                                    numero
                                }                       ) // No devuelve nada
        .repartition(1)
        .saveAsTextFile("/home/ubuntu/environment/dnis/invalidos.txt")
    
    // leer ambos ficheros y juntarlos en un RDD
    val rdd_guays = contexto_de_spark.textFile("/home/ubuntu/environment/dnis/guays.txt/part-00000")
    val rdd_ruinas = contexto_de_spark.textFile("/home/ubuntu/environment/dnis/invalidos.txt/part-00000")
    // Nos llega este conjunto de datos. Quiero saber cuales DNIs son correctos
    val rdd_completo = rdd_guays.union(rdd_ruinas)
    rdd_completo.foreach(println)
    
    // Quiero filtar los guays
    //rdd_completo.filter(    dni_como_texto  =>  new DNI(dni_como_texto).valido )
    //            .foreach(   dni_como_texto => println(s"DNI válido: ${dni}")   )
    
    //var numero_invalidos=0 // Esto no se puede montar en Spark
    // Para estos escenarios, Spark Ofrece una solución: LOS ACUMULADORES ! Accumulator de Spark
    // Qué es un accumulator: Una variable compartida entre todos las máquinas virtuales,
    //                        Junto con un mecanismo para su sincronización
    // CUIDADO: Realmente la variable solo existe en una JVM: El maestro
    //          El cliente, puede pedir el dato bueno, al maestro
    //          Los ejecutores, solo pueden modificar la variable, no consultarla
    //          Es como si fuera una variable de SOLO ESCRITURA !
    //          El único que podrá acceder al valor de esa variable es el cliente.
    var numero_invalidos = contexto_de_spark.longAccumulator("DNIs Invalidos")
    // Quiero una lista con los dnis inválidos
    val dnis_invalidos_acumulados = new DNIsAccumulator()
    contexto_de_spark.register(dnis_invalidos_acumulados, "DNIs inválidos") 

    rdd_completo.map(       dni_como_texto  =>  new DNI(dni_como_texto)         )
                .filter(    objeto_dni      =>  {
                                                    if(!objeto_dni.valido){
                                                        numero_invalidos.add(1)      
                                                        dnis_invalidos_acumulados.add(objeto_dni.dni)
                                                    } 
                                                    objeto_dni.valido
                                                }               
                                                                                )
                .foreach(   objeto_dni      =>  {
                                                    val dni_formateado = objeto_dni.formatear()
                                                    println(s"DNI válido: ${ dni_formateado }" )   
                                                }
                                                                                )
    println(s"El número total de dnis inválidos es: ${numero_invalidos.value}")
    println(s"Y son:")
    dnis_invalidos_acumulados.value.foreach(println)

    // Queramos sacar un recuento del numero de DNIs inválidos
    
    
    // Paso 4: Vuelco información

    // Paso 5: Cierro !
    // En condiciones normales, es decir, ejecutándonos sobre un cluster real, 
    // al acabar nuestro programa, cerrar la conexción que hemos abierto con el cluster
    // contexto_de_spark.close()
    
    // Pero, como nuestro programa está creando un cluster de prueba, no vamos a querer cerrarlo,
    // Sino forzar a que nuestro programa quede abierto, para que nos de tiempo a
    // entrar a la interfaz gráfica y COTILLEAR 
    
    Thread.sleep(100000000) // Que el programa se quede aquí frito esperando a "nada"
}
