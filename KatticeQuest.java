import java.io.*;
import java.util.*;

public class KatticeQuest {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        String[] s;
        String operator;
        TreeMap<Quest, Integer> record = new TreeMap<Quest, Integer>();
        long energyConsump, goldReward;
        for (int i = 0; i < n; ++i) {
            s = reader.readLine().split(" ");
            operator = s[0];

            if (operator.equals("add")) {
                energyConsump = Long.parseLong(s[1]);
                goldReward = Long.parseLong(s[2]);
                if (record.containsKey(new Quest(energyConsump,goldReward))) {
                    record.put(new Quest(energyConsump, goldReward), record.get(new Quest(energyConsump,goldReward)) + 1);
                }
                else
                    record.put(new Quest(energyConsump, goldReward), 1);
            }
            
            if (operator.equals("query")) {
                if (record.size() == 0) {
                    System.out.println(0);
                    continue;
                }
                long gold = 0;
                long query = Long.parseLong(s[1]);
                
                // System.out.println("query: " + query);
                Quest key = record.floorKey(new Quest(query, Long.MAX_VALUE)); 
                // System.out.println("Chosen key's energy: " + key.energy);
                
                while (query > 0 && key != null) {
                    gold += key.gold;
                    query -= key.energy;
                    if (record.get(key) == 1) {
                        record.remove(key);
                    }
                    else 
                        record.put(key, record.get(key) - 1);
                    
                    key = record.floorKey(new Quest(query, Long.MAX_VALUE));
                    // System.out.println("query left: " + query + " || gold: " + gold + " || empty: " + record.isEmpty() 
                    //                     + "floor function: " + record.floor(new Quest(query, gold)));
                    
                }
                System.out.println(gold);
            }
        }
    }
}


class Quest implements Comparable<Quest> {
    long energy, gold;

    public Quest(long energy, long gold) {
        this.energy = energy;
        this.gold = gold;
    }

    @Override
    public int compareTo(Quest quest2) {
        if (this.energy != quest2.energy) {
            return Long.compare(this.energy, quest2.energy);
        }
        else    
            return Long.compare(this.gold, quest2.gold);
        
    }
}