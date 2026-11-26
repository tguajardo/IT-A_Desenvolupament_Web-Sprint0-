import java.util.Scanner;

public class Main {
    public static boolean isHeterograma(String word){
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            for(int j = i + 1; j < word.length(); j++){
                if(word.charAt(j) == c){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isIsograma(String word){
        //que tanto se repite una letra, la primera la define y todas se comparan con ello
        if (isHeterograma(word)){
            return true;
        }
        int expectedCount = -1;

        //revisamos cada char del word
        for(int i = 0; i  < word.length(); i++){
            char current = word.charAt(i);

            //revisar si ya se reviso el char actual
            boolean alreadyProcessed = false;
            for (int j = 0; j < i; j++){
                if(word.charAt(j) == current){
                    //el char actual ya se ha revisado
                    alreadyProcessed = true;
                    break;
                }
            }
            if(alreadyProcessed){
                //pasamos al siguiente char
                continue;
            }

            //como no se ha contado el actual char, hacemos cuenta
            int count = 0;

            for(int j = 0; j < word.length(); j++){
                if(word.charAt(j) == current){
                    count++;
                }
            }

            //comparamos con expectedCount, el primer char determina el valor
            if(expectedCount == -1){
                expectedCount = count;
            } else if (count != expectedCount){
                return false;
            }
        }
        return true;
    }

    public static String getWord(){
        Scanner input = new Scanner(System.in);
        System.out.print("Introduce la palabra a revisar: ");
        return input.nextLine().trim();
    }

    public static void main(String[] args) {
        String word = getWord();
        System.out.println("Es heterograma: " + isHeterograma(word));
        System.out.println("Es isograma: " + isIsograma(word));
    }
}
