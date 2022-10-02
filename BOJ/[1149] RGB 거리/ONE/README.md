# [1149] RGB 거리
## Algorithm
- **DP**

## Logic
- 이전 집에서 같은 색이 아닌 최소의 값을 현재 위치의 집과 색에 해당하는 값에 더한

```java
for (int i = 0; i < N; i++) {
    st = new StringTokenizer(br.readLine());
    for (int j = 0; j < 3; j++)
        cost[i][j] = Integer.parseInt(st.nextToken());

    if (i == 0) continue;

    cost[i][0] = Math.min(cost[i][0] + cost[i - 1][1], cost[i][0] + cost[i - 1][2]);
    cost[i][1] = Math.min(cost[i][1] + cost[i - 1][0], cost[i][1] + cost[i - 1][2]);
    cost[i][2] = Math.min(cost[i][2] + cost[i - 1][0], cost[i][2] + cost[i - 1][1]);
}
```

## Review
힐링