import java.io.*;
import java.io.InputStreamReader;
import java.util.*;

public class HashTable1 {
    public static void main(String[] args) throws IOException {
        int count;
        String p;
        Hashtable<Integer, Integer> hashtable = new Hashtable<Integer, Integer>();
        
        
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            p = scan.readLine();
            count = 0;
            
            String[] n_m = p.split(" ");

            int a1 = Integer.parseInt(n_m[0]);
            int b2 = Integer.parseInt(n_m[1]);

            if (a1 == 0 && b2 == 0) {
                scan.close();
                break;
            }
            // Jack + Jill
            for (int i = 0; i < a1; i++) {
                int cdJack = Integer.parseInt(scan.readLine());
                hashtable.put(cdJack,1);
            }

            for (int i = 0; i < b2; i++) {
                int cdJill = Integer.parseInt(scan.readLine());
                if (hashtable.containsKey(cdJill))
                    count++;
            }

            System.out.println(count);
            hashtable.clear();
        }
    }
}
