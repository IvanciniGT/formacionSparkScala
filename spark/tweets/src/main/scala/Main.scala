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
    
    val rdd_tweets_originales   = contexto_de_spark.textFile("/home/ubuntu/environment/tweets.txt")
    val rdd_tweets              = rdd_tweets_originales.map(  mensaje => new Tweet(mensaje)  )

    val rdd_menciones           = rdd_tweets.flatMap( tweet => tweet.menciones )
    rdd_menciones.foreach(println)

    val rdd_hashtags           = rdd_tweets.flatMap( tweet => tweet.hashtags )
    rdd_hashtags.foreach(println)
    
    
    rdd_hashtags.map(               hashtag => (hashtag, 1)                     )
                .reduceByKey(                  _ + _                            )
                .map(               tupla   => (tupla._2,tupla._1)              )
                .sortByKey(         ascending = false                           )
                .take(              5                                           )
                .foreach(           println                                     )
     
    
    // Paso 4: Vuelco información

    // Paso 5: Cierro !
    
    contexto_de_spark.stop() //Que el programa se quede aquí frito esperando a "nada"
}
