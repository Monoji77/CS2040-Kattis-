import java.util.*;
import java.io.*;

public class PrimDemo {
  public static ArrayList < ArrayList < IntegerPair > > AdjList;
  public static ArrayList < Boolean > taken;
  public static PriorityQueue < IntegerPair > pq;

  public static void process(int vtx) {
    // System.out.println(">> At vertex " + vtx);
    taken.set(vtx, true);
    for (int j = 0; j < AdjList.get(vtx).size(); j++) {
      IntegerPair v = AdjList.get(vtx).get(j);
      if (!taken.get(v.second())) {
        pq.offer(v);  // we sort by weight then by adjacent vertex
      }
    }
  }
  public static void main(String[] args) throws Exception {
    /*
    // Sample graph shown in lecture
    5 7
    1 2 4
    1 3 4
    2 3 2
    1 4 6
    3 4 8
    1 5 6
    4 5 9
    */

    Kattio io = new Kattio(System.in, System.out);
    int n = io.getInt();
    AdjList = new ArrayList < ArrayList < IntegerPair > >();

    for (int i = 0; i < n; i++) {
      ArrayList < IntegerPair > Neighbor = new ArrayList < IntegerPair >(); // create vector of Integer pair 
      AdjList.add(Neighbor); // store blank vector first
    }
    
    for (int v = 0; v < n; v++) { // store graph information in Adjacency List
      // we decrease index by 1 to change input to 0-based indexing
      for (int u = 0; u < n; ++u) {
        int weight = io.getInt();
        if (weight == 0) {
          continue;
        }
        AdjList.get(v).add(new IntegerPair(v, u, weight)); 
      }
    }

    //check
    // for (int v = 0; v < n; v++) {
    //   for (int u = 0; u < AdjList.get(v).size(); ++u) {
        // System.out.print("(v: " + AdjList.get(v).get(u)._first + ", u: " +AdjList.get(v).get(u)._second + ") || ");
    //   }
    // }
    // System.out.println();

    taken = new ArrayList < Boolean >(); 
    taken.addAll(Collections.nCopies(n, false));
    pq = new PriorityQueue < IntegerPair > ();
    // take any vertex of the graph, for simplicity, vertex 0, to be included in the MST
    process(0);
    
    while (!pq.isEmpty()) { // we will do this until all V vertices are taken (or E = V-1 edges are taken)
      IntegerPair front = pq.poll();

      if (!taken.get(front.second())) { // we have not connected this vertex yet
        io.println(front._first +1 + " " + (front._second + 1));
        process(front.second());
      }
    }
    io.close();
  }
}

class IntegerPair implements Comparable<IntegerPair> {
  public Integer _first, _second, weight;

  public IntegerPair(Integer f, Integer s, Integer weight) {
    _first = f;
    _second = s;
    this.weight = weight;
  }

  public int compareTo(IntegerPair o) { 
    return this.weight - o.weight;
  }

  Integer first() { return _first; }

  Integer second() { return _second; }
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
