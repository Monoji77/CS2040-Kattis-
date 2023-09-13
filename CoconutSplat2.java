import java.util.*;
import java.io.*;



public class CoconutSplat2 {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        int s = io.getInt();
        int n = io.getInt();

        Deque<Player> playSequence = new LinkedList<>();    
        for (int i = 0; i < n; ++i) {
            playSequence.add(new Player(i));
        }

        // set count n*2 since maximum cases involve 2 fists / 2 palm per participant
        int count = n * 2, numofSyl = 0;
        Player inter;
        
        while (count != 1) {
            numofSyl = 1;

            // loop until numofSyl hits the participant, participant should be index 0 
            while (numofSyl++ < s) {
                inter = playSequence.removeFirst();
                playSequence.addLast(inter);
            }
            inter = playSequence.removeFirst(); 
            
            // update Player at index 0
            inter.update();

            // create helper player for addition of another hand, set it to be exactly the same state as original participant object
            Player helper = new Player(inter.getIndex(), inter.getHand1(), inter.getFolded(), inter.getLost());
            
            // Player got chosen 3 times
            if (inter.getLost()) { count--; }

            // Player got chosen 2 times, enqueue to let next player be the first index for numofSyl
            else if (inter.getHand1() == 2) { playSequence.addLast(inter); }

            // Player got chosen 1 time, add to the front of queue to let player's both fist be couonted for numofSyl 
            else if (inter.getHand1() == 1) {
                playSequence.addFirst(inter);
                playSequence.addFirst(helper);
            }
            
            //    Debugging (Ignore)
            // System.out.println("chosen: " + inter.getIndex());
            // System.out.print("Current seq: ");
            // LinkedList<Integer> testing = new LinkedList<>();
            // Queue<Player> testqueue = playSequence;
            // for (int i = playSequence.size()-1; i >= 0 ; i--) {
            //     Player b = testqueue.poll();
            //     testing.add(b.getIndex());
            //     testqueue.add(b);
            // }
            // System.out.println(testing);
        }
        System.out.println(playSequence.peek().getIndex() + 1);
        io.close();
    }
}

class Player {
    private Boolean folded, lost;
    private int index, hand1;
    
    // constructor
    public Player(int index) {
        this.index = index;
        this.hand1 = 0;
        this.folded = true;
        this.lost = false;
    }

    // constructor overload
    public Player(int index, int hand1, boolean folded, boolean lost) {
        this.index = index;
        this.hand1 = hand1;
        this.folded = folded;
        this.lost = lost;
    }

    boolean getLost() {
        return this.lost;
    }
    
    boolean getFolded() {
        return this.folded;
    }
    int getIndex() {
        return this.index;
    }
    int getHand1() {
        return this.hand1;
    }
    void update() {
        if (this.folded) {
            this.hand1++;
            this.folded = false;
        }
        else if(this.hand1 == 1) {
            this.hand1++;
        }
        else if (this.hand1 == 2) {
            this.lost = true; 
        }

        //
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