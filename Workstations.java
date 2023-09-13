import java.io.*;
import java.util.*;

public class Workstations {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] s1 = reader.readLine().split(" ");

        // n = no. of researchers
        // m = no. of min of inactivity
        int n = Integer.parseInt(s1[0]);
        int m = Integer.parseInt(s1[1]);

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        PriorityQueue<Researcher> researchers = new PriorityQueue<Researcher>(new ResearcherComparator());    
        
            
        for (int i = 0; i < n; i++) {
            s1 = reader.readLine().split(" ");
            int a =  Integer.parseInt(s1[0]);
            int b = Integer.parseInt(s1[1]);

            Researcher toAdd = new Researcher(a, b);
            researchers.add(toAdd);
        }
        int unlockedCount = 0;
        
        while(!researchers.isEmpty()) {
            Researcher A = researchers.poll();
            if (pq.isEmpty()) {
                pq.add(A.arrivalTime+A.stayTime);
                unlockedCount++;
            }
            
            else {
                while (!pq.isEmpty() && A.arrivalTime > pq.peek() + m) {
                    pq.poll();
                }
                if (!pq.isEmpty() && A.arrivalTime <= pq.peek() + m && A.arrivalTime >= pq.peek()) {
                    pq.poll();
                    }
                else {
                    unlockedCount++;
                }
                pq.add(A.arrivalTime+A.stayTime);
            }
        }   
        System.out.println(n-unlockedCount);
        reader.close();
    }
}

class Researcher {
    int arrivalTime;
    int stayTime;
    
    public Researcher(int taketoArrive, int staying) {
        this.arrivalTime = taketoArrive ;
        this.stayTime = staying;
    }
}

class ResearcherComparator implements Comparator<Researcher> {
    @Override
    public int compare(Researcher a1, Researcher a2) {
        int i = a1.arrivalTime - a2.arrivalTime;

        if (i == 0) {
            return a1.stayTime - a2.stayTime;
        }
        return i;
    }
}




