import java.util.Scanner;

public class Main {
    public static final int MIN_PASSWORD_LENGTH = 8;

    static boolean hasValidLength(String pwd) {
        return pwd.length() >= MIN_PASSWORD_LENGTH;
    }

    static boolean hasUppercase(String pwd) {
        for (int i = 0; i < pwd.length(); i++) {
            if (Character.isUpperCase(pwd.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    static boolean hasDigit(String pwd) {
        for (int i = 0; i < pwd.length(); i++) {
            if (Character.isDigit(pwd.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    static String getPwd() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Input a password: ");
            String pwd = input.nextLine().trim();
            boolean badPwd = false;
            if (!hasValidLength(pwd)) {
                badPwd = true;
                System.out.println("El password no tiene el largo indicado.\nMinimo 8 caracteres");
            }
            if (!hasUppercase(pwd)) {
                badPwd = true;
                System.out.println("El password no tiene mayuscula.\nAl menos una.");
            }
            if (!hasDigit(pwd)) {
                badPwd = true;
                System.out.println("El password no tiene digito.\nAl menos uno.");
            }
            if (!badPwd) {
                System.out.println("El password ingresado es correcto.");
                return pwd;
            }

        }
    }

    public static void main(String[] args) {
        String pwd = getPwd();
        System.out.println("Password = " + pwd);
    }
}

