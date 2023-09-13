import java.util.*;

public class SortofSorting1 {
    // variables
    private static ArrayList<String> names = new ArrayList<String>();
    private static ArrayList<Integer> values = new ArrayList<Integer>();

    public static void swap(ArrayList<String> array, int indexWord1, int indexWord2) {
        String intermediate = array.get(indexWord1);
        array.set(indexWord1, array.get(indexWord2));
        array.set(indexWord2,intermediate);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        while (true) {
            ArrayList<String> temporary = new ArrayList<String>();
            int count = scan.nextInt();

            if (count == 0){
                break;
            }
            values.add(count);

            
            
            for (int i = 0; i < count; i++) {
                temporary.add(scan.next());
            }
            
            while (true) {
                
                boolean swapped = false;
                for (int indexWords = 0; indexWords < temporary.size()-1; indexWords++) {
                    String s1 = temporary.get(indexWords);
                    String s2 = temporary.get(indexWords+1);

                    for (int indexLetter = 0; indexLetter < 2; indexLetter++) {
                        int test1 = s1.charAt(indexLetter);
                        int test2 = s2.charAt(indexLetter);
                        if (test1 < test2)
                            break;
                        if (test1 == test2) {
                            continue;
                        }
                        if (test1 > test2) {
                            swap(temporary, indexWords, indexWords+1);
                            swapped = true;
                            break;
                        }
                    }
                }
                
                if (!swapped){
                    // System.out.println("\"not-swapped\" condition met");
                    break;
                }
            }
            
            for (String name: temporary) {
                names.add(name);
            }
        }

        int helper = 0;

        for (int i = 0; i < values.size(); i++) {
            int value = values.get(i);

            for (int j = helper; j < value+helper; j++) {
                if (j == value+helper-1) {
                    System.out.print(names.get(j));
                    break;
                }
                System.out.println(names.get(j));
            }

            if (i == values.size()-1) 
                break;
            
            System.out.println();
            System.out.println();
            helper = value+helper;
        }
        scan.close();
    }
}