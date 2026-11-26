import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<String> getArray(Scanner sc) {
        ArrayList<String> returnArray = new ArrayList<>();
        String element;
        System.out.println("Ingresa los elementos del array, para terminar introduce \"FIN\"");
        while (true){
            element = sc.nextLine().trim();
            if (element.equals("FIN")){
                System.out.println("Array terminado.");
                break;
            }
            returnArray.add(element);
        }
        return returnArray;
    }

    public static ArrayList<String> moveArray(Scanner sc, ArrayList<String> array) {
        System.out.print("Espacios a mover el array: ");
        int moveSpaces = sc.nextInt();
        sc.nextLine();
        ArrayList<String> newArray = new ArrayList<>();
        for (int i = 0; i < array.size(); i++){
            newArray.add(array.get((i + moveSpaces)%array.size()));
        }
        return newArray;
    }

    public static void showArray(ArrayList<String> array) {
        System.out.print("Array: ");
        System.out.println(array);
    }
    public static int showMenu(Scanner sc, String title, String[] options) {
        System.out.println("----" + title + "----");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.println("0. Salir");
        System.out.println("Opcion: ");
        return Integer.parseInt(sc.nextLine());
    }

    public static void main(String[] args) {
        ArrayList<String> workArray = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        String[] options = {
                "Crear Array",
                "Mover Array",
                "Ver Array"};
        int option;
        do{
            option = showMenu(sc,"Menu Array", options);
            switch (option){
                case 0:
                    System.out.println("Saliendo del programa");
                    break;
                case 1:
                    if (workArray.isEmpty()) {
                        workArray = getArray(sc);
                        break;
                    }
                    System.out.println("El array ya existe.");
                    break;
                case 2:
                    workArray = moveArray(sc, workArray);
                    break;
                    case 3:
                        showArray(workArray);
                        break;
            }
        } while (option != 0);
        sc.close();
    }
}