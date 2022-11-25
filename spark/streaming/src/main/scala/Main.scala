import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds

object Main{
    
    val CHECK_POINT_DIR = "/home/ubuntu/environment/checkpoint"
    
    def main(args: Array[String]) {
        // Paso 1: Generar un contexto de streaming de spark
        val contexto_de_streaming =  StreamingContext.getOrCreate(CHECK_POINT_DIR, generarContextoDeStreaming )
        // Paso 2: Iniciar la ejecución de nuestra aplicación (en ese contexto de streaming)
        contexto_de_streaming.start()
        // Paso 3: Dejar nuestro programa en espera de que acabe el streaming
        contexto_de_streaming.awaitTermination() // Ocurrirá si hay un error, o alguien solicita su detención es Spark
    }

    def generarContextoDeStreaming(): StreamingContext = {
        println("****************************************************")
        println("****************************************************")
        println("   ATENCION: Usando nuevo contexto de streaming.    ")
        println("****************************************************")
        println("****************************************************")
        // Creamos un contexto de spark streaming
        val configuracion           = new SparkConf()
                                                 .setMaster("local[2]")     // Numero de cores que puede usar de mi host
                                                 .setAppName("Procesar datos")
        val contexto_de_streaming   = new StreamingContext(configuracion, Seconds(30))   // Cada cuanto tiempo, quiero que se ejecuten
                                                                                        // Las operaciones que voy a configurar
        contexto_de_streaming.checkpoint(CHECK_POINT_DIR)
        
        // De donde voy a estar leyendo la información de entrada
        val dstream_mensajes_recibidos = contexto_de_streaming.socketTextStream( "localhost" , 11111) // Aqui podriamos configurar si los mensajes que se reciben se guardan en disco, memoria o ambos
        // Configurar las operaciones que quiero que se hagan sobre los datos de entrada: TODO
        // Este dstream, lo configuro exactamente igual que un rdd
        dstream_mensajes_recibidos  .flatMap(           mensaje => mensaje.split(" ")   )     // Del mensaje saco las palabras
                                    .map(               palabra => (palabra, 1)         )
                                    .reduceByKey(                  _ + _                )
                                    .print()
        // Devolverlo
        return contexto_de_streaming
    }
    
    
}
