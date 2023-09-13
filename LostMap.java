import java.io.*;
import java.util.*;

public class LostMap {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int noofVillage = io.getInt(); // noofVillage

        Graph graph = new Graph(noofVillage);
        ArrayList<ArrayList<IntegerPair>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < noofVillage; ++i) {
            adjacencyList.add(new ArrayList<>());
        }
        // addition to adjacencyList
        for (int i = 0; i < noofVillage; ++i) {
            for (int j = 0; j < noofVillage; ++j) {
                int weight = io.getInt();
                if (weight == 0) { continue; }
                // System.out.println("weight != 0 || index: " + j);
                adjacencyList.get(i).add(new IntegerPair(i, j, weight));    
            }
        }

        int startingVertice = 0;

        PriorityQueue<IntegerPair> pq = new PriorityQueue<IntegerPair>();

        for (int i = 0; i < graph.adjacencyList.get(startingVertice).size(); ++i) {
            pq.offer(adjacencyList.get(startingVertice).get(i));
        }

        System.out.println(pq.size());
        while (!pq.isEmpty()) {
            IntegerPair next = pq.poll();
            if (!graph.verticesUsed[next.v]) {
                graph.verticesUsed[next.v] = true;
                graph.adjacencyList.get(next.v).add(next.u);

                int curr = next.v; int adjacent = next.u;
                io.println(curr + " " + adjacent);
                for (int i = 0; i < graph.adjacencyList.get(adjacent).size(); ++i) {
                    if (graph.verticesUsed[next.u] != true) {
                        pq.offer(adjacencyList.get(adjacent).get(startingVertice));
                    }
                }
            }
        }
        io.close();
    }
}

class Graph {
    public PriorityQueue<IntegerPair> pq;
    public ArrayList<ArrayList<Integer>> adjacencyList;
    public boolean[] verticesUsed;
    public int truthCount;

    public Graph(int n) {
        adjacencyList = new ArrayList<>();
        pq = new PriorityQueue<>();
        verticesUsed = new boolean[n];
        truthCount = 0;

        for (int i = 0; i < n; ++i) {
            adjacencyList.add(new ArrayList<>());
        }
    }
}

class IntegerPair implements Comparable<IntegerPair> {
    int v, u, weight;
    
    public IntegerPair(int v, int u, int weight) {
        this.v = v;
        this.u = u;
        this.weight = weight;
    }

    public int compareTo(IntegerPair other) {
        return this.weight - other.weight;
    }
}

class EdgePair implements Comparable<EdgePair> {
    int u, weight;

    public EdgePair(int u, int weight) {
        this.u = u;
        this.weight = weight;
    }

    public int compareTo(EdgePair other) {
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
