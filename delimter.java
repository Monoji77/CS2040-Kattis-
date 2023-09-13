import java.io.*;
import java.util.*;

public class delimter {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
        int n = Integer.parseInt(reader.readLine());
        
        Stack<Character> array = new Stack<>();
        
        String s = reader.readLine();
        int i = 0;
        for (; i < n; i++) {
            if (s.charAt(i) == ' ') {
                continue;
            }
            else if ((s.charAt(i) == ')' || s.charAt(i) == '}' ||
                        s.charAt(i) == ']') && array.size() == 0) {
                System.out.println(s.charAt(i) + " " + i);
                break;
            }
            else if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                array.push(s.charAt(i));
            }
            else if (array.peek() == '(' && s.charAt(i) == ')') {
                array.pop();
            }
            else if (array.peek() == '[' && s.charAt(i) == ']') {
                array.pop();
            }
            else if (array.peek() == '{' && s.charAt(i) == '}') {
                array.pop();
            }
            else {
                System.out.println(s.charAt(i) + " " + i);
                break;
            } 
            
        }
        if (i == n) {
            System.out.println("ok so far");
        }

    }
}
