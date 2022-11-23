import scala.math.random
import scala.math.sqrt

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