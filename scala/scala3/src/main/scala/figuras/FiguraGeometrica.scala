package figuras
// Un trait es un tipo de datos, que solo define funciones, sin darles código
trait FiguraGeometrica:     // interfaz

    def area: Double

    def perimetro: Double
    
    def imprimirDatos: Unit