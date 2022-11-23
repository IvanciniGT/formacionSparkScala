package dni

import scala.collection.immutable.HashMap

class DNI( val dni:String ):
    // Constructor
    val(numero, letra, error):(Int, Char, String)=DNI.validarDNI(dni)
    val valido:Boolean = (error == null)
    // TODO         
    
    //def esValido():Boolean =
    //    return valido
        
    //def dameTextoOriginal():String =
    //    return dni
        
    //def dameNumero():Int =
    //    return numero
    
    //def dameLetra():Char =
    //    return letra
    
    def formatear(  ceros:Boolean = false, 
                    puntos:Boolean = false, 
                    separador:String = "", 
                    letraMayusculas:Boolean = true):String =
        // Procesamos separador y letra
        var dni_formateado : String =s"${separador}${letra}"
        if(!letraMayusculas)
            dni_formateado = dni_formateado.toLowerCase()
        // Procesamos parte numérica... con sus ceros y sus puntos
        var parte_numerica:String = s"$numero"
        if (ceros)
            parte_numerica= s"0000000$numero"
                            // 0000000123456 . Longitud: 13-8  = 5
                            // 01234________
            parte_numerica=parte_numerica.substring(parte_numerica.length-8)
        
        val longitud = parte_numerica.length
        if(puntos && longitud>3)
            if(longitud>6) // 2300000
                val unidades = parte_numerica.substring(longitud-3)
                val miles = parte_numerica.substring(longitud-6,longitud-3)
                val millones = parte_numerica.substring(0,longitud-6)
                parte_numerica = s"$millones.$miles.$unidades"
            else                        // 23000
                val unidades = parte_numerica.substring(longitud-3)
                val miles = parte_numerica.substring(0,longitud-3)
                parte_numerica = s"$miles.$unidades"
        // Ensamblo y ya estoy !
        dni_formateado= s"${parte_numerica}${dni_formateado}"
        return dni_formateado

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
            // La primera:
            //     la tomo como letra... a no ser que sea un numero... entonces exploto
            if(letra_del_dni == null)           // Estoy en el primero
                if (caracter_actual.isDigit)
                    error = "El DNI no contiene una letra de control"
                else
                    letra_del_dni = s"$caracter_actual" // YA tengo la letra
            else
                // La segunda 
                //  Debe ser un número --> Me lo guardo
                //  O un guión o un espacio --->paso de ese
                //. Si es otra cosa: Exploto !
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
        
        // TODO: Puede ser que llegue a este punto del programa sin letra ni numero
        val numero=numero_del_dni.toInt
        val letra=letra_del_dni.toUpperCase.charAt(0)
        
        if(letra != letraDelNumero(numero))
            return ( -1, '-', "Letra inválida")
            
        return (numero, letra, null)
        
    }