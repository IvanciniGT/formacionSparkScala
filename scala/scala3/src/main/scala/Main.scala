// Scala version 3

import calculo.EstimadorPi // La clase Pi está dentro del paquete calculo
import dni.DNI // La clase Pi está dentro del paquete calculo

@main def hello: Unit = 
    val estimador_de_pi = new EstimadorPi(3000000)
    val estimacion_pi_1 = estimador_de_pi.estimar
    val estimacion_pi_2 = estimador_de_pi.estimar
    val estimacion_pi_3 = estimador_de_pi.estimar
    // Lo mostramos por pantalla
    println(s"El valor estimado de Pi es: ${estimacion_pi_1}")
    println(s"El valor estimado de Pi es: ${estimacion_pi_2}")
    println(s"El valor estimado de Pi es: ${estimacion_pi_3}")
    
    val dnis: List[String] = List( "23000T",
    "2300000T",
    "230001T",
    "23000t",
    "2300000 T",
    "230.001T",
    "00230.001T",
    "00.230.001T")
    
    Dejar un listado con los DNIS válidos formateados:
    - Sin separador, con puntos y sin ceros, letra mayusscula
    