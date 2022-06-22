# [60060] 가사 검색
## Algorithm
- **Trie**

## Logic
- 입력 문자열 & Reverse 문자열을 Trie, Reverse Trie에 입력
  - 물음표가 앞에 오는 문자열은 뒤집어서 생각해야한다 그렇지 않으면 끝까지 가야 검사가 가능하기 때문
- Trie의 Count 메소드로 각 쿼리에 대한 count 값 answer 배열에 입력
- ?가 앞에 온 쿼리는 Reverse 처리 후, reverse Trie 를 통해 확인

```java
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
```

## Review
최근에 풀었던 문제중에 가장 어려웠던 문제  
내가 Trie 자료구조를 이용해 푸는데 익숙하지 않았던 것 같다  
처음에는 Trie 자료구조를 안쓰고 풀었는데 효율성 테스트의 마지막 2개 케이스가 메모리 초과가 뜨는거 보고  
내 아이디어로는 안되는 것 같아 다른 사람의 코드를 참고하여 풀었다  
이 구조는 많이 푸는 연습이 필요할 듯..