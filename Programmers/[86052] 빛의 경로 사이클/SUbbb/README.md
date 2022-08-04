# [86052] 빛의 경로 사이클

## Algorithm
- 구현

## Logic

```java
private int go(int x, int y, int d) {
    // 이동 거리
    int cnt = 0;

    while (!visited[x][y][d]) {
        cnt++;
        visited[x][y][d] = true;

        if (grid[x].charAt(y) == 'L')
            d = d == 0 ? 3 : d - 1; // 좌회전
        else if (grid[x].charAt(y) == 'R')
            d = d == 3 ? 0 : d + 1; // 우회전
        
        // 다음 좌표 계산
        x = (x + dir[d][0] + m) % m;
        y = (y + dir[d][1] + n) % n;
    }

    return cnt;
}
```

- 방향에 따라 빛을 계속 이동시킨다.
- 해당 격자에 해당 방향에 방문 기록이 없는 경우 종료한다.

## Review
- 문제 자체가 잘 이해가 안되어서 질문하기의 설명을 참고했다.
- 단순 구현은 가끔 문제가 이해 안될 때가 있는 것 같다...