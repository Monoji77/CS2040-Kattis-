import java.util.*;
import java.lang.*;

public class T9Spelling {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n =  scan.nextInt();
        scan.nextLine();
        Hashtable<Character,String> hashtable = new Hashtable<Character,String>();
        ArrayList<Character> alphabets = new ArrayList<Character>();
        
        // have all alphabets
        for (char c = 'a'; c <= 'z'; ++c) {
            alphabets.add(c);
        }
        scan.close();


        // from 'a' to 'o'
        int index, jIndex, kIndex;
        for (index = 2, jIndex = 0, kIndex = 0; index < 7;jIndex++) {
            String inter = "";
            
            if (kIndex == 0) {
                inter += index ;
                kIndex++;
            }
            else if (kIndex == 1) {
                inter += ((int)Math.pow(10,kIndex) * index) + index;
                kIndex++;
            }
            else if (kIndex == 2) {
                inter += ((int)Math.pow(10,kIndex)*index + ((int)Math.pow(10,kIndex-1)*index) + index);
                kIndex = 0;
                index++;

            }
            hashtable.put(alphabets.get(jIndex), inter);
        }
        
        // from p-s
        int p; 
        String next;
        for (int i = 0; i < 4; i++, jIndex++) {
            p = 7;
            next = "";
            if (i == 3) {
                next += ((int) Math.pow(10,i) * p)  + ((int) Math.pow(10,i-1) * p) + ((int) Math.pow(10,i-2) * p) + p;
                
            }
            else if (i == 2) {
                next += ((int) Math.pow(10,i) * p)  + ((int) Math.pow(10,i-1) * p) + p;
            }
            else if (i == 1) {
                next += ((int) Math.pow(10,i) * p) + p;
            }
            else {
                next += p;
            }
            hashtable.put(alphabets.get(jIndex), next);
        }

        // from TUV
        
        for (int i = 0; i < 3; i++, jIndex++) {
            p = 8;
            next = "";
            if (i == 1) {
                next += ((int)Math.pow(10,i) * p) + p;
                
            }
            else if (i == 2) {
                next += ((int)Math.pow(10,i)*p + ((int)Math.pow(10,i-1)*p) + p);
            }
            else {
                next += p;
            }
            hashtable.put(alphabets.get(jIndex), next);
            
        }

        //WXYZ
        
        for (int i = 0; i < 4; i++,jIndex++) {
            p = 9; 
            next = "";
            if (i == 3) {
                next += ((int) Math.pow(10,i) * p)  + ((int) Math.pow(10,i-1) * p) + ((int) Math.pow(10,i-2) * p) + p;
                
            }
            else if (i == 2) {
                next += ((int) Math.pow(10,i) * p)  + ((int) Math.pow(10,i-1) * p) + p;
            }
            else if (i == 1) {
                next += ((int) Math.pow(10,i) * p) + p;
            }
            else {
                next += p;
            }
            hashtable.put(alphabets.get(jIndex), next);

        }
        hashtable.put(' ', "0");
        
        String cases;
        StringBuilder answer;
        String prev = " ";
        String curr;
        for (int i = 0; i < n; i++) {
            answer = new StringBuilder(); 
            cases = scan.nextLine();
            for (int j = 0; j < cases.length(); j++) {
                curr = hashtable.get(cases.charAt(j));
            
                if (prev.charAt(0) == curr.charAt(0)) {
                    answer.append(" "); 
                }
                
                
                answer.append(hashtable.get(cases.charAt(j)));        
                prev = curr;
            }
            System.out.println("Case #" + (i+1) +": " + answer);
            scan.close();

        }        
    }
}

