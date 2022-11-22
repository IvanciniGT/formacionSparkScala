import calculo.EstimadorPiMapReduceVersion2
import scala.math.abs

class PruebasPyMapReduceVersion2 extends munit.FunSuite {
  
  test("Probar el estimador de Pi") {
    val estimador_de_pi = new EstimadorPiMapReduceVersion2(3000000)
    val estimacion_pi = estimador_de_pi.estimar
    
    val valor_real_pi = 3.1416
    assert( abs(estimacion_pi - valor_real_pi) <= 0.003, "La estimación se ha ido de madre!" ) // ASERCIÓN:   REGLA QUE ASEGURE ALGO
  }
}
