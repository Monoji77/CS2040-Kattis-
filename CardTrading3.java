// Chris Yong Hong Sen A0257899X - this took me way to long to figure out that 
// I could implement a comparator which makes it easier to sort.


import java.util.*;

public class CardTrading3 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int t = scan.nextInt();
        int k = scan.nextInt();

        // Storing variables
        Card[] deck = new Card[t+1];
        int[] cardCount = new int[t+1];
        
        for (int i = 0; i < n; i++) {
            cardCount[scan.nextInt()]++;
        }

        long buyVal, sellVal;
        for (int i = 1; i <= t; i++) {
            buyVal = scan.nextLong();
            sellVal = scan.nextLong();
            Card cardtoAdd = new Card(i, buyVal, sellVal, cardCount[i]);
            deck[i] = cardtoAdd;
        }

        Arrays.sort(deck, new CostComparator());
        
        long answer = 0;

        
        
        // buy k smallest true value cards
        for (int i = 1; i <= k; i++) {
            answer -= (deck[i].getBuy() * (2-deck[i].getCount()));
            // System.out.println("chosen card: " + deck[i].getId() + " || answer: " + answer);
        }

        // sell t-k remaining cards
        for (int i = k+1; i <= t; i++) {
            answer += (deck[i].getSell() * deck[i].getCount());
            // System.out.println("chosen card: " + deck[i].getId() + " || answer: " + answer);
        }

        System.out.println(answer);
        scan.close();
    }    
}

class Card {
    private int id;
    private long buy;
    private long sell;
    private int count;
    

    public Card(int id, long buy, long sell, int count) {
        this.id = id;
        this.buy = buy;
        this.sell = sell;    
        this.count = count;
    }

    public int getId() { return this.id; }
    public long getSell() { return this.sell; }
    public long getBuy() { return this.buy; }
    public int getCount() { return this.count; }
    
    public long calctrueValue() {
        long trueValue;
        if (this.count == 2) {
            trueValue = this.getCount() * this.getSell();             
        }
        else if (this.count == 1) { 
            trueValue = this.getSell() + this.getBuy();
        }
        else  {// if this.count == 0
            trueValue = this.getBuy() * 2;
        }
        return trueValue;
    }
}

class CostComparator implements Comparator<Card> {

    // method override
    public int compare(Card card1, Card card2) {
        if (card1 != null && card2 != null) {
            return (int) (card1.calctrueValue() - card2.calctrueValue()); 
        }
        else
            return 0;
    }
}
