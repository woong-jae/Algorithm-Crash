# [17685] 자동완성
## Algorithm
- Trie
## Logic
트라이를 구성하면서 각 노드마다 몇 번 방문했는지 저장한다.

구성한 트라이를 통해 입력해야할 문자수를 구한다.
만약 현재 노드가 1번 방문됐다면 일치하는 단어가 1개라는 뜻이므로 바로 `answer`에 노드의 깊이를 더해주면 된다.

```js
words.forEach(word => {
    let cur = head, depth = 1;
    while(depth < word.length) {
        const next = word.slice(0, depth);
        cur = cur.childs.get(next);
        if(cur.count === 1) break;
        depth++;
    }
    answer += depth;
});
```

## Review
보자마자 트라이 문제인 것을 알았고 빨리 풀 수 있었다. 레벨 4가 맞나 싶을 정도로 쉬웠다.