// https://www.geeksforgeeks.org/find-first-non-repeating-character-stream-characters/

import java.util.HashMap;
import java.util.LinkedList;

public class FirstNonRepeatChar {

    enum Freq{
        One,
        Repeated
    };

    public static void main(String args[]) {
        printFirstNonRepeatChars("aabcbddefc");
    }

    public static void printFirstNonRepeatChars(String str) {
        HashMap<Character, Freq> charFreq = new HashMap<>();
        LinkedList<Character> firstCharList = new LinkedList<>();
        for(char ch: str.toCharArray()) {
            if(charFreq.containsKey(ch) == false) {
                firstCharList.add(ch);
                charFreq.put(ch, Freq.One);
            }
            else {
                charFreq.put(ch, Freq.Repeated);
            }
            while(firstCharList.isEmpty() == false) {
                char firstCh = firstCharList.peekFirst();
                if(charFreq.get(firstCh) == Freq.Repeated) {
                    firstCharList.removeFirst();
                }
                else {
                    break;
                }
            }
            if(firstCharList.isEmpty() == false) {
                System.out.print(firstCharList.peekFirst() + " ");
            }
            else {
                System.out.print("-1 ");
            }
        }

    }
}
