import java.util.Scanner;

public class Apaxians {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char[] s = scan.nextLine().toCharArray();
        
        StringBuilder answer = new StringBuilder();
        int j;
        for (int i = 0; i < s.length; i++) {
            j = i+1;
            if (j == s.length) {
                answer.append(s[i]); 
                break;
            }
            if (s[i] == s[j]) {
                continue;
            }
            answer.append(s[i]);
        }
        
        System.out.println(answer);
        scan.close();
    }
}
