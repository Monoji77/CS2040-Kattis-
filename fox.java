import java.io.*;
import java.util.*;
public class fox {
    public static void main(String[] args) throws IOException {

        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        Hashtable<String, String> hashtable = new Hashtable<String,String>(); 
        

        int t = Integer.parseInt(scan.readLine());
        
        for (int i = 0; i < t; i++) {
            String[] recording = scan.readLine().split(" ");
            String sound;
            hashtable.clear();
            StringBuilder answer = new StringBuilder();

            while (true) {
                sound = scan.readLine();

                if (sound.equals("what does the fox say?")) {
                    break;
                }
                    
            
                hashtable.put(sound.split(" ")[0], sound.split(" ")[2]);
            
            }
            
            for (String s: recording) {
                if (hashtable.containsValue(s)) {
                    continue;
                }
                answer.append(s + " ");
            }

            System.out.println(answer.toString());
            
        }
        scan.close();
    }
}
