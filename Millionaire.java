import java.io.*;
import java.util.*;

public class Millionaire {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int m = io.getInt(), n = io.getInt();

        int[][] adjacencyMatrix = new int[m][n];
    
        // have a reference adjacency matrix => O(V^2)
        for (int v = 0; v < m; ++v) {
            for (int u = 0; u < n; ++u) {
                adjacencyMatrix[v][u] = io.getInt();
            }
        }

        PriorityQueue<IntegerPair> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[m][n];
        int difference = 0;
        pq.offer(new IntegerPair(0, 0, difference));

        while (!visited[m-1][n-1]) {
            IntegerPair curr = pq.poll();
            // System.out.println("Integer Pair: " + curr + " || weight: " + curr.weight);
            difference = Math.max(difference, curr.weight);

            visited[curr.v][curr.u] = true;

            int[] rowMove = { 1, -1, 0, 0 };
            int[] colMove = { 0, 0, -1, 1 };
            for (int j = 0; j < 4; ++j) {
                int newRow = curr.v + rowMove[j];
                int newCol = curr.u + colMove[j];
                if (newRow >= 0 && newRow < m && newCol >= 0 
                && newCol < n && !visited[newRow][newCol]) {
                    pq.offer(new IntegerPair(newRow, newCol, adjacencyMatrix[newRow][newCol] - adjacencyMatrix[curr.v][curr.u]));
                }
            }
        }

        io.println(difference);
        io.close();
    }
}

class IntegerPair implements Comparable<IntegerPair> {
    int v, u;
    int weight;

    public IntegerPair(int v, int u, int weight) {
        this.v = v;
        this.u = u;
        this.weight = weight;
    }

    public int compareTo(IntegerPair other) {
        return this.weight - other.weight;
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