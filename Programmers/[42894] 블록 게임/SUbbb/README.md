# [42894] 블록 게임

## Algorithm
- 구현

## Logic

```java
public void findRemovableBlock() {
    int count;
    
    // 더 이상 삭제 가능한 블록이 없을 때까지 진행
    do {
        count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 가로 또는 세로 범위의 직사각형 블록 여부 확인
                if ((i <= N - 2 && j <= N - 3 && find(i, j, 2, 3)) || (i <= N - 3 && j <= N - 2 && find(i, j, 3, 2)))
                    count++;
            }
        }
        removableBlocks += count;
    } while(count != 0);
}
```

- 이중 for문으로 보드를 확인하면서, 2x3 범위와 3x2 범위를 확인하여 검정 블록 2개로 삭제할 수 있는 블록의 수를 카운트한다.

```java
private boolean find(int x, int y, int h, int w) {
    // 빈 공간을 카운트, 빈 공간은 2개까지만 가능하다. 블록 범위의 크기는 6이고 블록의 크기는 4이기 때문
    int emptyCount = 0;
    // 빈 공간이 아닌 블록 번호를 저장, 빈 공간이 아닌 경우에는 이 값과 모두 동일해야 함
    int lastNum = -1;
    
    for (int i = x; i < x + h; i++) {
        for (int j = y; j < y + w; j++) {
            // 빈 공간인 경우
            if (board[i][j] == 0) {
                if (!canFill(i, j)) return false;
                if (++emptyCount > 2) return false;
            } else {
                // 빈 공간이 아닌 블록 번호가 있고, 현재 영역과 다른 번호라면 같은 블록이 아니므로 종료
                if (lastNum != -1 && lastNum != board[i][j]) return false;
                lastNum = board[i][j];
            }
        }
    }
    
    removeBlock(x, y, h, w);
    
    return true;
}
```

- 현재 위치와 가로, 세로 범위를 가지고 해당 범위 내의 빈 공간의 개수와 빈 공간이 아닌 블록의 번호를 저장한다.
  - 빈 공간의 개수가 **3개 이상**일 경우 종료한다.
  - 빈 공간이 아닌 블록 번호와 같지 않은 블록 번호가 나온 경우, 동일한 블록이 아니므로 종료한다.

## Review
- 문제 자체 이해는 어렵지 않았지만, 구현이 좀 빡세서 유튜브 강의를 봤는데 엄청 깔끔하게 설명하더라.
- 삭제가 가능한지 아닌지 확인하는 로직이 중요했다.