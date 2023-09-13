import java.io.*;
import java.util.*;

public class Guests {
    public static void main(String[] args) throws IOException {
        ArrayList<String> answer = new ArrayList<String>();

        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        Hashtable<String,Integer> hashtable = new Hashtable<String,Integer>();

        int n = Integer.parseInt(scan.readLine());

        for (int i = 0; i < n; i++) {
            
            int g = Integer.parseInt(scan.readLine());

            String p1 = scan.readLine();
            String[] p = p1.split(" ");
            
            
            for (int j = 0; j < g; j++) {
                if (hashtable.containsKey(p[j])) {
                    hashtable.put(p[j], hashtable.get(p[j])+1);
                    continue;
                }
                hashtable.put(p[j], 1);
            }

            for (String keys: hashtable.keySet()) {
                if (hashtable.get(keys) == 1) {
                    answer.add("Case #" + (i+1) + ": " + keys);
                    break;
                }
            }

            hashtable.clear();
        }
        scan.close();
        for (String s: answer) {
            System.out.println(s);
        }
        
    }
}
