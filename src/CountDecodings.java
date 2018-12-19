// https://www.geeksforgeeks.org/count-possible-decodings-given-digit-sequence/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class CountDecodings {


    public static void main(String args[]) {
        Set<String> decodings = new TreeSet<>();
        System.out.println(countDecodings("1072110", 0, "", decodings));
        System.out.println(decodings);
    }

    public static int countDecodings(String str, int curIdx, String curDecoding, Set<String> decodings) {
        int n = str.length();
        if(curIdx > n) // invalid path
            return 0;
        if(curIdx == n) { // exhausted exactly, or
            decodings.add(curDecoding);
            return 1;
        }
        if(str.charAt(curIdx) == '0') // No decoding is possible with a string starting at 0
            return 0;
        if(curIdx == n-1) { // just one number left
            curDecoding = curDecoding + str.charAt(curIdx);
            decodings.add(curDecoding);
            return 1;
        }
        int ways = countDecodings(str, curIdx+1, curDecoding + str.charAt(curIdx) + " ", decodings);
        if(str.charAt(curIdx) <= '2' && str.charAt(curIdx+1) <= '6') {
            ways += countDecodings(str, curIdx+2,
                    curDecoding + str.charAt(curIdx) + str.charAt(curIdx+1) + " ",
                    decodings);
        }
        return ways;
    }
}
