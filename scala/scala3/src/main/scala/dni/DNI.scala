package dni

import scala.collection.immutable.HashMap

class DNI( val dni:String ):
    // Constructor
    var valido:Boolean = true
    val numero:Int = -1
    val letra:Char = 'a'
    // TODO         
    
    def esValido():Boolean =
        return valido
        
    def dameTextoOriginal():String =
        return dni
        
    def dameNumero():Int =
        return numero
    
    def dameLetra():Char =
        return letra
    
    def formatear(  ceros:Boolean = false, 
                    puntos:Boolean = false, 
                    separador:String = "", 
                    letraMayusculas:Boolean = true):String =
        // TODO
        return ""

object DNI:
    private val LETRAS_DNI: HashMap[Int, Char] = HashMap(
                                                    0 -> 'T',
                                                    1 -> 'R',
                                                    2 -> 'W',
                                                    3 -> 'A',
                                                    4 -> 'G',
                                                    5 -> 'M',
                                                    6 -> 'Y',
                                                    7 -> 'F',
                                                    8 -> 'P',
                                                    9 -> 'D',
                                                    10 -> 'X',
                                                    11 -> 'B',
                                                    12 -> 'N',
                                                    13 -> 'J',
                                                    14 -> 'Z',
                                                    15 -> 'S',
                                                    16 -> 'Q',
                                                    17 -> 'V',
                                                    18 -> 'H',
                                                    19 -> 'L',
                                                    20 -> 'C',
                                                    21 -> 'K',
                                                    22 -> 'E'
    )
    def letraDelNumero(numero:Int):Char =
        return LETRAS_DNI(numero % 23)

    def validarDNI(dni:String):(Int,Char,String) = {
        var numero_del_dni:String = ""
        var letra_del_dni:String = null      // Sin asignar.... null
        var error:String = null
        var lleva_puntos:Boolean = false
        
        for (caracter_actual <- dni.toCharArray.reverse)
            if(letra_del_dni == null)           // Estoy en el primero
                if (caracter_actual.isDigit)
                    error = "El DNI no contiene una letra de control"
                else
                    letra_del_dni = s"$caracter_actual" // YA tengo la letra
            else
                if(numero_del_dni.length == 0)  // Estoy en el segundo
                    if (caracter_actual.isDigit)  // Me lo guardo
                        numero_del_dni = s"$caracter_actual$numero_del_dni"
                    else if (caracter_actual != ' ' && caracter_actual != '-' )
                        error = "El dni contiene caracteres no admitidos"
                else                            // Estoy en el bloque del numero
                    // Si es un digito y no debe ser un punto, lo añado
                    if (caracter_actual.isDigit)  // Me lo guardo
                        if (numero_del_dni.length == 6 && lleva_puntos ) // Deberia ser un punto
                            error ="Falta el punto de los millones. Formato incorrecto del dni"
                        else
                            numero_del_dni = s"$caracter_actual$numero_del_dni" 
                    // Si es un punto, y debe ir un punto, lo ignoro
                    else if (caracter_actual == '.')
                        if (numero_del_dni.length == 3 && ! lleva_puntos)
                            lleva_puntos = true     // Buscar el punto de los millones
                        else if (numero_del_dni.length == 6 && lleva_puntos)
                            lleva_puntos = false    // Ya no busco más puntos
                        else 
                            error = "Puntos de miles y millones no colocados adecuadamente"
                    // En cualquier otro escenario, exploto
                    else 
                        error = "El dni no es válido"
        if(error != null)
            return ( -1, '-', error)
        return (numero_del_dni.toInt, letra_del_dni.toUpperCase.charAt(0), null)
        
        // La primera:
        //     la tomo como letra... a no ser que sea un numero... entonces exploto
        // La segunda 
        //  Debe ser un número --> Me lo guardo
        //  O un guión o un espacio --->paso de ese
        //. Si es otra cosa: Exploto !
        // A partir de ahí, deben ser todos dígitos....
        //  Salvo que esté en el 4º o 8º puede ser un punto
        //   De hecho, si estoy en el octavo y el cuarto fue un punto... 
        //      DEBE SER UN PUNTO
    }