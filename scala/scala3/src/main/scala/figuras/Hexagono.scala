package figuras
import scala.math.sqrt

class Hexagono (val lado: Double) extends Poligono( 6 ):

    def perimetro: Double =
        return 6 * lado
    
    def area: Double =
        return 3 * sqrt(3) * lado * lado / 2

    override def imprimirDatos: Unit =        
        println(s"Datos del hexágono de lado: ${lado}")
        super.imprimirDatos