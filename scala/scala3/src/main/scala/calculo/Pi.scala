package calculo // Esto nos da una forma de organizar 
                // todos los ficheros de nuestro programa

import scala.math.random
//import scala.math.pow

class Pi(val numero_de_dardos:Int): // Lo que ponemos aquí se recibe en el constructor de la clase

    def estimar: Double = 
        // Defino el número total de dardos que voy a tirar
        // val numero_de_dardos = 3000000  // En este caso usamos la palabra val por que NO ES UNA VARIABLE!
                                        // Si no UNA CONSTANTE
        // Contador del número de dardos que caen dentro del círculo
        var numero_de_dardos_en_el_circulo = 0  // En este caso usamos var, por que estamos definiendo una VARIABLE
                                                // No hemos definido tipo de datos para la variable.
                                                // Tiene tipo de datos? SI, INTEGER... se Infiere del valor que le paso
                                                // Si quisiera asignarlo explicitamente:
                                                //    var numero_de_dardos_en_el_circulo:Int = 0
                                                // Me interesa hacer eso? Depende del escenario. 
                                                // En este caso , no haría falta.
                                                // Cuándo haría falta? 
                                                // var numero_de_dardos_en_el_circulo:Int = preguntarAlUsuarioPorElNumeroMaximoDeVeces()
        // Por cada dardo que necesite tirar:   BUCLE
        var dardo_actual = 0
        for (dardo_actual <- 1 to numero_de_dardos) 
            // Generar un número aletario x
            val coordenada_x:Double = random // Da entre 0 y 1
            // Generar un número aletario y
            val coordenada_y:Double = random // Da entre 0 y 1
            // Aplicar pitagoras para calcular su distancia al centro
            val distancia_al_centro_al_cuadrado = coordenada_x*coordenada_x + coordenada_y*coordenada_y
            // En lugar de esto, podría calcular: pow(coordenada_x,2) + pow(coordenada_y,2)
            // Cual creeis que es más eficiente, desde un punto de vista computacional?
            // La primera: CON MUCHA DIFERENCIA # MICROOPTIMIZACION
            // Si la distancia es menor que 1, (conceptualmente está en el círculo): CONDICIONAL
            if (distancia_al_centro_al_cuadrado <= 1)
                // Incrementar la cuenta de dardos dentro del círculo
                numero_de_dardos_en_el_circulo += 1
        
        // Estimo Pi como: 4 x número de dardos en el círculo / número de dardos totales
        val Pi = 4.0 * numero_de_dardos_en_el_circulo / numero_de_dardos
        return Pi
        // Lo mostramos por pantalla
        //println(s"El valor estimado de Pi, habiendo tirado ${numero_de_dardos} dardos, es: ${Pi}")
