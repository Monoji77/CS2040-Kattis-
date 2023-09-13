import java.util.*;

public class NumberFun {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int cases = scan.nextInt();

        for (int i = 0; i < cases; i++) {
            int num1 = scan.nextInt();
            int num2 = scan.nextInt();
            int num3 = scan.nextInt();

            //addition
            if (num1 + num2 == num3) {
                System.out.println("Possible");
            }

            //subtraction
            else if (Math.abs(num1-num2) == num3) {
                System.out.println("Possible");
            }

            //multiplication
            else if (num1 * num2 == num3) {
                System.out.println("Possible");
            }
            
            //division
            else if (((double) num1/ (double) num2 == (double) num3 && num2 != 0) || ((double) num2/(double) num1 == (double) num3 && num1 != 0)) {
                System.out.println("Possible");
            }

            //Impossible
            else {
                System.out.println("Impossible");
            }
        
        }
        scan.close();
    }
}
