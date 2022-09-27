# [2098] 외판원 순회

## Algorithm
- DP
- 비트마스킹

## Logic

```java
static int tsp(int x, int check) {
    // 모든 도시 방문 완료한 경우
    if (check == statusFullBit) {
        // 그때의 x로부터 0으로 가는 비용이 0, 즉 갈 수 없는 경우인지 확인합니다.
        if (w[x][0] == 0) return INF;
        else return w[x][0];
    }

    // 이미 방문한 도시라면 그 도시까지의 비용을 반환합니다.
    if (dp[x][check] != -1) return dp[x][check];

    // 해당 도시에 방문했음을 표시
    // 두 번 방문하지 않도록 하기 위함입니다.
    dp[x][check] = INF;

    // 방문하지 않은 도시 탐색
    for (int i = 0; i < n; i++) {
        // i 도시 방문 표현
        int next = check | (1 << i);

        // 경로가 없거나 i 도시를 이미 방문했을 경우는 다음 도시 확인
        if (w[x][i] == 0 || (check & (1 << i)) != 0) continue;

        dp[x][check] = Math.min(dp[x][check], tsp(i, next) + w[x][i]);
    }

    return dp[x][check];
}
```

- `check` 로 전체 방문 완료 여부를 확인한다.
- 현재 x에서 도시 방문 현황이 check일 때, 나머지 도시들을 모두 방문한 후, 시점으로 돌아갈 때 드는 최소 비용을 갱신한다.

## :black_nib: **Review**

- 예 .. 하나도 모르겠어서 풀이 확인했습니다.