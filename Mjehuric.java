import java.util.*;

public class Mjehuric {
    static Boolean escape = false;
    static Boolean isSwapped = false;

    static void swap(Integer[] array, int index1, int index2) {
        int intermediate = array[index1];
        array[index1] = array[index2];
        array[index2] = intermediate;
        isSwapped = true;

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Integer[] sequence = new Integer[5];

        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = scan.nextInt();
        }

        while (!escape) {
            
            // swap
            for (int i = 0; i < sequence.length-1; i++) {
                if (sequence[i] > sequence[i+1]) {
                    swap(sequence, i, i+1);
                }
            }

            if (!isSwapped) {
                escape = true;
                break;
            }

            isSwapped = false;
        }
        scan.close();
    }
}