import org.apache.spark.util.AccumulatorV2
import scala.collection.mutable.ListBuffer
                                            // Trabajar con Genericos
                                            // Indicando con qué TIPOS DE DATOS TRABAJO
                                            // Dar info a spark de los tipos de datos que manejamos
class DNIsAccumulator(var lista_dnis: ListBuffer[String] = new ListBuffer()) extends AccumulatorV2[String, List[String]] {
                                            // voy a acumular String
                                                    // Voy a devolver listas de string
    // Me creo mi lista de strings donde guardar mis DNIs
    
    def add(un_dni: String): Unit = {
        lista_dnis += un_dni
    }
    
    def copy(): AccumulatorV2[String, List[String]] = {
        return new DNIsAccumulator(lista_dnis.clone)
    }
    
    def isZero: Boolean = {
        return lista_dnis.isEmpty
    }
    
    def merge(mas_dnis: AccumulatorV2[String, List[String]]): Unit = {
        mas_dnis.value.foreach( un_dni => lista_dnis += un_dni )
    }
    
    def reset(): Unit = {
        lista_dnis.clear
    }
    
    def value: List[String] = {
        return lista_dnis.toList
    }
}
