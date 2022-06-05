# [42894] 블록 게임
## Algorithm
- Bruteforce
## Logic
모든 위치에서 오른쪽으로 긴 직사각형과 아래로 긴 직사각형을 확인한다.

두 직사각형 중 없앨 수 있는 직사각형이 있으면 없애고 카운트해준다.
```js
const canDelete = (row, col, width, height) => {
    if(row + height > N || col + width > N) return false;
    let emptyCount = 0, lastValue = -1;
    for(let r = row; r < row + height; r++) {
        for(let c = col; c < col + width; c++) {
            if(board[r][c] === 0) {
                if(!canFill(r, c)) return false;
                emptyCount++;
                if(emptyCount > 2) return false;
            }
            else {
                if(lastValue === -1) lastValue = board[r][c];
                else if(lastValue !== board[r][c]) return false;
            }
        }
    }
    
    deleteBlock(row, col, width, height);
    
    return true;
}
```

더는 삭제할 수 없을 떄까지 반복 순회한다.

```js
while(1) {
    let deleted = 0;
    
    for(let row = 0; row < N; row++) {
        for(let col = 0; col < N; col++) {
            if(canDelete(row, col, 3, 2) || canDelete(row, col, 2, 3)) {
                deleted++;
            }
        }
    }
    
    if(deleted === 0) break;
    answer += deleted;
}
```

## Review
모르겠어서 [유튜브](https://www.youtube.com/watch?v=EB8p3bHLJyU) 참고함. 나는 일반적인 BFS 문제로 접근해서 해결할 수가 없었다.

상식의 틀에서 벗어나야 풀 수 있는 문제같다.