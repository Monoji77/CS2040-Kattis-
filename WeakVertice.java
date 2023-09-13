import java.io.*;
import java.util.*;

public class WeakVertice {
    static ArrayList<ArrayList<Integer>> adjList;
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int noofVertices = io.getInt();
        
        while (noofVertices != -1) {    
            // create new list
            adjList = new ArrayList<ArrayList<Integer>>(noofVertices);

            for (int row = 0; row < noofVertices; ++row) {
                ArrayList<Integer> adjlst = new ArrayList<>();
                
                int count = 0;

                for (int col = 0; col < noofVertices; ++col) {
                    int val = io.getInt();
                    if (val == 1) {
                        adjlst.add(count);
                    }
                    count++;
                }
                adjList.add(adjlst);
            }

            ArrayList<Integer> includedVertices = new ArrayList<>(noofVertices);

            for (int i = 0; i < noofVertices; ++i) {
                if (includedVertices.contains(i)) { continue; }

                if (isWeak(adjList, i, includedVertices)) {
                    io.print(i + " ");
                }
            }
            io.println();
            noofVertices = io.getInt();
        }
        io.close();
    }

    static boolean isWeak(ArrayList<ArrayList<Integer>> adjList, int vertex, ArrayList<Integer> includedVertices) {
        if (adjList.get(vertex).size() < 2) {
            return true;
        }
        for (int neighbour: adjList.get(vertex)) {
            for (int next: adjList.get(neighbour)) {
                if (adjList.get(next).contains(vertex)) {
                    includedVertices.add(vertex);
                    includedVertices.add(neighbour);
                    includedVertices.add(next);
                    return false;
                }
            }
        }
        return true;
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
