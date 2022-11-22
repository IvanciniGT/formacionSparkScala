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
        