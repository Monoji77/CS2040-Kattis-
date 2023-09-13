import java.io.*;
import java.util.*;


public class Main {
    static double cannonRange = 50, cannonTime = 2, runningSpeed = 5; // m/s 
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        double startX = io.getDouble();
        double startY = io.getDouble();
    
        double endX = io.getDouble();
        double endY = io.getDouble();
    
        int noofCannons = io.getInt();

        double[][] cannonPosition = new double[noofCannons][2];

        for (int i = 0; i < noofCannons; i++) {
            cannonPosition[i][0] = io.getDouble();
            cannonPosition[i][1] = io.getDouble();
        }

        // Adjacency Matrix to store all weight of edges
        double[][] timeTakenMatrix = new double[noofCannons+2][noofCannons+2];
        
        for (int row = 0; row < noofCannons+2; row++) {
            for (int col = 0; col < noofCannons + 2; col++) {
                // initiate
                double  TotaldistanceStarttoCannon = 0, 
                        TotaldistanceCannontoCannon = 0, TotaldistanceCannontoStart = 0,
                        TotaldistanceCannontoEnd = 0, TotaldistanceEndtoCannon = 0,
                        timeTaken = 0, distancetoRun = 0;
                
                // no time taken to travel from vertex i to vertex i
                if (row == col) {
                    timeTakenMatrix[row][col] = 0;
                }
                // first row 
                else if (row == 0) {
                    if (col > 0 && col < noofCannons+1) {
                        // can only run to first cannon
                        TotaldistanceStarttoCannon = Math.sqrt(Math.pow(startX-cannonPosition[col-1][0],2) + 
                                                                Math.pow(startY-cannonPosition[col-1][1],2));
                        timeTaken = TotaldistanceStarttoCannon / runningSpeed;
                        timeTakenMatrix[row][col] = timeTaken;
                    }
                    else {
                        TotaldistanceStarttoCannon = Math.sqrt(Math.pow(startX-endX,2) + 
                                                            Math.pow(startY-endY,2));
                        timeTaken = TotaldistanceStarttoCannon / runningSpeed;
                        timeTakenMatrix[row][col] = timeTaken;
                    }
                }
                // not start or end vertice
                else if (row > 0 && row < noofCannons+1){
                    // cannon to start vertice
                    if (col == 0) {
                        TotaldistanceCannontoStart = Math.sqrt(Math.pow(cannonPosition[row-1][0]-startX,2) +
                                                        Math.pow(cannonPosition[row-1][1]-startY,2));
                        timeTaken = 2; distancetoRun = 0;
                        if (TotaldistanceCannontoStart > cannonRange) {
                            distancetoRun = TotaldistanceCannontoStart - cannonRange;
                            timeTaken += distancetoRun / runningSpeed;
                        }
                        timeTakenMatrix[row][col] = timeTaken; 
                    }
                    
                    // cannon to end vertice
                    else if (col == noofCannons+1) {
                        TotaldistanceCannontoEnd = Math.sqrt(Math.pow(cannonPosition[row-1][0]-endX,2) +
                                                        Math.pow(cannonPosition[row-1][1]-endY,2));
                        timeTaken = 2; distancetoRun = 0;
                        if (TotaldistanceCannontoEnd > cannonRange) {
                        distancetoRun = TotaldistanceCannontoEnd - cannonRange;
                        timeTaken += distancetoRun / runningSpeed;
                        }
                        timeTakenMatrix[row][col] = timeTaken;    
                    }
                    
                    // cannon to cannon
                    else {
                        TotaldistanceCannontoCannon = Math.sqrt(Math.pow(cannonPosition[row-1][0]-cannonPosition[col-1][0],2) +
                                                        Math.pow(cannonPosition[row-1][1]-cannonPosition[col-1][1],2));
                        timeTaken = 2; distancetoRun = 0;
                        if (TotaldistanceCannontoCannon > cannonRange) {
                        distancetoRun = TotaldistanceCannontoCannon - cannonRange;
                        timeTaken += distancetoRun / runningSpeed;
                        }
                        timeTakenMatrix[row][col] = timeTaken;  
                    }                       
                }

                // end vertice
                else if (row == noofCannons + 1) { // end vertice to the rest can only run          
                    // run from end to start (already solved)
                    if (col == 0) {
                        timeTakenMatrix[row][col] = timeTakenMatrix[col][row];
                    }

                    else { // run from end to cannon
                        TotaldistanceEndtoCannon = Math.sqrt(Math.pow(endX-cannonPosition[col-1][0],2) + 
                                                            Math.pow(endY-cannonPosition[col-1][1],2));
                        timeTaken = TotaldistanceEndtoCannon / 5;
                        timeTakenMatrix[row][col] = timeTaken;
                    }
                }
            }
        }

        double[] finalWeight = new double[noofCannons+2];
        Arrays.fill(finalWeight, Double.MAX_VALUE);
        finalWeight[0] = 0;

        PriorityQueue<IntegerPair> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[noofCannons + 2];
        pq.add(new IntegerPair(0,0));

        while (!pq.isEmpty()) {
            IntegerPair curr = pq.poll();
            int currVertice = curr.vertice;

            if (!visited[currVertice]) {
                visited[currVertice] = true;
                // System.out.println(count + " iteration (" + currVertice +")... ");
                for (int neighbour = 0; neighbour < noofCannons + 2; neighbour++) {
                    if (timeTakenMatrix[currVertice][neighbour] + 
                    finalWeight[currVertice] < finalWeight[neighbour] &&
                    !visited[i] && neighbour != currVertice) {
                        
                        double timetoAdd = timeTakenMatrix[currVertice][neighbour] + finalWeight[currVertice];
                        finalWeight[neighbour] = timetoAdd;
                        
                        pq.add(new IntegerPair(finalWeight[neighbour], neighbour));
                    }
                }
            }
        }
        io.println(finalWeight[noofCannons+1]);
        io.close();
    }
}

class IntegerPair implements Comparable<IntegerPair> {
    public double timeWeight;
    public int vertice;

    public IntegerPair(double weight, int index) {
        timeWeight = weight;
        vertice = index;
    }

    public int compareTo(IntegerPair other) {
        return Double.compare(this.timeWeight, other.timeWeight);
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
