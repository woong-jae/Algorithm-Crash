# [60060] 가사 검색

## Algorithm

- trie

## Logic

```js
const forwardRoot = new Map();
const backwardRoot = new Map();
```

- 정방향, 역방향 trie root를 생성한다.

  - 각 root는 문자열의 길이를 key, trie를 value로 하는 map 객체이다.

<br />

```js
words.forEach((word) => {
  insertTrie(word, forwardRoot);
  insertTrie(word.reverse(), backwardRoot);
});
```

- 주어진 단어들을 각 trie에 삽입한다. (`insertTrie`)

  - 만약 각 root에 해당 단어의 길이 key가 없다면 { 단어의 길이: node } 형태의 데이터를 추가한다.

  - 해당 key의 node들을 순회하며 단어의 각 문자들을 삽입한다.

  - 이때, 순회하는 동시에 각 node의 `count` 프로퍼티에 +1 한다.

    - `count`는 해당 node 이후 만들어질 수 있는 모든 단어의 수를 말한다.

<br />

```js
queries.forEach((query, i) => {
  answer[i] = (query[0] !== '?')
    ? searchTrie(query, forwardRoot)
    : searchTrie(query.reverse(), backwardRoot);
});
```

- 주어진 쿼리들을 trie에 검색한다. (`searchTrie`)

  - 먼저 root에 해당 쿼리의 길이 key가 있는지 확인한다. 없다면 0을 반환한다.

  - 해당 key의 node들을 순회하며 쿼리의 각 문자들이 있는지 확인한다. 없다면 0을 반환한다. 

  - 쿼리의 '?' 이전까지의 문자들이 모두 있다면 해당 node의 `count`를 반환한다.

### 시간 복잡도: O(NlogN)

## Review

트라이는 한번도 사용해본적 없지만, 해당 문제를 읽고 트라이를 사용해야 한다는 것을 알았다.

정방향, 역방향 트라이를 각각 생성하는 방법 또한 떠올렸지만 효율성에서 자꾸만 틀렸다.

결국 다른 사람의 해결 방법을 참고했고, 각 단어의 길이별로 트라이를 생성하는 효율적인 방법을 알게되었다.

해당 방법은 정방향, 역방향 루트 노드에 100001개의 트라이를 가진 배열을 정의하여 각 인덱스를 문자의 길이로 이용하는 방법이었는데, 

이는 메모리 효율이 떨어진다 생각했고, 배열이 아닌 map을 사용하여 문자 길이별 트라이를 동적으로 생성하도록 변형했다.

트라이를 문제에 처음 적용해보아 유익했던 문제다.