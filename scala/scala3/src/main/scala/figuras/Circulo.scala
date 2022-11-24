package figuras
import scala.math.Pi

class Circulo (val radio: Double) extends FiguraGeometrica:

    def area: Double =
        return Pi * radio * radio

    def perimetro: Double =
        return 2 * Pi * radio
    
    def imprimirDatos: Unit =
        println(s"Datos del circulo de radio: ${radio}")
        println(s"  perimetro: ${perimetro}")
        println(s"  area: ${area}")
