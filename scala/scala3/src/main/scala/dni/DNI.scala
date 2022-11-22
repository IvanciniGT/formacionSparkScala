import scala.collection.immutable.HashMap

class DNI( val dni:String ):
    // Constructor
    var valido:Boolean = true
    val numero:Int = -1
    val letra:Char
    // TODO
    
    def esValido():Boolean =
        return valido
        
    def dameTextoOriginal():Boolean =
        return dni
        
    def dameNumero():Int =
        return numero
    
    def dameLetra():Char =
        return letra
    
    def formatear(ceros:Boolean, puntos:Boolean, Separador:String = ""):String =
        // TODO

object DNI:
    private val LETRAS_DNI: HashMap[Int, Char] = HashMap(
                                                    0 -> 'T'
                                                    1 -> 'R'
                                                    2 -> 'W'
                                                    3 -> 'A'
                                                    4 -> 'G'
                                                    5 -> 'M'
                                                    6 -> 'Y'
                                                    7 -> 'F'
                                                    8 -> 'P'
                                                    9 -> 'D'
                                                    10 -> 'X'
                                                    11 -> 'B'
                                                    12 -> 'N'
                                                    13 -> 'J'
                                                    14 -> 'Z'
                                                    15 -> 'S'
                                                    16 -> 'Q'
                                                    17 -> 'V'
                                                    18 -> 'H'
                                                    19 -> 'L'
                                                    20 -> 'C'
                                                    21 -> 'K'
                                                    22 -> 'E'
    )
    def letraDelNumero(numero:Int):Char =
        return LETRAS_DNI(numero % 23)
