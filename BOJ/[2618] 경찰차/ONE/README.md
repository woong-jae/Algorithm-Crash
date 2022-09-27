# [2618] 경찰차
## Algorithm
- **DP**

## Logic
- [첫번쨰 경찰차가 x번쨰 사건][두번째 경찰차가 y번째 사건] 현재 위치에서 사건을 끝까지 해결할 때까지 이동하는 거리의 합의 최솟값
- 1번 경찰차와의 거리를 뺀 뒤 1번 차가 움직인 경우의 dp와 비교해서
  - 같으면 1번째차를 출력하고 1번째차를 움직이고
  - 다르면 2번째차를 출력하고 2번째 차를 움직인다

```java
static int solution(int eventIdx, int oneIdx, int twoIdx) {
    if(eventIdx > W)
        return 0;

    if(dp[oneIdx][twoIdx] != 0)
        return dp[oneIdx][twoIdx];

    int oneMoveCount = solution(eventIdx+1, eventIdx, twoIdx) + distance(1, oneIdx, eventIdx);
    int twoMoveCount = solution(eventIdx+1, oneIdx, eventIdx) + distance(2, twoIdx, eventIdx);

    return dp[oneIdx][twoIdx] = Math.min(oneMoveCount, twoMoveCount);
}
```

## Review
문제 자체도 어려운 것 같고 아이디어가 생각나지 않았다  
DP 문제는 너무 어려운것 같다  
다음주 문제는 난이도를 낮춰서...