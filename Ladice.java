import java.io.*;
import java.util.*;

public class Ladice {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int n = io.getInt();
        int l = io.getInt();

        UFDS drawers = new UFDS(l);

        int a, b;

        for (int i = 0; i < n; ++i) {
            // System.out.println("index: " + i);         
            a = io.getInt();
            b = io.getInt();
            
            drawers.union(a, b);
            int parent = drawers.getSet(a);
            if (drawers.numItems[parent] < drawers.size[parent]) {
                drawers.numItems[parent]++;
                io.println("LADICA");
            }
            else {
                io.println("SMECE");
            }
        }
        io.close();
    }
}

class UFDS {
    int[] parent;
    int[] rank;
    int[] size;
    int[] numItems;

    public UFDS(int n) {
        parent = new int[n+1];
        rank = new int[n+1];
        size = new int[n+1];
        numItems = new int[n+1];

        for (int i = 0; i <= n; ++i) {
            parent[i] = i;
            rank[i] = 0;
            size[i] = 1;
        }
    }

    int getSet(int i) {
        if (parent[i] == i) {
            return i;
        }
        else { 
            // path compression
            parent[i] = getSet(parent[i]);
            return parent[i];
            
        }
    }
    
    boolean isSameSet(int i, int j) {
        return getSet(i) == getSet(j);
    }

    // union by rank (ensure height of tree is minimised)
    void union(int i, int j) {
        // each union operation adds 1 item to the drawer
        // if number of items exceed drawer capacity, it is not possible to add anym items to the drawer
        if (!isSameSet(i, j)) {
            int x = getSet(i), y = getSet(j);
            if (rank[x] > rank[y]) {
                parent[y] = x;      
                size[x] += size[y];    
                numItems[x] += numItems[y];
            }
            else {
                parent[x] = y;
                if (rank[y] == rank[x]) {
                    rank[y]++;
                }
                size[y] += size[x];
                numItems[y] += numItems[x];
            }
        }    
        
            // mainset[get(i)] = get(j);
            // if (!filled[i]) {
            //     filled[i] = true;
            //     System.out.println("LADICA");
            // }
            // else if (filled[i] && !filled[j]) {
            //     filled[j] = true;
            //     System.out.println("LADICA");
            // }
            // else if (!filled[j]) {
            //     filled[j] = true;
            //     System.out.println("LADICA");
            // }
            // else if (!filled[get(i)]) {
            //     filled[get(i)] = true;
            //     System.out.println("LADICA");
            // }
            // else if (!filled[get(j)]) {
            //     filled[get(j)] = true;
            //     System.out.println("LADICA");
            // }
        // }
        
        // else {
            // if (!filled[j]) {
            //     filled[j] = true;
            //     System.out.println("LADICA");
            // }
            // else 
            //     System.out.println("SMECE");
        
        // System.out.print("Mainset seq: ");
        // for (i = 0; i < parent.length; ++i) {
        //     System.out.print(parent[i] + " ");
        // }

        // System.out.println();
        // System.out.print("rank seq:    ");
        // for (i = 0; i < parent.length; ++i) {
        //     System.out.print(rank[i] + " ");
        // }
        // System.out.println();
        // System.out.print("size seq:    ");
        // for (i = 0; i < parent.length; ++i) {
        //     System.out.print(size[i] + " ");
        // }
        // System.out.println();
        // System.out.print("noItems seq: ");
        // for (i = 0; i < parent.length; ++i) {
        //     System.out.print(numItems[i] + " ");
        //}
        // System.out.println();
        // xSystem.out.println();
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