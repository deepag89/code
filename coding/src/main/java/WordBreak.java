import java.util.HashSet;
import org.apache.commons.lang3.StringUtils;

public class WordBreak {
    
    public static void main(String[] args) {
        HashSet<String> dict = new HashSet<>();
        dict.add("cat");
        dict.add("cats");
        dict.add("sand");
        dict.add("and");
        dict.add("dog");
        
        System.out.println(doesWordBreak("catsanddog", dict));
        System.out.println(doesWordBreak("catsandog", dict));
        System.out.println(doesWordBreak("catanddog", dict));
        System.out.println(doesWordBreak("catandog", dict));
        System.out.println(doesWordBreak("and", dict));
        System.out.println(doesWordBreak("an", dict));
        System.out.println(doesWordBreak("", dict));
        System.out.println(doesWordBreak("catsandsanddogdogdogcatandandcatssandsanddog", dict));
    }
    
    private static boolean doesWordBreak(String word, HashSet<String> dict) {
        Trie trie = new Trie();
        for (String validWord : dict) {
            trie.addToTrie(validWord);
        }
        return doesWordBreak(word, trie);
    }
    
    private static boolean doesWordBreak(String word, Trie trie) {
        if (StringUtils.isEmpty(word)) {
            return true;
        }
        // TODO: use boolean dp for the current word status
        Trie.TrieNode cur = trie.root;
        for (int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (cur.children[ch - 'a'] == null) {
               return false;
            }
            cur = cur.children[ch-'a'];
            if (cur.isWord) {
                if(doesWordBreak(word.substring(i+1), trie)) {
                    return true;
                }
            }
        }
        return false;
    }
}
