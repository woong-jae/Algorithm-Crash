# [2800] 괄호 제거
## Algorithm
- **플로이드 워셜**

## Logic
- **플로이드 워셜**로 각 노드간 최단 거리를 구한 후 X까지 왕복거리들의 최댓값을 구한다

```java
for (int k = 1; k <= N; k++)
    for (int i = 1; i <= N; i++)
        for (int j = 1; j <= N; j++)
            if (i != j && dist[i][k] != INF && dist[k][j] != INF)
                dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);

for (int i = 1; i <= N; i++)
    answer = Math.max(answer, dist[i][X] + dist[X][i]);
```

## Review
어렵진 않지만 다익스트라로 풀면 더 적은 시간으로 구할 수 있을 것 같다  
다시 해보고 코드 교체 예정