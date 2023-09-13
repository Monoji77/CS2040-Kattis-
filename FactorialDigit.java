import java.util.*;

public class FactorialDigit {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int test = scan.nextInt();
        int num = 1;
        //for loop for test cases
        for (int i = 0; i < test; i++) {
            int a = scan.nextInt();

            //for loop for obtaining factorials
            for (int j = 1; j <= a; j++) {
                num *= j;
        }
        System.out.println(num%10);
        num = 1;      
    }
    scan.close();
}

}
