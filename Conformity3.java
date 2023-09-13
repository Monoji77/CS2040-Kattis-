import java.io.*;
import java.util.*;

public class Conformity3 {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();

        // Keep a record of combinations: key = multiplication of combination, value = count 
        Hashtable<Long, Integer> recordofCombinations = new Hashtable<>();

        long a1,a2,a3,a4,a5;// multiplicationofCombinations;
        long popularCombination = 0;
        
        for (int i = 0; i < n; i++) {
            a1 = io.getInt();
            a2 = io.getInt();
            a3 = io.getInt();
            a4 = io.getInt();
            a5 = io.getInt();

            // multiplicationofCombinations = a1 * a2 * a3 * a4 * a5;

            long[] array = {a1, a2, a3, a4, a5};

            Arrays.sort(array);
            StringBuilder temp = new StringBuilder();
            
            for (int j = 0; j < 5; j++) {
                temp.append(array[j]);
            }

            long temporary = Integer.parseInt(temp.toString());
            io.close();
            System.out.println("temporary: " + temporary);
            // System.out.println("array " + i +": " + Arrays.toString(array));

            // io.println("Index " + i + ": " + multiplicationofCombinations);
            if (i == 0) {
                popularCombination = temporary;
            }
            if (recordofCombinations.containsKey(temporary)) {
                // io.print("Reached here");
                recordofCombinations.put(temporary, recordofCombinations.get(temporary)+1);
                
                if (recordofCombinations.get(temporary) > recordofCombinations.get(popularCombination)) {
                    popularCombination = temporary;
                    // io.print("most pop combination: " + popularCombination);
                    io.println("THIS HAPPENED Hashtable at index " + i + ": " + recordofCombinations);
                }
            }   
            else {
                recordofCombinations.put(temporary,1);
                io.println("Hashtable at index " + i + ": " + recordofCombinations);
            }
        }

        if (recordofCombinations.get(popularCombination) == 1) {
            // System.out.println("this happened");
            io.print(n);
        }
        
        else {
            // System.out.println("else this happened");
            int num = 0;
            for (long key : recordofCombinations.keySet()) {
                if (recordofCombinations.get(key) == recordofCombinations.get(popularCombination)) {
                    num += recordofCombinations.get(key);
                }
            }
            io.print(num);
        }

        io.close();

        
    }
}


class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
    super(new BufferedOutputStream(System.out));
    r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
    super(new BufferedOutputStream(o));
    r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
    return peekToken() != null;
    }

    public int getInt() {
    return Integer.parseInt(nextToken());
    }

    public double getDouble() { 
    return Double.parseDouble(nextToken());
    }

    public long getLong() {
    return Long.parseLong(nextToken());
    }

    public String getWord() {
    return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
    if (token == null) 
        try {
        while (st == null || !st.hasMoreTokens()) {
            line = r.readLine();
            if (line == null) return null;
            st = new StringTokenizer(line);
        }
        token = st.nextToken();
        } catch (IOException e) { }
    return token;
    }

    private String nextToken() {
    String ans = peekToken();
    token = null;
    return ans;
    }
}