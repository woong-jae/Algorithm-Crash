import java.util.*;

class TrieNode {

    private int count;
    private boolean last;
    private Map<Character, TrieNode> childNodes = new HashMap<>();

    public Map<Character, TrieNode> getChildNodes() {
        return this.childNodes;
    }

    public int getCount() {
        return this.count;
    }

    public void increaseCount() {
        this.count++;
    }

    public boolean isLast() {
        return this.last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        int len = word.length();
        TrieNode node = this.root;

        for (int i = 0; i < len; i++) {
            node.increaseCount();
            node = node.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
        }

        node.setLast(true);
    }

    public boolean contains(String word) {
        int len = word.length();
        TrieNode node = this.root;

        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            TrieNode trieNode = node.getChildNodes().get(c);

            if(trieNode == null)
                return false;
            node = trieNode;
        }
        return node.isLast();
    }

    public int count(String keyword) {
        int len = keyword.length();
        TrieNode node = this.root;

        int count = 0;

        for (int i = 0; i < len; i++) {
            char c = keyword.charAt(i);
            if(c == '?')
                return node.getCount();
            TrieNode trieNode = node.getChildNodes().get(c);
            count = trieNode.getCount();
            node = trieNode;
        }
        return count;
    }
}

class Solution {
    public int[] solution(String[] words, String[] queries) {
        int n = queries.length;
        int[] answer = new int[n];
        Trie[] front = new Trie[10001];
        Trie[] back = new Trie[10001];

        for (String word : words) {
            int len = word.length();

            if (front[len] == null)
                front[len] = new Trie();
            front[len].insert(word);

            String reversed = new StringBuffer(word).reverse().toString();
            if (back[len] == null)
                back[len] = new Trie();
            back[len].insert(reversed);
        }

        for (int i = 0; i < n; i++) {
            String query = queries[i];
            int count = 0, len = query.length();

            try {
                if (query.charAt(0) == '?') {
                    query = new StringBuffer(query).reverse().toString();
                    count = back[len].count(query);
                } else
                    count = front[len].count(query);
            } catch (NullPointerException ignored) {
            }
            answer[i] = count;
        }
        return answer;
    }
}