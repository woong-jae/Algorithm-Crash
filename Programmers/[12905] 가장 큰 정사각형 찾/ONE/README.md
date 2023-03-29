# [12905] 가장 큰 정사각형 찾기
## Algorithm
- **DP**

## Logic
- (1, 1) 부터 시작해 자신의 위치를 기준으로 (대각선 왼쪽 위, 위, 왼쪽) 중 최솟값에 +1 한 값을 현재 위치에 저장한다
  - 위를 반복하게 된다면 현재 위치에 가장 큰 정사각형의 한 변의 길이를 구할 수 있다
```java
public int solution(int[][] board) {
    int n = board.length;
    int m = board[0].length;

    for (int row = 1; row < n; row++) {
        for (int col = 1; col < m; col++) {
            if (board[row][col] == 1) {
                board[row][col] = Math.min(board[row - 1][col - 1],
                        Math.min(board[row][col - 1], board[row - 1][col])) + 1;
            }
        }
    }

    int max = Arrays.stream(board)
            .flatMapToInt(Arrays::stream)
            .max()
            .getAsInt();

    return max * max;
}
```

## Review
기본적인 DP 문제
