# [1504] 특정한 최단 경로

## Algorithm

- 플로이드-와샬

## Logic

플로이드 와샬 알고리즘으로 모든 노드 모든 최단거리를 구한다.

그후 v1을 먼적 거치는 거리와 v2를 먼저 거치는 거리중 더 짧은 것을 반환한다.

```js
for (let k = 0; k < N; k++) {
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
    }
  }
}

const result = Math.min(
  dist[0][v1] + dist[v1][v2] + dist[v2][N - 1],
  dist[0][v2] + dist[v2][v1] + dist[v1][N - 1]
);
```

## Review
쉬운 문제.
