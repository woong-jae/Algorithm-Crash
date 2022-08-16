# [87391] 공 이동 시뮬레이션
## Algorithm
- **구현**

## Logic
- 쿼리의 목적지 좌표부터 쿼리를 거꾸로 실행시켜 가능한 모든 부분의 좌표를 찾기
- 각 쿼리에서 행과 열에 대한 범위를 구한 후 열과 행의 곱이 공이 출발할 수 있는 좌표의 개수가 된다 

```java
for (int i = len - 1; i >= 0; i--) {
    int direction = queries[i][0];
    int distance = queries[i][1];

    switch (direction) {
        case 0: // 열을 증가
            if (colStart != 0)
                colStart += distance;
            colEnd += distance;
            if (colEnd > m - 1)
                colEnd = m - 1;
            break;
        case 1: // 열을 감소
            colStart -= distance;
            if (colStart < 0)
                colStart = 0;
            if (colEnd != m - 1)
                colEnd -= distance;
            break;
        case 2: // 행을 증가
            if (rowStart != 0)
                rowStart += distance;
            rowEnd += distance;
            if (rowEnd > n - 1)
                rowEnd = n - 1;
            break;
        case 3: // 행을 감소
            rowStart -= distance;
            if (rowStart < 0)
                rowStart = 0;
            if (rowEnd != n - 1)
                rowEnd -= distance;
    }
    if (rowStart > n - 1 || rowEnd < 0 || colStart > m - 1 || colEnd < 0)
        return 0;
}
```

## Review
거꾸로 실행시켜 범위를 구하는 아이디어까지는 생각해서 dfs를 통해구현했으나 전부 시간초과나 런타임 에러가 나서 통과하지 못해서 코드를 참고했다  
범위로 접근을 하는 것을 전혀 생각해내지 못했던 것 같다... 핵어려움..
