import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int filas = 0;
        int columnas = 0;
        int opcion = 0;
        boolean datofilas = false;
        boolean datocolumnas = false;
        int valor;
        ArrayList<Integer> ranking = new ArrayList<>();

        //Solicitamos número de columnas y filas

        while(!datofilas || !datocolumnas){
            System.out.println(datofilas ? "Introduce el número de columnas: " : "Introduce el número de filas: ");
            if(!scanner.hasNextInt()){              // Verificamos que el tamaño es un entero.
                System.out.println("Dato erróneo, introduce un número entero.");
                scanner.next();                     // Limpieza buffer.
            }else{
                valor = scanner.nextInt();
                if (valor > 0) {
                    if (!datofilas){
                        filas=valor;
                        datofilas = true;           //Filas okey.
                    }else{
                        columnas = valor;
                        datocolumnas = true;        //Columnas okey.
                    }
                }else{
                    System.out.println("El valor debe ser un entero positivo. Dime un nuevo valor");

                }
            }
        }
        System.out.println("---------------------------------------------");
        //Creamos e imprimimos la matriz con números aleatorios

        int[][] matriz = new int[filas][columnas];
        System.out.println("La Matriz és: ");

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = random.nextInt(9) + 1; //Incluimos el 1 y el 9
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------------------------");
        //Creamos el Menú
        do {
            System.out.println("Elige una opción:");
            System.out.println("[3] Ranking");
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
                    if (ponerBomba(matriz, filas, columnas, scanner, ranking)) {
                        System.out.println("Todos los valores de la matriz són 0. Ranking final!"); // Salimos mostrando el ranking cuando todos los valores de la matriz están a 0.
                        ranking.sort(Collections.reverseOrder());
                        for (int i = 0; i < ranking.size(); i++) {
                            System.out.println("Puesto " + (i + 1) + ": " + ranking.get(i));
                        }
                        opcion = 0;             //Termina el juego si la matriz esta vacía.
                    }
                    break;
                case 3:
                    if(ranking.size()>0){
                        ranking.sort(Collections.reverseOrder());       // Lo he consultado en chatgpt para ver como añadir, guardar y ordenar el ranking.
                        for (int i = 0; i < ranking.size(); i++) {
                            System.out.println("Puesto " + (i + 1) + ": " + ranking.get(i));
                        }
                    }else{
                        System.out.println("No hay puntuaciones. Empieza a jugar!!!!");
                    }
                    break;
                default:
                    System.out.println("Selecciona una opción del menú. ");
            }
            System.out.println("---------------------------------------------");
        } while (opcion != 0);                  //Continuamos en el menú hasta que el usuario seleccione 0.
        scanner.close();
    }

    private static boolean ponerBomba(int[][] matriz, int filas, int columnas, Scanner scanner, ArrayList<Integer> ranking) {

        int x = 0, y = 0, valor;
        boolean coordenadaX = false;
        boolean coordenadaY = false;

        while(!coordenadaX || !coordenadaY) {
            System.out.println("Introduce la coordenada "  + (coordenadaX ? "Y (columna) " : "X (fila) ") +  "de la bomba: ");
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                if (valor >= 1 && valor <= (!coordenadaX ? filas : columnas)) {     // Si tenemos un valor correcto para filas preguntamos por columnas sino repetimos bucle. Cuando tenemos ambos valores correctos, seguimos adelante.
                    valor -= 1;                         //esto me lo ha chivado chatgpt para que coja valores desde el 1.
                    if(!coordenadaX){
                        x = valor;
                        coordenadaX = true;
                    }else{
                        y = valor;
                        coordenadaY = true;
                    }
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
            matriz[i][y] = 0;                   //Colocamos todo a 0
        }
        for (int j = 0; j < matriz[0].length; j++) {
            sumaExplosion += matriz[x][j];      //Suma fila x.
            matriz[x][j] = 0;                   //Colocamos todo a 0
        }
        sumaExplosion -= matriz[x][y];          //NO contamos la intersección.
        System.out.println("Resultado de la explosión en (" + (x + 1)+ ", " + (y + 1) + "): " + sumaExplosion);     // Sumamos 1 a las coordenadas para que nos de el valor "real".
        ranking.add(sumaExplosion);             // Añadimos al ranking la puntuación.

        // Comprobar si todo esta a 0
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (matriz[i][j] != 0) {
                    return false; // La matriz no está vacía
                }
            }
        }
        return true; // La matriz está vacía
    }
}












