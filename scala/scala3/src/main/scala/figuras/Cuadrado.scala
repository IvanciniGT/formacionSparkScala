package figuras

class Cuadrado (val lado: Double) extends Rectangulo(lado, lado) :
    // No necesito definir estas funciones porque las hereda de rectangulo
    //def area: Double =
    //    return lado * lado
    //def perimetro: Double =
    //    return 4 * lado
    
    override def imprimirDatos: Unit =          // La sobreescribo
        println(s"Datos del cuadrado de lado: ${lado}")
        println(s"  lados: ${numero_de_lados}")
        println(s"  perimetro: ${perimetro}")
        println(s"  area: ${area}")