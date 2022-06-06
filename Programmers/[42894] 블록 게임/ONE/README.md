# [42894] 블록 게임
## Algorithm
- **Brute Force**

## Logic
- board를 순회하며 6칸짜리 가로 직사각형과 세로 직사각형을 검사한다
  - 해당 직사각형이 보드의 범위를 벗어나지 않고
  - 빈칸이 2개인지 검사
  - 빈칸이 세로로 1칸짜리 검은 블록으로 내려와서 채울수 있는지 검사
  - 해당 정사각형이 1가지 숫자로 이루어졌는지를 검사

```java
private boolean find(int row, int col, int h, int w, int[][] board) {
    int emptyCount = 0;
    int value = -1;

    for (int r = row; r < row + h; r++)
        for (int c = col; c < col + w; c++) {
            if (board[r][c] == 0) {
                if (!canFill(r, c, board))
                    return false;
                if (++emptyCount > 2)
                    return false;
            }
            else {
                if(value != -1 && value != board[r][c])
                    return false;
                value = board[r][c];
            }
        }

    erase(row, col, h, w, board);

    return true;
}
```

## Review
BFS를 이용하여 문제를 풀려고 접근했다가 사각형을 어떻게 확인해야할지 감이 잡히지 않아서 풀이를 참고했다  
이렇게 복잡한 문제는 안되는 경우를 계속 제껴야 한다는 것을 느낄 수 있었다  
갱장히 어려웠던 문제...