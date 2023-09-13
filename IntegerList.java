import java.io.*;
import java.util.*;

public class IntegerList {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio(System.in, System.out);

        int n = io.getInt();

        for (int i = 0; i < n; i++) {
            String operation = io.getWord();
            
            int size = io.getInt();
        
            String list = io.getWord();

            CustomList integerlist = new CustomList(list, size);

            for (char c: operation.toCharArray()) {
                if (c == 'R') {
                    integerlist.reverse();
                }
                else if (c == 'D') {
                    integerlist.drop();
                }
            }

            if (integerlist.error) {
                // System.out.println("error");
                io.println("error");
            }

            else if (size == 0 || integerlist.removed == integerlist.size) {
                io.println("[]");
            }

            else if (integerlist.reversed){
                // System.out.print("[");
                io.print("[");
                while (integerlist.tail < integerlist.head) {
                    // System.out.print(integerlist.list[integerlist.tail] + ",");
                    io.print(integerlist.list[integerlist.head] + ",");
                    integerlist.head--;
                }
                // System.out.print(integerlist.list[integerlist.tail] + "]");
                io.print(integerlist.list[integerlist.head] + "]");
                // System.out.println();
                io.println();
            }
            
            else if (!integerlist.reversed) {
                io.print("[");
                // System.out.print("[");
                while (integerlist.head < integerlist.tail) {
                    // System.out.print(integerlist.list[integerlist.head] + ",");
                    io.print(integerlist.list[integerlist.head] + ",");
                    integerlist.head++;
                }
                // System.out.print(integerlist.list[integerlist.head] + "]");
                io.print(integerlist.list[integerlist.head] + "]");
                // System.out.println();
                io.println();
            }
            
        }
        io.close();
    }
}

class CustomList {
    int head;
    int tail;
    int size;
    String[] list;
    boolean reversed;
    int removed;
    boolean error;

    public CustomList(String s, int n) {
        
        list = new String[n];
        head = 0;
        tail = 0;
        reversed = false;
        removed = 0;
        processString(s, n);
        // for (int i = 0; i < list.length; i++){
        //     System.out.print(list[i] + " ");
        // } 
        // System.out.println();
    }

    String[] processString(String s, int n) {
        if (n == 0) {
            list = new String[0];
            return list;
        }
        StringBuilder helper = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 48 && s.charAt(i) <= 57) {
                helper.append(s.charAt(i));
            }
            else if (helper.length() != 0 ) {
                list[tail] = helper.toString();
                helper = new StringBuilder();
                tail++;
            }
        }
        size = tail;
        tail = (tail-1) % size;
        return list;
    }

    void reverse() {
        if (reversed) {
            reversed = false;
        }
        else 
            reversed = true;

        int temp = head;
        head = tail;
        tail = temp;
        // System.out.print("head: " + head + " || tail: " + tail);
        // System.out.println();
    }

    void drop() {
        if (size == 0) { error = true; } 

        else if (!reversed) {
            head = (head + 1) % size;
            removed++;
        }
        else {
            head = (head - 1 + size) % size;
            removed++;
        }
        if (removed > size) {
            error = true;
        }
        // System.out.print("head: " + head + " || tail: " + tail);
        // System.out.println();
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