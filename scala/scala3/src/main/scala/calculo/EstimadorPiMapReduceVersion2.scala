package calculo // Esto nos da una forma de organizar 
                // todos los ficheros de nuestro programa
import scala.math.random
import scala.math.sqrt

class EstimadorPiMapReduceVersion2(val numero_de_dardos:Int): // Lo que ponemos aquí se recibe en el constructor de la clase

    def calcularDistancia(coordenadas: (Double, Double)): Double =
        return sqrt( coordenadas._1*coordenadas._1 + coordenadas._2*coordenadas._2 )
        
    def estaEnCirculo(distancia:Double): Int =
        return if(distancia <= 1) 1 else 0

    def estimar: Double = 
        var numero_de_dardos_en_el_circulo = (1 to numero_de_dardos)
            .map(       numeroDardo =>          (random, random)        )
            .map(       coordenadas =>          calcularDistancia       )
            .map(       distancia   =>          estaEnCirculo           )
            //.reduce(    (estaEnCirculo1 , estaEnCirculo2)  => estaEnCirculo1 + estaEnCirculo2  )
            //.reduce ( (dato1, dato2 ) => dato1 + dato2)
            .reduce (                      _   +   _  )
        
        val Pi = 4.0 * numero_de_dardos_en_el_circulo / numero_de_dardos
        return Pi
