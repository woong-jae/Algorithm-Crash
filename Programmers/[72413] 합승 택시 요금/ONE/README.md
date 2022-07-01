# [72413] 합승 택시 요금
## Algorithm
- **Floyd-Warshall**

## Logic
- S 지점부터 어느 한지점까지 같이 간 후, A & B 로 갈라진다
- 플로이드 워셜 알고리즘으로 각각의 노드에서 다른 노드들 까지의 거리를 구한 후
- S에서 어느지점까지와 어느지점에서 A & B 까지의 거리의 최솟값을 구한다

```java
for (int k = 1; k <= n; k++)
    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= n; j++)
            if(weights[i][j] > weights[i][k] + weights[k][j])
                weights[i][j] = weights[i][k] + weights[k][j];

for (int i = 1; i <= n; i++)
    answer = Math.min(weights[s][i] + weights[i][a] + weights[i][b], answer);
```

## Review
플로이드 워셜 알고리즘을 써야한다는게 금방 생각은 나서 구현은 빨리 한거같은데  
처음에 노드사이의 거리를 Integer.MAX_VALUE 로 설정했는데 계속 다르게 나와  
질문을 참고해보니 최대값을 100001*n 정도로 설정해야 올바른 답이 나온다는 걸 알게 되었다  
까비!
