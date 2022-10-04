# [1043] 거짓말

## Algorithm

- Disjoint Set

## Logic

파티를 노드, 사람을 간선으로 하는 인접 리스트를 생성한다.

```js
const personToParty = Array.from(Array(N + 1), () => []);
attends.forEach((people, partyIndex) => {
  people.forEach((person) => personToParty[person].push(partyIndex));
});
```

분리 집합으로 연결된 파티들을 구한다.

```js
const partyDisjointSet = new DisjointSet(M);
personToParty.forEach((parties) => {
  for (let from = 0; from < parties.length; from++) {
    for (let to = from + 1; to < parties.length; to++) {
      partyDisjointSet.union(parties[from], parties[to]);
    }
  }
});
```

진실을 알고 있는 사람들이 참여한 파티를 제외시킨다.

## Review
어떤 것을 기준으로 그래프를 표현하는지가 관건이었던 문제. 오랜만에 참신한 그래프 문제를 만났다.
