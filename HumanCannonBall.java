import java.io.*;
import java.util.*;

public class HumanCannonBall {

    static int cannonRange = 50, cannonTimeTaken = 2;
    static int runningSpeed = 5; // 5m/s

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        double startX = io.getDouble();
        double startY = io.getDouble();
    
        double endX = io.getDouble();
        double endY = io.getDouble();
    
        int noofCannons = io.getInt();


        // col 0 = x-coordinate; col 1 = y-xoordinate
        double[][] cannonPos = new double[noofCannons][2];

        for (int i = 0; i < noofCannons; ++i) {
            cannonPos[i][0] = io.getDouble();
            cannonPos[i][1] = io.getDouble();
        }
        
        // the whole graph including start and end destination
        // stored value will be the time taken from vertex to vertex
        double[][] adjacencyMatrix = new double[noofCannons + 2][noofCannons+2];
        

        // shortest distance to reach by walking from start to end
        double hypotenuse = Math.sqrt(Math.pow(startX-endX,2) + Math.pow(startY-endY,2));

        System.out.println("hypotenuse == " + hypotenuse);
        
        // time taken to run a direct path to final destination (might not be the fastest)
        double timeTaken = hypotenuse / runningSpeed;

        for (int row = 0; row < noofCannons+2; row++) {
            Arrays.fill(adjacencyMatrix[row], Double.MAX_VALUE);
        }

        adjacencyMatrix[noofCannons+1][noofCannons+1] = timeTaken;
        adjacencyMatrix[0][0] = 0;

        // create a graph that connects start to all cannons (ONLY RUN) [FIRST ROW (from index i=0 to j=k)]
        for (int col = 0; col < noofCannons; ++col) {   
            double distanceStarttoCannon = Math.sqrt(Math.pow(startX-cannonPos[col][0],2) + Math.pow(startY-cannonPos[col][1], 2));
            double timeTakentoRun = distanceStarttoCannon / runningSpeed;
            adjacencyMatrix[0][col+1] = timeTakentoRun;    
        }

        for (int cannonA = 0; cannonA < noofCannons; ++cannonA) {
            for (int cannonB = 0; cannonB < noofCannons; ++cannonB) {
                if (cannonA == cannonB) {
                    adjacencyMatrix[cannonA+1][cannonB+1] = 0;
                    continue;
                }
                // obtain distance from cannon i to cannon j
                double distanceCannonAtoCannonB = Math.sqrt(Math.pow(cannonPos[cannonA][0]-cannonPos[cannonB][0],2) +
                                                                     Math.pow(cannonPos[cannonA][1]-cannonPos[cannonB][1], 2));
                adjacencyMatrix[cannonA+1][cannonB+1] = 2;
            }
        }

        for (int cannonA = 0; cannonA < noofCannons+2; ++cannonA) {
            for (int cannonB = 0; cannonB < noofCannons+2; ++cannonB) {
                System.out.print(adjacencyMatrix[cannonA][cannonB] + " ");
            }
            System.out.println();
        }
        
        PriorityQueue<IntegerPair> pq = new PriorityQueue<>();
        
        // enqueue all Integer Pairs (time taken edge + vertice) into pq
        for (int i = 1; i < noofCannons+1; ++i) {
            pq.add(new IntegerPair(Double.MAX_VALUE, i));
        }

        boolean[] visited = new boolean[noofCannons + 2];

        // enqueue starting location
        pq.add(new IntegerPair(0,0));
        
        while (!pq.isEmpty()) {
            IntegerPair intPair = pq.poll();
            if (!visited[intPair.index]) {
                visited[intPair.index] = true;
                for (int i = 1; i < noofCannons+1; ++i) {
                    pq.add(new IntegerPair(adjacencyMatrix[intPair.index][i], i));
                }
            }
        }
        io.close();
    
    }
}

class IntegerPair implements Comparable<IntegerPair> {
    double time;
    int index;
    public IntegerPair(double time, int index) {
        this.time = time;
        this.index = index;
    }

    public int compareTo(IntegerPair other) {
        return Double.compare(this.time, other.time);
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