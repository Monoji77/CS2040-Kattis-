import java.util.*;

public class Autori {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String[] names = scan.nextLine().split("-");
        for (String letter: names) {
            System.out.print(letter.charAt(0));
        }
        scan.close();
    }
}