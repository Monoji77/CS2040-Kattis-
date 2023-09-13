import java.util.*;

public class FizzBuzz {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int x = scan.nextInt(), y = scan.nextInt(), n = scan.nextInt();

        for (int i = 1; i <= n; i++) {
            if ((i % x == 0) && (i % y == 0) && i >= x && i >= y) {
                System.out.println("FizzBuzz");
            }
            else if (i % x == 0) {
                System.out.println("Fizz");
            }
            else if (i % y == 0) {
                System.out.println("Buzz");
            }
            else 
                System.out.println(i);
        }
        scan.close();
    }
}
