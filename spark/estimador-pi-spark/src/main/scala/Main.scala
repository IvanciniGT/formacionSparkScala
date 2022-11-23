import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Main extends App {
    //  Paso 1: Abro conexción con un cluster(en este caso lo estamos creando)
    val configuracion         = new SparkConf()
                                             // Con qué cluster me conecto?
                                             .setMaster("local[2]") // Numero de cores que puede usar de mi host
                                             // Un ID de mi programa/proceso de datos
                                             .setAppName("MiEstimadoDePi")
    val contexto_de_spark     = new SparkContext(configuracion)
      
    // Paso 2: Meter mis datos en spark: RDD o un DataFrame
    val numero_de_dardos    = 1000000
    val PARTICIONES         = 10
    val dardos_a_disparar   = contexto_de_spark.parallelize(1 to numero_de_dardos, PARTICIONES)
        // TIPO:    RDD
        // VALORES: Del 1 al 1000000
    
    // Paso 3: Jugar con mis datos... Map-Reduce
    var numero_de_dardos_en_el_circulo = dardos_a_disparar  .map(       EstimadorPi.tirarDardo             )
                                                            .map(       EstimadorPi.calcularDistancia      )
                                                            .map(       EstimadorPi.estaEnCirculo          )
                                                            .reduce(    EstimadorPi.sumar                  )
    val Pi = 4.0 * numero_de_dardos_en_el_circulo / numero_de_dardos

    // Paso 4: Vuelco información
    println(s"El valor de Pi estimado es: ${Pi}")
    
    // METODO ALTERNATIVO ////////////////////////////////////////////////////////////////////////////////
    val disparadores_de_dardos  = contexto_de_spark.parallelize(1 to PARTICIONES, PARTICIONES) 
        // TIPO:    RDD
        // VALORES: Del 1 al 10
    var suma_de_pis             = disparadores_de_dardos.map(  disparador =>    new EstimadorPi(numero_de_dardos/PARTICIONES )      )
                                                        .map(  estimador  =>    estimador.estimar )   
                                                        .reduce( _ + _ )
    val Pi2 = suma_de_pis / PARTICIONES
    println(s"El valor de Pi estimado es: ${Pi2}")

    // FIN METODO ALTERNATIVO ////////////////////////////////////////////////////////////////////////////

    // Paso 5: Cierro !
    // En condiciones normales, es decir, ejecutándonos sobre un cluster real, 
    // al acabar nuestro programa, cerrar la conexción que hemos abierto con el cluster
    // contexto_de_spark.close()
    
    // Pero, como nuestro programa está creando un cluster de prueba, no vamos a querer cerrarlo,
    // Sino forzar a que nuestro programa quede abierto, para que nos de tiempo a
    // entrar a la interfaz gráfica y COTILLEAR 
    
    Thread.sleep(100000000) // Que el programa se quede aquí frito esperando a "nada"
}

