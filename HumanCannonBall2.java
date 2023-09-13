import java.io.*;
import java.util.*;

public class HumanCannonBall2 {
    public static double cannonDistance = 50, runningSpeed = 5;
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in,System.out);

        double startX = io.getDouble();
        double startY = io.getDouble();
    
        double endX = io.getDouble();
        double endY = io.getDouble();
    
        int noofCannons = io.getInt();

        double[][] x_yofCannons = new double[noofCannons][2];
        
        // get cannon positions
        for (int i = 0; i < noofCannons; ++i) {
            x_yofCannons[i][0] = io.getDouble();
            x_yofCannons[i][1] = io.getDouble();
        }

        double[][] timeTakenMatrix = new double[noofCannons+2][noofCannons+2];

        double distance, timeTaken;

        //  get time taken from start to end and end to start
        distance = Math.sqrt(Math.pow(startX-endX,2) + Math.pow(startY-endY,2));
        timeTaken = distance / runningSpeed;
        timeTakenMatrix[0][1] = timeTakenMatrix[1][0] = timeTaken;
        
        // get time taken between start to cannons and cannons to start
        for (int i = 0; i < noofCannons; ++i) {
            distance = Math.sqrt(Math.pow(startX-x_yofCannons[i][0],2) + Math.pow(startY-x_yofCannons[i][1],2));
            timeTaken = distance / runningSpeed; 
            timeTakenMatrix[0][i+2] = timeTaken;
            
            timeTaken = Math.abs(distance - cannonDistance) / runningSpeed;
            timeTakenMatrix[i+2][0] = timeTaken + 2;

        }

        // get time taken between end to cannons
        for (int i = 0; i < noofCannons; ++i) {
            distance = Math.sqrt(Math.pow(endX-x_yofCannons[i][0],2) + Math.pow(endY-x_yofCannons[i][1],2));
            timeTaken = distance / runningSpeed; 
        
            timeTakenMatrix[1][i+2] = timeTaken;

            timeTaken = Math.abs(distance - cannonDistance) / runningSpeed ;
            timeTakenMatrix[i+2][1] = timeTaken + 2;
        }
        
        // get time taken between cannon to everywhere else
        for (int i = 0; i < noofCannons; ++i) {
            for (int j = 0; j < noofCannons; ++j) {
                // System.out.println("i: " + i + " j: " + j);
                distance = Math.sqrt(Math.pow(x_yofCannons[i][0]-x_yofCannons[j][0],2) + 
                                    Math.pow(x_yofCannons[i][1]-x_yofCannons[j][1],2));
                timeTaken = Math.abs(distance - cannonDistance) / runningSpeed;
                timeTakenMatrix[i+2][j+2] = timeTakenMatrix[j+2][i+2] = timeTaken + 2;
            }
        }
        
        PriorityQueue<IntegerPair> pq = new PriorityQueue<>();

        pq.add(new IntegerPair(0, 0));

        double[] timeWeight = new double[noofCannons+2];
        boolean[] visited = new boolean[noofCannons+2];

        Arrays.fill(timeWeight, Double.MAX_VALUE);

        while (!pq.isEmpty()) {
            IntegerPair current = pq.poll();
            if (!visited[current.vertice]) {
                visited[current.vertice] = true;
                for (int neighbour = 0; neighbour < noofCannons + 2; neighbour++) {
                    if (!visited[neighbour] && 
                    timeWeight[neighbour] > current.time + timeTakenMatrix[current.vertice][neighbour] &&
                    neighbour != current.vertice) {
                        timeWeight[neighbour] = current.time + timeTakenMatrix[current.vertice][neighbour];
                        pq.offer(new IntegerPair(timeWeight[neighbour], neighbour));
                    }
                }
            }
        }
        // for (int i = 0; i < 6; ++i) {
        //     for (int j = 0; j < 6; ++j) {
        //         System.out.print(timeTakenMatrix[i][j] + "\t");
        //     }
        //     System.out.println();
        // }
        // System.out.println();
        // for (int i = 0; i < 6; ++i) {
        //     System.out.print(timeWeight[i] + "\t");
        // }
        // System.out.println();
        io.println(timeWeight[1]);
        io.close();
        
            
    }
}

class IntegerPair implements Comparable<IntegerPair> {
    public double time;
    public int vertice;

    public IntegerPair(double time, int vertice) {
        this.time = time;
        this.vertice = vertice;
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
