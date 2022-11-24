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
    val rdd = contexto_de_spark.parallelize(lista_de_numeros, partes)
    // Paso 3: Jugar con mis datos... Map-Reduce
    rdd .map(   numero      => Random.between(1,99999999)   )   // genero numero aleatorio entre
                                                                                    // 1 y 99999999
        .map(   numero       => s"${numero}${DNI.letraDelNumero(numero)}" )
        //.foreach(   println     )
        .repartition(1)
        .saveAsTextFile("/home/ubuntu/environment/dnis.txt")
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

