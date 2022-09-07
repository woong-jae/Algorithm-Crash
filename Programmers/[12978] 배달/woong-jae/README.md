# [12978] 배달

## Algorithm

- BFS
- Naive Dijkstra

## Logic

한 점에서 모든 점까지 최소 거리를 구하면 된다. 다익스트라 알고리즘을 사용했다.

우선순위 큐를 사용해야 더 효율적이지만, 주어진 N의 크기가 그렇기 크지 않아 일반적인 큐를 사용했다.

```js
const q = [[1, 0]];
while (q.length) {
  const [cur, accDist] = q.shift();
  if (accDist > dist) continue;

  adjList[cur].forEach(([next, w]) => {
    if (accDist + w >= dist[next]) return;
    dist[next] = accDist + w;
    q.push([next, accDist + w]);
  });
}
```

## Review
쉬운 문제. 다익스트라 알고리즘을 최적화하지 않아도 통과되는 문제여서 더 쉬웠다.
