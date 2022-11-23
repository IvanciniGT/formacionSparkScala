import scala.math.random
import scala.math.sqrt

class EstimadorPi(val numero_de_dardos:Int){
    
    def estimar: Double = {
        // En python equivalente a: range(1, numero_de_dardos)
        var numero_de_dardos_en_el_circulo = (1 to numero_de_dardos).map(       EstimadorPi.tirarDardo             )
                                                                    .map(       EstimadorPi.calcularDistancia      )
                                                                    .map(       EstimadorPi.estaEnCirculo          )
                                                                    .reduce(    EstimadorPi.sumar                  )
        val Pi = 4.0 * numero_de_dardos_en_el_circulo / numero_de_dardos
        return Pi
    }
}

object EstimadorPi {
    
    def tirarDardo(numeroDardo:Int) : (Double, Double) = {
        val coordenada_x:Double = random 
        val coordenada_y:Double = random 
        return (coordenada_x, coordenada_y)
    }
    
    def calcularDistancia(coordenadas: (Double, Double)): Double = {
        return sqrt( coordenadas._1*coordenadas._1 + coordenadas._2*coordenadas._2 )
    }
    
    def estaEnCirculo(distancia:Double): Int = {
        return if(distancia <= 1) 1 else 0
    }
    
    def sumar(numero1:Int , numero2:Int): Int = {
        return numero1 + numero2
    }
    
}