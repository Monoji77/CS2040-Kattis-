import java.util.*;

public class Skener {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int r = scan.nextInt(), c = scan.nextInt(), zR = scan.nextInt(), zC = scan.nextInt();

        scan.nextLine();
        
        char[] s;
        String inter = new String();
        ArrayList<String> answer = new ArrayList<String>();
        for (int row = 0; row < r; row++) {
            s = scan.nextLine().toCharArray();
            inter = new String();
            for (int col = 0, cCount = 1; col < c; cCount++) {
                inter += s[col];
    
                if (cCount == zC) {

                    cCount = 0;
                    col++; 
                    
                }
            }
            answer.add(inter);
            for (int additional = 1; additional < zR; additional++) {
                answer.add(inter);
            }
        }
        
        for (int i = 0; i < answer.size(); i++) {
            System.out.println(answer.get(i) +"\n");

        }
    scan.close();
    }

}
