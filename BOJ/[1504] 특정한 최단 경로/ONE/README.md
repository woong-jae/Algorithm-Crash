# [2800] 괄호 제거
## Algorithm
- **Floyd-Warshall**

## Logic
- 시작점이 1 도착점이 N 이고 그 사이에 v1, v2를 포함해야한다
- 그러면 2가지 경우가 있다
  - 1 -> v1 -> v2 -> N
  - 1 -> v2 -> v1 -> N
- 각 노드간 거리의 최단 거리들을 구해 위의 경우 중에서 최솟값을 출력

```java
for (int k = 1; k <= N; k++)
    for (int i = 1; i <= N; i++)
        for (int j = 1; j <= N; j++)
            if (i != j && dist[i][k] != INF && dist[k][j] != INF)
                dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);

if (dist[v1][v2] == INF || dist[1][v1] == INF || dist[1][v2] == INF
        || dist[v1][N] == INF || dist[v2][N] == INF) {
    System.out.println(-1);
} else {
    System.out.println(Math.min(dist[1][v1] + dist[v2][N] + dist[v1][v2],
            dist[1][v2] + dist[v1][N] + dist[v1][v2]));
}
```

## Review
출발지와 도착지가 정해져있어 어렵지 않았던 문제