# [60060] 가사 검색
## Algorithm
- Trie
## Logic
키워드는 무조건 "?"를 하나 이상 접두사나 접미사에 포함하고 있다.

만약 접미사에 "?"가 있다면 "?"가 나온 이후 만들 수 있는 단어의 개수를 알 수 있으면 매칭 수를 알 수 있다.
그래서 트라이를 구성할 때 한 노드에서 만들 수 있는 단어의 개수를 `count`로 저장한다.

```js
insert(word) {
    let cur = this.root;
    if(!cur.child.has(word.length)) cur.child.set(word.length, new Node());
    cur = cur.child.get(word.length);
    
    for(let char of word) {
        if(!cur.child.has(char)) cur.child.set(char, new Node());
        cur.count++;
        cur = cur.child.get(char);
    }
}
```

접두사에 나오는 경우는 단어를 뒤집어서 생각하면 접미사의 경우와 같다.
따라서 트리를 정방향과 역방향, 두 개를 만들어야 한다.

그리고 단어의 길이별로 트라이를 만들어야 정확한 `count` 수를 얻을 수 있다.

## Review
트라이를 사용해야 한다는 것은 알았는데 계속 효율성에서 통과를 못했다. 그래서 동규 코드를 참고했다.

이런 식으로 트라이를 응용하는 문제는 처음이여서 신박했다. 트라이는 여러 조건을 보고 세세하게 구성해야 효율성이 좋은 것 같다.
트라이 응용을 위한 좋은 문제인듯.