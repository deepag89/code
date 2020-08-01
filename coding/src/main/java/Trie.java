public class Trie {

  TrieNode root = new TrieNode();

  public class TrieNode {
    public boolean isWord;
    public TrieNode[] children = new TrieNode[26];
  }

  public void addToTrie(String word) {
    TrieNode cur = root;
    for (char ch : word.toCharArray()) {
      if (cur.children[ch - 'a'] == null)
        cur.children[ch - 'a'] = new TrieNode();
      cur = cur.children[ch - 'a'];
    }
    cur.isWord = true;
  }
}
