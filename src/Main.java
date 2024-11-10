import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int filas;
        int columnas;
        int opcion = 0;


        //Solicitamos número de columnas y filas

        while (true) {
            System.out.println("Introduce número de filas: ");
            if (scanner.hasNextInt()) {
                filas = scanner.nextInt();
                if (filas > 0) {
                    break;

                } else {
                    System.out.println("El valor debe ser un entero positivo. Dime un nuevo valor");
                }
            } else {
                System.out.println("Valor incorrecto. Introduce un número entero.");
                scanner.next();             //Limpieza buffer.
            }
        }
        while (true) {
            System.out.println("Introduce el número de columnas: ");
            if (scanner.hasNextInt()) {
                columnas = scanner.nextInt();
                if (columnas > 0) {
                    break;
                } else {
                    System.out.println("El valor debe ser un entero positivo. Dime un nuevo valor");
                }
            } else {
                System.out.println("Valor incorrecto. Introduce un número entero.");
                scanner.next();             // Limpieza buffer.
            }
        }

        //Creamos matriz con números aleatorios

        int[][] matriz = new int[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = random.nextInt(9) + 1; //Incluimos el 1 y el 9
            }
        }

        // Imprimimos matriz

        System.out.println("La Matriz és: ");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        //Creamos el Menú
        do {
            System.out.println("Elige una opción:");
            System.out.println("[2] Poner bomba");
            System.out.println("[1] Mostrar matriz");
            System.out.println("[0] Salir");
            if (!scanner.hasNextInt()) {
                System.out.println("Valor incorrecto. Introduce un número entero.");
                scanner.next();             // Limpieza buffer.
                continue;
            }

            opcion = scanner.nextInt();

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo...");
                    scanner.close();
                    break;

                case 1:
                    System.out.println("La Matriz és: ");
                    for (int i = 0; i < filas; i++) {
                        for (int j = 0; j < columnas; j++) {
                            System.out.print(matriz[i][j] + " ");
                        }
                        System.out.println();
                    }
                    break;
                case 2:
                    if (ponerBomba(matriz, filas, columnas, scanner)) {
                        System.out.println("Todos los valores de la matriz són 0. Saliendo...");
                        opcion = 0;             //Termina el juego si la matriz esta vacía.
                    }
                    break;

                default:
                    System.out.println("Selecciona una opción del menú. ");
            }
        } while (opcion != 0);                  //Continuamos en el menú hasta que el usuario seleccione 0.
        scanner.close();
    }

    private static boolean ponerBomba(int[][] matriz, int filas, int columnas, Scanner scanner) {
        int x, y;

        while (true) {
            System.out.print("Introduce la coordenada X (fila) de la bomba: ");
            if (scanner.hasNextInt()) {
                x = scanner.nextInt();
                if (x >= 1 && x <= filas) {
                    x -= 1;                     //esto me lo ha chivado chatgpt para que coja valores desde el 1.
                    break;
                } else {
                    System.out.println("Coordenada fuera de rango. Dame un valor dentro de la matriz.");
                }
            } else {
                System.out.println("Valor incorrecto. Introduce un número entero.");
                scanner.next();                 //Limpieza buffer.
            }
        }
        while (true) {
            System.out.print("Introduce la coordenada Y (columna) de la bomba: ");
            if (scanner.hasNextInt()) {
                y = scanner.nextInt();
                if (y >= 1 && y <= columnas) {
                    y -= 1;                     //Ajuste a índice de matriz
                    break;
                } else {
                    System.out.println("Coordenada fuera de rango. Dame un valor dentro de la matriz.");
                }
            } else {
                System.out.println("Valor incorrecto. Introduce un número entero.");
                scanner.next();                 //Limpieza buffer.
            }
        }

        //Calcular explosión.
        int sumaExplosion = 0;
        for (int i = 0; i < matriz.length; i++) {
            sumaExplosion += matriz[i][y];      //Suma columna y.
        }
        for (int j = 0; j < matriz[0].length; j++) {
            sumaExplosion += matriz[x][j];      //Suma fila x.
        }
        sumaExplosion -= matriz[x][y];          //NO contamos la intersección.
        System.out.println("Resultado de la explosión en (" + x + ", " + y + "): " + sumaExplosion);

        //Colocamos todo a 0
        for (int i = 0; i < filas; i++) {
            matriz[i][y] = 0;
        }
        for (int j = 0; j < columnas; j++) {
            matriz[x][j] = 0;
        }

        // Comprobar si todo esta a 0
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (matriz[i][j] != 0) {
                    return false;               // La matriz no está vacía
                }
            }
        }
        return true;                            // La matriz está vacía
    }
}











