package figuras

abstract class Poligono ( val numero_de_lados: Int ) extends FiguraGeometrica:

    // Hay funciones que aqui deberia implementar
    // pero no les pudo dar codigo.. lo desconozco
    // def area
    // def perimetro

    override def imprimirDatos: Unit =
        println(s"  lados: ${numero_de_lados}")
        println(s"  perimetro: ${perimetro}")
        println(s"  area: ${area}")