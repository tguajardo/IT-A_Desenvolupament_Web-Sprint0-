import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Contact> contacts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("----MENU----");
            System.out.println("1. Crear contacto nuevo.");
            System.out.println("2. Buscar contacto.");
            System.out.println("3. Ver todos los contactos.");
            System.out.println("0. Salir del programa.");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addContact(scanner, contacts);
                    break;
                case 2:
                    findContact(scanner, contacts);
                    break;
                case 3:
                    printContacts(contacts);
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opcion invalida.");

            }

        } while (option != 0);
        scanner.close();
    }

    public static void addContact(Scanner scanner, ArrayList<Contact> contacts) {
        contacts.add(Contact.createContact(scanner));
    }

    public static void findContact(Scanner scanner, ArrayList<Contact> contacts) {
        System.out.println("Datos contacto a buscar");
        System.out.println("Nombre");
        String name = scanner.nextLine();
        System.out.println(("Apellidos"));
        String lastNames = scanner.nextLine();

        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(name.toLowerCase()) && contact.getLastNames().toLowerCase().contains(lastNames.toLowerCase())) {
                System.out.println("Persona Encontrada.");
                System.out.println(contact);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Persona NO encontrada.");
        }
    }

    public static void printContacts(ArrayList<Contact> contacts) {
        System.out.println("Lista de contactos: ");
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println("Contacto " + (i + 1));
            System.out.println(contacts.get(i));
        }
    }

}
