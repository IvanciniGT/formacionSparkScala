import calculo.EstimadorPiMapReduce
import scala.math.abs
import scala.math.sqrt

class PruebasPyMapReduce extends munit.FunSuite {
  
  test("Probar tirar un dardo") {
    val estimador_de_pi = new EstimadorPiMapReduce(3000000)
    val coordenadas:(Double, Double) = estimador_de_pi.tirarDardo(500)
    
    assert( coordenadas._1 >= 0, "La coordenada x no puede ser menor que 0!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
    assert( coordenadas._1 <= 1, "La coordenada x no puede ser mayor que 1!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
    assert( coordenadas._2 >= 0, "La coordenada y no puede ser menor que 0!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
    assert( coordenadas._2 <= 1, "La coordenada y no puede ser mayor que 1!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
  }

  test("Calcular distancia del dardo (0,0) al centro") {
    val estimador_de_pi = new EstimadorPiMapReduce(3000000)
    val coordenadas = (0.0,0.0)
    val distancia:(Double) = estimador_de_pi.calcularDistancia(coordenadas)
    
    assert( distancia == 0, "La distancia del punto (0,0) debe ser 0!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
  }
  
  test("Calcular distancia del dardo (1,0) al centro") {
    val estimador_de_pi = new EstimadorPiMapReduce(3000000)
    val coordenadas = (1.0,0.0)
    val distancia:(Double) = estimador_de_pi.calcularDistancia(coordenadas)
    
    assert( distancia == 1, "La distancia del punto (1,0) debe ser 1!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
  }
  
  test("Calcular distancia del dardo (0,1) al centro") {
    val estimador_de_pi = new EstimadorPiMapReduce(3000000)
    val coordenadas = (0.0,1.0)
    val distancia:(Double) = estimador_de_pi.calcularDistancia(coordenadas)
    
    assert( distancia == 1, "La distancia del punto (0,1) debe ser 1!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
  }
  
  test("Calcular distancia del dardo (1,1) al centro") {
    val estimador_de_pi = new EstimadorPiMapReduce(3000000)
    val coordenadas = (1.0,1.0)
    val distancia:(Double) = estimador_de_pi.calcularDistancia(coordenadas)
    
    assert( distancia == sqrt(2), "La distancia del punto (1,1) debe ser raiz(2)!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
  }
  
  test("Calcular distancia del dardo (0.5,0.5) al centro") {
    val estimador_de_pi = new EstimadorPiMapReduce(3000000)
    val coordenadas = (0.5,0.5)
    val distancia:(Double) = estimador_de_pi.calcularDistancia(coordenadas)
    
    assert( distancia == sqrt(0.5), "La distancia del punto (0.5,0.5) debe ser raiz(0.5)!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
  }
  
  test("Esta en circulo la distancia 0") {
    val estimador_de_pi = new EstimadorPiMapReduce(3000000)
    val estaEnCirculo:(Int) = estimador_de_pi.estaEnCirculo( 0.0 )
    
    assert( estaEnCirculo == 1, "La distancia 0 está en el circulo!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
  }
  
  test("Esta en circulo la distancia 0.5") {
    val estimador_de_pi = new EstimadorPiMapReduce(3000000)
    val estaEnCirculo:(Int) = estimador_de_pi.estaEnCirculo( 0.5 )
    
    assert( estaEnCirculo == 1, "La distancia 0 está en el circulo!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
  }
  
  test("Esta en circulo la distancia 1") {
    val estimador_de_pi = new EstimadorPiMapReduce(3000000)
    val estaEnCirculo:(Int) = estimador_de_pi.estaEnCirculo( 1.0 )
    
    assert( estaEnCirculo == 1, "La distancia 1 está en el circulo!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
  }
  
  test("Esta en circulo la distancia 1.3") {
    val estimador_de_pi = new EstimadorPiMapReduce(3000000)
    val estaEnCirculo:(Int) = estimador_de_pi.estaEnCirculo( 1.3 )
    
    assert( estaEnCirculo == 0, "La distancia 1.3 no está en el circulo!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
  }
  
  test("Probar el estimador de Pi") {
    val estimador_de_pi = new EstimadorPiMapReduce(3000000)
    val estimacion_pi = estimador_de_pi.estimar
    
    val valor_real_pi = 3.1416
    assert( abs(estimacion_pi - valor_real_pi) <= 0.003, "La estimación se ha ido de madre!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
  }
}
