# [92345] 사라지는 발판
## Algorithm
- **DFS**

## Logic
- 이번턴에 플레이하는 사람이 현재 발판이 있고  
- 다른곳으로 이동할 수 있다면 이동을 한 후 DFS 실행
- 그리고 DFS 결과로 나온 등수를 OR 연산하여 현재 플레이 하는 사람의 승패여부를 판단한다
- 현재 사람이 졌다면 최대의 값으로 갱신
- 현재 사람이 이겼다면 최소의 값으로 갱신

```java
public Result dfs(int curX, int curY, int anotherX, int anotherY, int count, int[][] board){
    boolean win = false;
    int minCount = 5 * 5;
    int maxCount = count;

    if(board[curX][curY] == 1){
        for(int[] tmp : dir){
            int x1Tmp = curX + tmp[0];
            int y1Tmp = curY + tmp[1];
            if(isValid(x1Tmp, y1Tmp, board)){
                board[curX][curY] = 0;
                Result d = dfs(anotherX, anotherY, x1Tmp, y1Tmp, count + 1, board);
                win |= !d.win;
                if (d.win)
                    maxCount = Math.max(maxCount, d.count);
                else
                    minCount = Math.min(minCount, d.count);
                board[curX][curY] = 1;
            }
        }
    }

    return new Result(win, win ? minCount : maxCount);
}
```

## Review
미니맥스 알고리즘을 사용하는 문제였는데  
처음 보는 형태의 문제였고 많이 어려웠다  
'최적의 승부'가 너무 이해가 안됐던 문제