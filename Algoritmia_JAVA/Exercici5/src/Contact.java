//Exercici 5
//Mitjançant programació orientada a objectes, tracta de crear una agenda de contactes. Cada contacte tindrà:
//
//» Nom
//» Cognoms
//» Nº telèfon
//» Email
//A l'agenda podrem afegir contactes nous, veure un contacte introduint nom i cognoms, i veure tots els contactes enregistrats.

import java.util.Scanner;

public class Contact {
    private String name;
    private String lastNames;
    private int phone;
    private String mail;

    public Contact(String name, String lastNames, int phone, String mail) {
        this.name = name;
        this.lastNames = lastNames;
        this.phone = phone;
        this.mail = mail;
    }

    public static Contact createContact(Scanner scanner) {
        System.out.print("Introduce Nombre del contacto: ");
        String name = scanner.nextLine();
        System.out.print("Introduce Apellidos del contacto: ");
        String lastNames = scanner.nextLine();
        System.out.print("Introduce Telefono del contacto: ");
        int phone = Integer.parseInt(scanner.nextLine());
        System.out.print("Introduce Correo del contacto: ");
        String mail = scanner.nextLine();
        return new Contact(name, lastNames, phone, mail);
    }

    @Override
    public String toString() {
        return "Contact Info\n" +
                "Nombre Completo: " + getName() + " " + getLastNames() + "\n" +
                "Correo Electronico: " + getMail() + "\n" +
                "Telefono: " + getphone();
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String newLastNames) {
        this.lastNames = newLastNames;
    }

    public int getphone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String newMail) {
        this.mail = newMail;
    }

    public void set(int newPhone) {
        this.phone = newPhone;
    }
}
