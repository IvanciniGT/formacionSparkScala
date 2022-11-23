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
    
    val dnis: List[String] = List(  "23000T",
                                    "2300000T",
                                    "230001T",
                                    "23000t",
                                    "2300000 T",
                                    "230.001T",
                                    "00230.001T",
                                    "00.230.001T")
    
    println(dnis)
    
    var resultado = dnis
                        .map(       dni_como_texto  => new DNI(dni_como_texto)                          )
                        .filter(    dni             => dni.valido                                       )
                        //.map(       dni_valido      => dni_valido.formatear(    ceros = false, 
                        //                                                        puntos = true, 
                        //                                                        separador= "", 
                        //                                                        letraMayusculas = true) )
                        .map(       dni_valido      => dni_valido.numero                                 )
                        .map(       numero          => 
                                                        val letra = DNI.letraDelNumero(numero)
                                                        s"$numero$letra"                                 )
                        
                        .toList
    println(resultado)
    
    var invalidos = dnis
                        .map(       dni_como_texto  => new DNI(dni_como_texto)                          )
                        .filter(    dni             => !dni.valido                                      )
                        .map(       dni_invalido    => 
                                                        val dni_texto   =   dni_invalido.dni
                                                        val error       =   dni_invalido.error
                                                        s"$dni_texto: $error"                           )
                        .toList
    println(invalidos)