
import java.util.Scanner;

public class SampleInput {

    public static void main(String[] args) {

        // Use next() for space separated values; nextLine() to get the whole line in one shot

        // String Input
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println("String Input: " + s);

        // Integer Input
        Scanner inInt = new Scanner(System.in);
        String str = inInt.next();
        int num = Integer.parseInt(str);
        System.out.println("Integer Input: " + num);

        // Array of Integers Input
        Scanner inIntAr = new Scanner(System.in);
        int ar[] = new int[5];
        for(int i=0; i < ar.length; i++) {
            String strAr = inIntAr.next();
            ar[i] = Integer.parseInt(strAr);
        }
        for(int i=0; i < ar.length; i++) {
            System.out.println("Array Input: " + ar[i]);
        }
    }

}
