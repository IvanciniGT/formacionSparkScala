import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import scala.util.Random
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.functions.{col, lit, sum, count}

object Main extends App {
    //  Paso 1: Abro conexción con un cluster(en este caso lo estamos creando)
    /*
    val configuracion         = new SparkConf()
                                             // Con qué cluster me conecto?
                                             .setMaster("local[2]") // Numero de cores que puede usar de mi host
                                             // Un ID de mi programa/proceso de datos
                                             .setAppName("MiEstimadoDePi")
    val contexto_de_spark     = new SparkContext(configuracion)
    val contexto_sql    = new SQLContext(contexto_de_spark)
    */
    val contexto_sql = SparkSession.builder
                                    .master("local[2]")
                                    .appName("Tweets")
                                    .getOrCreate()
    val contexto_de_spark = contexto_sql.sparkContext
    
    import contexto_sql.implicits._                         
    
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
    // Para trabajar con SparkSQL tenemos 2 opciones.
    // Usaremos cualquiera de las 2 para generar un ContextoSQL de Spark
    // Si abro una conexión con Spark, a través de un SparkSession, ya puedo trabajar con SQL en autom...
    //      Lo que no puedo es trabajar con RDDs... del SparkSession tendré que sacar el SparkContext
    // Si abro una conexión con Spark, a través de un SparkContext, no puedo trabajar con SQL auto....
    //      Pero si con RDDs.
    //      Para trabajar con SQL y DataFrames, es necesio generar un SQLContext desde el SparkContext
    
    /// Me permite usar autom. las funciones de conversión de rdd-> dataframe
    // Basicamente la función toDF()
    
    // Filtro: filas o columnas
    // Columnas calculadas de forma sencilla
    // Agrupaciones
    // Ordenaciones
    // Joins
    val dataframe_hashtags  = rdd_hashtags.toDF()
    dataframe_hashtags.show()
    dataframe_hashtags.withColumn("cantidad", lit( 1 ) )
                      .groupBy(col("value"))
                      .agg(sum(col("cantidad")))
                      .show()

    dataframe_hashtags.groupBy(col("value"))
                      .agg(count(col("value")))
                      .orderBy(col("count(value)").desc)
                      .select(col("value"))
                      .show(5)    
    
    dataframe_hashtags.createOrReplaceTempView("hashtags")
    contexto_sql.sql("SELECT * FROM hashtags").show()
    
    //dataframe_hashtags.select(col("value")).show()
    
    //.groupBy(department.col("name"), people.col("gender"))
    //.agg(avg(people.col("salary")), max(people.col("age")));


    
    
    // Paso 4: Vuelco información

    // Paso 5: Cierro !
    
    contexto_de_spark.stop() //Que el programa se quede aquí frito esperando a "nada"
}
