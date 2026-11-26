/*
L'usuari introdueix un número, si el número és positiu, el programa escriu tots els nombres
 de 0 a aquest nombre positiu, d'un en un. Si el número és negatiu, el programa escriu tots els nombres des de
 d'aquest nombre negatiu fins a 0. També d'un en un.
*/

import java.util.Scanner;

public class Main {
    static int getNumber() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Input a number: ");
            //para confirmar int usar .hasNextInt()
            int number = input.nextInt();

            if (number != 0) {
                return number;
            }

            System.out.println("El numero debe ser distinto a 0");
        }
    }
    static void printNumbers(int number){
        if (number < 0){
            for (;number < 0; number++){
                System.out.print(number + " ");
            }
        } else {
            for(int i = 1; i <= number; i++){
                System.out.print(i + " ");
            }
        }
    }
    public static void main(String[] args){
        int number = getNumber();
        printNumbers(number);
    }
}
