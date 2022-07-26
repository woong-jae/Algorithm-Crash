# [92345] 사라지는 발판

## Algorithm
- DFS
- 완전 탐색

## Logic

```java
private Result dfs(int aX, int aY, int bX, int bY, int moveA, int moveB) {
    boolean win = false;
    int minCount = 5 * 5;
    int maxCount = moveA + moveB;
    
    if (moveA == moveB && board[aX][aY] == 1) {
        // a가 움직일 차례
        for (int[] d : dir) {
            int naX = aX + d[0];
            int naY = aY + d[1];

            if (!checkBoundary(naX, naY)) continue;
            
            board[aX][aY] = 0;
            Result r = dfs(naX, naY, bX, bY, moveA + 1, moveB);
            win |= !r.win;
            if (!r.win) minCount = Math.min(minCount, r.count);
            else maxCount = Math.max(maxCount, r.count);

            board[aX][aY] = 1;
        }
    } else if (moveA > moveB && board[bX][bY] == 1) {
        // b가 움직일 차례
        for (int[] d : dir) {
            int nbX = bX + d[0];
            int nbY = bY + d[1];

            if (!checkBoundary(nbX, nbY)) continue;
            
            board[bX][bY] = 0;
            // dfs의 결과는 본인이 아닌 다른 사람의 결과이므로, 승패가 반대이다.
            // 또한 r.win = true인 경우가 하나라도 있을 경우 win은 true가 되어야 하므로 or연산을 수행한다.
            Result r = dfs(aX, aY, nbX, nbY, moveA, moveB + 1);
            win |= !r.win;

            // 본인이 이기는 경우라면 최대한 빨리 이길 때의 이동횟수를 갱신한다.
            if (!r.win) minCount = Math.min(minCount, r.count);
            // 본인이 지는 경우라면 최대한 오래 버틸 때의 이동횟수를 갱신한다.
            else maxCount = Math.max(maxCount, r.count);

            board[bX][bY] = 1;
        }
    }
    
    return new Result(win, win ? minCount : maxCount);
}
```

- A가 움직일 차례와, B가 움직일 차례를 나누어 생각한다.
- DFS방식으로 움직이면서, 이기는 경우와 지는 경우의 이동횟수를 갱신한다.

## Review
- 이길 수 있는 플레이어는 최대한 빨리 승리하고, 질 수밖에 없는 플레이어는 최대한 오래 버틴다라는 조건에 있어 조금 독특한 문제가 아니었나싶다.
- 중복되는 로직을 떼내어야 할 것 같다.
- 역시 마지막 문제는 어렵다 ...