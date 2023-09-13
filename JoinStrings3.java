import java.io.*;
import java.util.*;

public class JoinStrings3 {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
    
        int n = io.getInt();

        List<ListofWords> recordofStrings = new ArrayList<ListofWords>(n);
        // keep a record of the Words
        
        for (int i = 1; i < n+1; ++i) {
            recordofStrings.add(new ListofWords(new Word(io.getWord())));
        }

        int a = 0, b = 0;
        for (int i = 1; i < n; ++i) {
            a = io.getInt() - 1;
            b = io.getInt() - 1;
        
            recordofStrings.get(a).concatList(recordofStrings.get(b));
        }        
        io.flush();
        
        Word manipulate = recordofStrings.get(a).getHead();
        StringBuilder answer = new StringBuilder();
        
        answer.append(manipulate);
        
        while (manipulate.getnextWord() != null) {
            manipulate = manipulate.getnextWord();
            answer.append(manipulate);
        }
        // System.out.print(recordofStrings[a].toString());
        System.out.println(answer);
        
    }
}

class Word {
    String word;
    Word nextWord;

    public Word(String s) {
        this.word = s;
        this.nextWord = null;
    }
    // Override
    public String toString() {
        return this.word;
    }

    Word getnextWord() {
        return this.nextWord;
    }
    void setNextWord(Word otherWord) {
            this.nextWord = otherWord;
    }
    boolean hasNextWord() {
        if (this.nextWord != null) { return true; }
        return false;
    }
}

class ListofWords {
    private Word head;
    private Word tail;

    public ListofWords(Word word) {
        this.head = word;
        this.tail = word;
    }

    Word getHead() {
        return this.head;
    }
    void concatList(ListofWords otherList) {

        // store next word in word itself 
        this.tail.setNextWord(otherList.head);

        // change tail to other list's tail
        this.tail = otherList.tail;
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