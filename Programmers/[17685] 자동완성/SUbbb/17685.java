import java.util.*;

class TrieNode {
    private Map<Character, TrieNode> childNodes = new HashMap<>();
    int size;

    public Map<Character, TrieNode> getChildNodes() {
        return this.childNodes;
    }
}

class Trie {
    private TrieNode rootNode;

    public Trie() {
        rootNode = new TrieNode();
    }

    public void insert(String word) {
        TrieNode thisNode = this.rootNode;

        for (int i = 0; i < word.length(); i++) {
            thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
            thisNode.size++;
        }
    }

    public int findCount(String word) {
        TrieNode thisNode = this.rootNode;
        int count = 0;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNode node = thisNode.getChildNodes().get(c);
            count++;

            if (node.size == 1) break;

            thisNode = node;
        }

        return count;
    }
}

class Solution {
    public int solution(String[] words) {
        Trie t = new Trie();
        
        for (String word : words)
            t.insert(word);
        
        int answer = 0;
        for (String word : words) 
            answer += t.findCount(word);
        
        return answer;
    }
}