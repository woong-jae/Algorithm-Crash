import java.util.*;

class TrieNode {
    private Map<Character, TrieNode> childNodes = new HashMap<>();
    int count;

    // 자식 노드 맵 Getter
    public Map<Character, TrieNode> getChildNodes() {
        return this.childNodes;
    }
}

class Trie {
    // 트라이를 정방향, 뒷방향으로 2개 생성
    // 접두사가 ?인 경우는 탐색해야 하는 경우가 많고 이때문에 시간 초과 발생...
    private TrieNode forward;
    private TrieNode backward;

    public Trie() {
        forward = new TrieNode();
        backward = new TrieNode();
    }

    public void insert(String word) {
        insertBackward(word);
        insertForward(word);
    }

    // 주어진 문자의 뒷부분부터 Trie 구조 생성, count 계산
    private void insertBackward(String word) {
        TrieNode node = backward;

        for (int i = word.length() - 1; i >= 0; i--) {
            node.count++;
            char ch = word.charAt(i);
            Map<Character, TrieNode> childNodes = node.getChildNodes();
            childNodes.putIfAbsent(ch, new TrieNode());
            node = childNodes.get(ch);
        }
    }

    // 주어진 문자의 앞부분부터 Trie 구조 생성, count 계산
    private void insertForward(String word) {
        TrieNode node = forward;

        for (int i = 0; i < word.length(); i++) {
            node.count++;
            char ch = word.charAt(i);
            Map<Character, TrieNode> childNodes = node.getChildNodes();
            childNodes.putIfAbsent(ch, new TrieNode());
            node = childNodes.get(ch);
        }
    }

    // 접두사가 ?라면 뒷방향 Trie로부터 count
    // 그렇지 않다면 정방향 Trie로부터 count
    public int getCount(String query) {
        return query.charAt(0) == '?' ? getCountBackward(query) : getCountFarward(query);
    }

    private int getCountBackward(String query) {
        TrieNode node = backward;

        for (int i = query.length() - 1; i >= 0; i--) {
            char ch = query.charAt(i);
            if (ch == '?') return node.count;
            Map<Character, TrieNode> childNodes = node.getChildNodes();
            if (!childNodes.containsKey(ch)) return 0;
            node = childNodes.get(ch);
        }

        return node.count;
    }

    private int getCountFarward(String query) {
        TrieNode node = forward;

        for (int i = 0; i < query.length(); i++) {
            char ch = query.charAt(i);
            if (ch == '?') return node.count;
            Map<Character, TrieNode> childNodes = node.getChildNodes();
            if (!childNodes.containsKey(ch)) return 0;
            node = childNodes.get(ch);
        }

        return node.count;
    }
}

class Solution {
    public int[] solution(String[] words, String[] queries) {
        // 문자열 길이를 인덱스로 하는 Trie 배열 선언
        Trie[] trie = new Trie[100001];
        int[] answer = new int[queries.length];
        
        // 문자열 길이를 기준으로 배열 초기화 후, trie 생성
        for (String word : words) {
            int len = word.length();
            if (trie[len] == null) trie[len] = new Trie();
            trie[len].insert(word);
        }

        // query 길이에 해당하는 trie 배열 값이 없다면 해당하는 문자가 없다는 의미,
        // 있다면 개수 count
        for (int i = 0; i < queries.length; i++) {
            int len = queries[i].length();
            if (trie[len] == null) answer[i] = 0;
            else answer[i] = trie[len].getCount(queries[i]);
        }
        
        return answer;
    }
}