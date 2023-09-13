import java.util.*;

public class Detailed {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int cases = scan.nextInt();

        for (int i=0; i<cases; i++) {
            String A = scan.next();
            String B = scan.next();
            String[] C = A.split("");
            String[] D = B.split("");
            System.out.println(A);
            System.out.println(B);

            for (int j=0; j<C.length; j++) {
                if (C[j].equals(D[j])) {
                    System.out.print(".");
                }
                else {
                    System.out.print("*");
                }
            
            }
        System.out.println(""); 
        System.out.println("");
        }
        scan.close();
    }
}