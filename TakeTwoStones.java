import java.util.*;

public class TakeTwoStones {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int answer = scan.nextInt();

        if (answer%2 != 0) {
            System.out.println("Alice");
        }
        else {
            System.out.println("Bob");
        }
        scan.close();
    }
}