import java.util.HashMap;

class MaxSubstringKDistinctChars {
    
    static int getMinWindowsWithKSum(String s, int k) {
        HashMap<Character, Integer> countOfChar = new HashMap<>();
        int maxWindowSize = Integer.MIN_VALUE;
        int winStart = 0;
        for (int cur = 0; cur < s.length(); cur++) {
            countOfChar.put(s.charAt(cur), countOfChar.getOrDefault(s.charAt(cur), 0) + 1);
            while (countOfChar.size() > k) {
                char charStart = s.charAt(winStart);
                if (countOfChar.get(charStart) == 1) {
                    countOfChar.remove(charStart);
                } else {
                    countOfChar.put(charStart, countOfChar.get(charStart) - 1);
                }
                winStart++;
            }
            maxWindowSize = Math.max(maxWindowSize, cur - winStart + 1);
            
        }
        
        return maxWindowSize;
    }
    
    public static void main(String[] args) {
        System.out.println(getMinWindowsWithKSum(new String("ebbebi"), 2));
    }
}