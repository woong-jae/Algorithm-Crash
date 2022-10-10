# [1238] 파티

## Algorithm

- Floyd

## Logic

플로이드 와샬 알고리즘으로 모든 노드 최소 거리를 구한 후, `[from][X] + [X][from]`의 거리가 가장 큰 것을 찾는다.

```js
let max_dist = -1;
for (let i = 0; i < N; i++) {
  max_dist = Math.max(max_dist, dist[i][X - 1] + dist[X - 1][i]);
}
return max_dist;
```

## Review

플로이드 알고리즘을 알고 있다면 쉽게 풀 수 있는 문제.
