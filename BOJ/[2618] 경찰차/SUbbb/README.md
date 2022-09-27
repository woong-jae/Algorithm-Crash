# [2618] 경찰차

## Algorithm
- DP

## Logic

```java
dp[x][y] = Math.min(process(nextT, B) + distA, process(A, nextT) + distB)
```

- 위의 점화식을 사용

## Review
- 처음엔 간단한 문제인 줄 알았는데, 만약 두 경찰차와 사건 간의 거리가 같은 경우를 포함한 최소를 찾기 위해 DP를 사용해야겠다 싶었다.
- dp[i][j][k] 로 i와 j는 사건 위치, k는 어떤 경찰차가 맡을지로 해서 구해보려했는데 좀 이상한 거 같다.
- 풀이를 참고하니 2차원 DP로 해결이 가능했다. 이걸 어떻게 하지 ?