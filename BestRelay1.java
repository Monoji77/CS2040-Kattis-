import java.util.*;
import java.io.*;

public class BestRelay1 {
    public static void main(String[] args) throws Exception {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(scan.readLine());

        Runners[] runners = new Runners[n];
        String[] s;
        String runner; 
    
        Double firstLeg, secondLeg;
        for (int i = 0; i < n; i++) {
            s = scan.readLine().split(" ");
            runner = s[0];
            firstLeg = Double.parseDouble(s[1]);
            secondLeg = Double.parseDouble(s[2]);
            runners[i] = new Runners(runner, firstLeg, secondLeg); 
        }

        Arrays.sort(runners, new TimeComparator());
        
        LinkedList<Runners> bestGroup = new LinkedList<Runners>();
        double bestTiming = 1000;
        for (int i = 0; i < n; i++) {
            LinkedList<Runners> temporary = new LinkedList<Runners>();
            // get first
            double timingTrail = runners[i].getLeg1();
            temporary.add(runners[i]);

            int index = 0;

            // match with top 3 2nd legged runners
            while (temporary.size() != 4) {
                if (temporary.contains(runners[index])) {index++;}
                timingTrail += runners[index].getLeg2();
                temporary.add(runners[index]);
                // System.out.println(index);
                index++;
            }
            // System.out.println("index : " + i + " || Exited inner loop");
            
            if (timingTrail < bestTiming) {
                bestGroup = temporary;
                bestTiming = timingTrail;
            }
        }
        // System.out.println("Exited outer loop");
        System.out.println(bestTiming);
        for (int i = 0; i < 4; i++) {
            System.out.println(bestGroup.get(i).getName());
        }
        scan.close();

    }
}

class Runners {
    private Double leg1;
    private Double leg2;
    private String name;

    public Runners(String name, Double leg1, Double leg2) {
        this.leg1 = leg1;
        this.leg2 = leg2;
        this.name = name;
    }

    // methods 
    Double getLeg1() { return this.leg1; }
    Double getLeg2() { return this.leg2; }
    String getName() { return this.name; }

    Double calctotalTime() { return getLeg1()  + getLeg2(); }

}

class TimeComparator implements Comparator<Runners> {

    // method override
    public int compare(Runners runner1, Runners runner2) {
        if (runner1 != null && runner2 != null) {
            return runner1.getLeg2().compareTo(runner2.getLeg2());
        }
        else
            return 0;
    }
}