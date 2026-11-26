/*Exercici 3
Donat un
array de
temperatures,digues:

Temperatura màxima.
Temperatura mínima.
Temperatura mitjana.
Dies amb
més de 20graus.
        Exemple :[17,19,18,21,20,17,19,20,21,24,26,25,20,18,19,17,22,20,25,28,25]

Temperatura màxima = 28.
Temperatura mínima = 17.
Temperatura mitjana = 21.
Dies amb
més de 20graus =9.*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    // Temperaturas de ejemplo
    public static final ArrayList<Integer> TEMPERATURES =
            new ArrayList<>(List.of(17, 19, 18, 21, 20, 17, 19,
                    20, 21, 24, 26, 25, 20, 18, 19, 17, 22, 20, 25, 28, 25));

    // Funciones que reciben un Array
    public static int getMax(ArrayList<Integer> tempArray) {
        int maxValue = tempArray.getFirst();
        for (int temp : tempArray) {
            maxValue = Math.max(maxValue, temp);
        }
        return maxValue;
    }

    public static int getMin(ArrayList<Integer> tempArray) {
        int minValue = tempArray.getFirst();
        for (int temp : tempArray) {
            minValue = Math.min(minValue, temp);
        }
        return minValue;
    }

    public static double getAvg(ArrayList<Integer> tempArray) {
        int tempSum = 0;
        for (int temp : tempArray) {
            tempSum += temp;
        }
        return (double) tempSum / tempArray.size();
    }

    public static int over20(ArrayList<Integer> tempArray) {
        int dayCount = 0;
        for (int temp : tempArray) {
            if (temp > 20) {
                dayCount++;
            }
        }
        return dayCount;
    }

    //Si no se ingresa un array, se usa el de ejemplo
    public static int getMax() {
        return getMax(TEMPERATURES);
    }

    public static int getMin() {
        return getMin(TEMPERATURES);
    }

    public static double getAvg() {
        return getAvg(TEMPERATURES);
    }

    public static int over20() {
        return over20(TEMPERATURES);
    }

    //funcion para recibir un array
    public static ArrayList<Integer> getArray() {
        ArrayList<Integer> tempArray = new ArrayList<>();

        System.out.println("Ingresa las temperaturas (int) a analizar (para terminar ingresa una \"x\"): ");
        Scanner input = new Scanner(System.in);

        while (true) {
            if (input.hasNextInt()) {
                tempArray.add(input.nextInt());
            } else if (input.nextLine().trim().equals("x")){
                    break;
            }
        }
        return tempArray;
    }

    public static void main(String[] args) {
        ArrayList<Integer> tempArray = getArray();
        int maxTemp;
        int minTemp;
        double avgTemp;
        int over20Temp;

        if (tempArray.isEmpty()) {
            System.out.println("No se han ingresado Valores, se usaran los por defecto.");
            maxTemp = getMax();
            minTemp = getMin();
            avgTemp = getAvg();
            over20Temp = over20();
        } else {
            System.out.println("Calculando con valores ingresados.");
            maxTemp = getMax(tempArray);
            minTemp = getMin(tempArray);
            avgTemp = getAvg(tempArray);
            over20Temp = over20(tempArray);
        }
        System.out.println("Resultados\n" +
                "Temperatura Max: " + maxTemp + "\n" +
                "Temperatura Min: " + minTemp + "\n" +
                "Temperatura Promedio: " + avgTemp + "\n" +
                "Dias con temperatura sobre 20: " + over20Temp);
    }

}
