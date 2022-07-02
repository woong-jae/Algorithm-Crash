# [72413] 합승 택시 요금
## Algorithm
- Floyd
## Logic
1. 플로이드 알고리즘으로 모든쌍 최단거리를 구한다.
2. 시작점부터 `i`, `i`부터 a와 b까지 거리합의 최솟값을 구한다.

```js
let answer = Infinity;
for(let i = 0; i < n; i++) {
    answer = Math.min(answer, dist[s - 1][i] + dist[i][a - 1] + dist[i][b - 1]);
}
```

## Review
문제를 보자마자 플로이드 알고리즘을 사용해야 된다는 것을 알아서 쉽게 풀 수 있었다.