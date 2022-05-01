# [67259] 경주로 건설

## Algorithm
- DFS

## Logic

```java
private void dfs(int[][] board, int prevDir, int x, int y) {
    if (x == n - 1 && y == n - 1) {
        minCost = Math.min(costs[x][y], minCost);
        return;
    }
    
    for (int i = 0; i < 4; i++) {
        int nx = x + xDir[i];
        int ny = y + yDir[i];
        
        if (nx == 0 && ny == 0) continue;
        if (!checkBoundary(nx, ny)) continue;
        if (board[nx][ny] == 1) continue;
        
        int tmp = costs[x][y];
        if (prevDir != -1 && Math.abs(prevDir - i) % 2 == 1) 
            tmp += 500;
        tmp += 100;
            
        if ((costs[nx][ny] != 0 && costs[nx][ny] >= tmp) || costs[nx][ny] == 0) {
            costs[nx][ny] = tmp;
            dfs(board, i, nx, ny);
        }
    }
}
```

- `prevDir` 을 사용해 이전 방향을 저장하고, 직선인지 코너인지 판단한다.
- 탐색 방향에 우선순위를 두고, 먼저 탐색한다.
  - 비용이 더 작은 경우의 건설만을 update하도록 한다.

## Review
- 생각 외로, 간단한 `DFS` 문제였다. 이전 방향을 DFS의 인자로 전달해주는 것이 중요했던 것 같다.