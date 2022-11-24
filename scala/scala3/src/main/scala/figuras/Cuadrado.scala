package figuras

class Cuadrado (val lado: Double):

    def area: Double =
        return this.lado * this.lado

    def perimetro: Double =
        return 4 * this.lado
    
    def imprimirDatos: Unit =
        println(s"Datos del cuadrado de lado: ${lado}")
        println(s"  perimetro: ${perimetro}")
        println(s"  area: ${area}")
