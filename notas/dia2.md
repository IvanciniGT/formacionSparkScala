En scala, usamos conceptos de programación orienta a objetos:

# Clases

Podemos crear nuestros propios tipos de datos, con sus propias funciones

## Object (singleton)

Un tipo especial de clase... del que solo podía obtener/crear una única instancia(objecto, dato concreto de un tipo)


---

Programa 1, vamos a montar un programa que estime / calcule PI
                            
                            y
                            ^
                            |
                  +---------1---------+
                  |         |       x |
                  |         |         |
                  |         |  x      |
                  |         |         |
            ---- -1---------+----------1------> x
                  |     x x |         |
                  |         |    x    |
                  |         |         |
                  | x       |         |
                  +------- -1---------+
                            |
                            |
Imaginad que ese recuadro es una diana... Y tiro en ella dardos al azar..... 1.000.000
Imnaginad que cuento cúantos de esos dados han caido dentro de un circulo de radio 1, centrado en (0,0)....
Si tirase infinitos dardos al azar... Cúantos habrían caído dentro?
    Área circulo: Pi x R^2      = Pi 
    Área Cuadrado: Lado x Lado. = 4

Dentro del circulo habrán caído: Pi/4 dardos
    4 x dardos en el círculo       
    ------------------------   = Pi
        1.000.000           

Para tirar un dardo, calculo 2 números al azar entre -1 y 1
serán la coordenada x e y donde impactó el dardo.

    NOTA: REALMENTE solo necesito calcular x e y dentro del intervalo [0,1]

Cómo sé si un dardo cayó dentro del círculo?
    Pitagoras: x^2 + y*2 <= 1
        Si se cumple eso, es que el dardo está dentro del círculo

---

# Pruebas de software

- Estáticas: No requieren poner el código en funcionamiento
             La que antes hacía un desarrollador SENIOR.
             Se centra en la calidad del código.
             Esto en la mayor parte de las compañías está AUTOMATIZADO: SONARQUBE
- Dinámicas: Si requieren poner el código en funcionamiento
    - Pruebas funcionales: Ver si aquello funciona
                            OBJETO DE PRUEBA       
        - Unitarias         Un componente "aislado" del sistema (metodo, clase)
                Para meter pruebas unitarias usamos frameworks de pruebas unitarias. 
        - Integración       Comunicación entre 2 componentes
        - Sistema           Comportamiento del sistema en su conjunto
            - Aceptación
    - Pruebas no funcionales:
        - Pruebas de rendimiento

---

# Estimación PI Map Reduce

1 -> tiroDardo -> (x1,y1) -> calcularDistancia ->   d1 ->   estaEnCirculo ->    1
2                 (x2,y2)                           d2                          0
3                 (x3,y3)                           d3                          1
4                 (x4,y4)                           d4                          1
                                                                            ----------
                                                                            Reduce: sumarlo = DardosDentro
---

Estoy montando unas ETLs en SPARK (bigdata)

Y traen DNIs... vaya aventura !!!!!!
TABLA 1:    12345678A
TABLA 2:    12345678-A
            12345678-a
            12345678a
              345678a
TABLA 3:    00345678a
TABLA 4:    12.345.678a

Que me puede interesar? 
    Es disponer de un validador / normalizador de DNIs

        1º Validar si un DNI es correcto
                (entendiendo todas las vasibles variaciones lógicas de un DNI)
        2º Extraer de ese texto (DNI) -> El NUMERO y la LETRA
        3º Aplicar un formato a ese NUMERO y esa LETRA
            Con puntos / sin ellos
            Con un determinado caracter separador / sin él
            Con ceros por delante... o no

DNI en BBDD = String(9) Cuánto ocupa esto en una BBDD? 
                        Cúanto ocupa un caracter en una BBDD? o en un fichero?
                        Depende del caracter y del juego de caracteres
                        0-9[A-Z]                    ASCII / UTF-8  -> 1 byte
                                                            UTF-32 -> 4 bytes
              El DNI va a ocupar 9 bytes

Cuanto ocupa el número 99.999.999 en una BBDD?
    1 bit:  2
    2 bits: 4
    3 bits: 8
    8 bits = 1 byte = 2^8 = 256
    2 bytes = 256^2 = 65536
    4 bytes = 65536 ^2 = + 4kM En 4 bytes me sobra para guardar numeros hasta el 4kM
    
    Y la letra? 1 byte
    
    TOTAL si guardo el numero como numero y la letra aparte? 5 bytes

HDD 10 Mbs x 50.000 pts año 90 - 300€


IMPORTANTE: NUNCA JAMAS EN LA VIDA PROGRAMO CONTRA UN CONJUNTO DE DATOS !
            SIEMPRE PROGRAMO CONTRA UNOS REQUISITOS !!!!!!
            

23.000.023 / 23             => T
           --------
           Me da igual
 RESTO                      = 0..22
 
 RESTO	0	1	2	3	4	5	6	7	8	9	10	11
LETRA	T	R	W	A	G	M	Y	F	P	D	X	B
 

RESTO	12	13	14	15	16	17	18	19	20	21	22
LETRA	N	J	Z	S	Q	V	H	L	C	K	E


TipoDeDatos: CLASE: DNI(dni)
                                .esValido():Boolean
                                .dameNumero():Int
                                .dameLetra():Char
                                .formatear(ceros:Boolean, puntos:Boolean, Separador:Char):String
                        static  .dameLetra(numeroDNI): Char
                        
Qué admito como dni?    ESTOS SON MIS REQUERIMIENTOS
    Que venga con ceros delante
    Que venga sin puntos y con puntos.....OJO: 12.12.2022-T es válido? No es válido
                                     Pero bien puestos
    Que venga sin caracter de separación, o con un espacio o guión...
    Con la letra en mayuscula o minuscula