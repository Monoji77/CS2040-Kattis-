import java.io.*;
import java.util.*;


public class Dominos {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        

        for (int i = 0; i < n; ++i) {
            int noofDominos = io.getInt();
            int m = io.getInt();

            // storage of (Dominos) vertices into graph 
            Graph record = new Graph(noofDominos);

            // addition of the dominos into graph
            for (int j = 0; j < m; ++j) {
                int cause = io.getInt();
                int effect = io.getInt();
            
                record.addEdge(cause-1, effect-1);
            }

            // topoSort the graph first using Stack to get topo order 
            record.dfsTopoSort();

            // perform dfs according to toposort
            int answer = record.calculateMin();
            io.println(answer);
        }
        io.close();
    }
}

class Graph {
    int[] visited;
    ArrayList<ArrayList<Integer>> adjacentList;
    int[] toposorted;
    int n;
    Stack<Integer> stack;

    public Graph(int n) {
        this.adjacentList = new ArrayList<>(n);
        this.toposorted = new int[n];
        this.n = n;
        this.visited = new int[n];

        for (int i = 0; i < n; ++i) {
            adjacentList.add(new ArrayList<>());
        }
        this.stack = new Stack<>();
    }

    void addEdge(int v, int u) {
        adjacentList.get(v).add(u);
    }

    // recursively check ALL dominos visited == true 
    void dfsTopoSort() {
        for (int i = 0; i < n; ++i) {
            if (visited[i] == 0) {
                visited[i] = 1;
                dfswStack(i);
            }
        }
    }
    
    // recursively check if dominos are connected by source domino, if yes visited = true
    void dfswStack(int v) {
        visited[v] = 1;
        for (int i = 0; i < adjacentList.get(v).size(); ++i){
            int otherVertice = adjacentList.get(v).get(i);
            if (visited[otherVertice] == 0) {
                visited[otherVertice] = 1;
                dfswStack(otherVertice);

            }
        }
        stack.push(v);
    }

    void dfs(int v) {
        visited[v] = 1;
        for (int i = 0; i < adjacentList.get(v).size(); ++i){
            int otherVertice = adjacentList.get(v).get(i);
            if (visited[otherVertice] == 0) {
                visited[otherVertice] = 1;
                dfs(otherVertice);
            }
        }
    }

    int calculateMin() {
        visited = new int[n];
        int count = 0;
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (visited[v] == 0) {
                visited[v] = 1;
                dfs(v);
                count++;
            }
        }
        return count;
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