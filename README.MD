# BOMBERMAN

## He desarrollado un código que simula el juego de acción/puzzle Bomberman.

- El juego consiste en acumular el máximo de puntos posible detonando bombas, esta explotará en las coordenadas
que el jugador indique, sumando todos los números que se encuentren en dichas filas y columnas y colocando 
estas a valor 0. El jugador que mayor puntuación obtenga será el ganador.

- Lo primero que nos pedirá el juego será ingresar dos números (filas,columnas) para generar una matriz de
números aleatorios.
Seguidamente nos mostrará la matriz y un menú dónde podremos ver diferentes opciones:

3. Ranking
2. Poner una bomba
1. Mostrar matriz
0. Salir

- Iremos colocando bombas hasta que la matriz quede completamente a 0, al finalizar nos mostrará el Ranking
y nos sacará del programa.

## Problemas y soluciones

- Mi primer problema lo encontré al pedir los primeros datos, puesto que queria hacerlo solo con una función,
buscando encontre las relaciones ternarias y con ella conseguí lo que buscaba, en una sola función recoger los
dos datos (filas,columnas), y así imprimir directamente la matriz.
- Otro quebradero de cabeza fue al colocar la bomba y mostrar la posición de las coordenadas, puesto que me 
mostraba la posición de la matriz, la cual empieza en 0 (obviamente) y hasta que me di cuenta no lo pude solucionar.
Sólo tuve que sumar uno a esa posición y solucionado. (Para solucionar esto me apoye en chatgpt).
- El último escollo fue el Ranking y como guardar las posiciones, para ello implementé la clase Collections, que me
permite almacenar y administrar datos, los cuales guardaba en mi Arraylist. (Chatgpt me ayudo un poco con esta parte).

