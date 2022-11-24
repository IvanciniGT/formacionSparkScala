package figuras

object Figuras :
    def main(arg: Array[String]): Unit =
        
        println("HOLA, soy el programa de las figuras") 

        val cuadrado1=new Cuadrado(5)

        val rectangulo1=new Rectangulo(5,2)

        val circulo1=new Circulo(5)
        val hexagono1=new Hexagono(5)

        val figuras: List[FiguraGeometrica] = 
            List(rectangulo1,cuadrado1,circulo1, hexagono1)
        figuras.foreach( figura => figura.imprimirDatos )