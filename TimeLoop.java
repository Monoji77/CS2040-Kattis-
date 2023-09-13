import java.util.*;

public class TimeLoop {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        int count = 1;
        while (count <= num) {
            System.out.println(count + " Abracadabra");
            count += 1;
        }
    scan.close();
    }
}
