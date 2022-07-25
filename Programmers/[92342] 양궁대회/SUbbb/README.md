# [92342] 양궁대회

## Algorithm
- DFS

## Logic

```java
private static void dfs(int cnt) {
    if (cnt == n + 1) {
        // 점수 계산
        int ryanScore = 0;
        int apeachScore = 0;
        
        for (int i = 0; i <= 10; i++) {
            if (info[i] == 0 && ryan[i] == 0) continue;
            
            if (info[i] < ryan[i]) ryanScore += 10 - i;
            else apeachScore += 10 - i;
        }
        
        if (ryanScore > apeachScore && ryanScore - apeachScore >= max) {
            max = ryanScore - apeachScore;
            answer = ryan.clone();
        }
        
        return;
    }
    // ryan이 쏜 화살이 info보다 크면 진행할 필요 없음
    for (int i = 0; i <= 10 && ryan[i] <= info[i]; i++) {
        ryan[i]++;
        dfs(cnt + 1);
        ryan[i]--;
    }
}
```

- DFS로 라이언이 어피치보다 화살을 많이 쏘지 않은 경우에 대해 화살을 쏜다.
- 1부터 n+1까지 진행하면서, 라이언과 어피치의 점수를 계산하고, 점수 차가 큰 경우를 `max` 에 갱신하면서 라이언의 과녁 점수를 갱신한다.
  - 이때 `max` 랑 같은 경우여도 갱신하도록 하여, 점수 차가 같은 경우 가장 낮은 점수를 많이 쏜 경우로 과녁 점수를 갱신할 수 있도록 한다.

## Review
- 재귀적인 방식으로 높은 점수부터 화살을 쏴보면서 진행해야 하는 문제임은 쉽게 알 수 있었다.
- 자잘한 구현에서 막혀 풀이를 참고했다.