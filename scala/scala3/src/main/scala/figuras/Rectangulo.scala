package figuras

class Rectangulo (val base: Double,val altura: Double) extends Poligono( 4 ):

    def perimetro: Double =
        return 2 * base + 2 * altura
    
    def area: Double =
        return base * altura

    override def imprimirDatos: Unit =
        println(s"Datos del rectangulo de base: ${base} y altura: ${altura}")
        super.imprimirDatos