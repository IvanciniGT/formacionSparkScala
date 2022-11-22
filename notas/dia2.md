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
