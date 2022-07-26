# [17679] 프렌즈4블록
## Algorithm
- Simulation
## Logic
1. 보드를 순회하면서 2 * 2인 블록을 찾아 마크한다.
2. 마크한 블록을 지우고 보드를 업데이트한다.
3. 만약 지워진 블록이 있다면 1번부터 반복하고, 없다면 종료한다.

```js
while(1) {
    let count = 0;

    const marked = Array.from(Array(m), () => Array(n).fill(false));
    // 모든 보드를 순회하면서 2 * 2 블록을 찾아 마크해놓음
    for(let row = 0; row < m; row++) {
        for(let col = 0; col < n; col++) {
            if(!board[row][col]) continue;
            mark(row, col, marked);
        }
    }
    // 마크한 블록을 지우고 보드 업데이트
    for(let col = 0; col < n; col++) {
        for(let row = m - 1; row >= 0; row--) {
            if(marked[row][col]) {
                count++;
                board[row][col] = null;
                continue;
            }
            let cur = row;
            while(isValid(cur + 1, col) && !board[cur + 1][col]) cur++;
            if(cur !== row) {
                [board[row][col], board[cur][col]] = [board[cur][col], board[row][col]];
            }
        }
    }
    
    if(count === 0) break;
    answer += count;
}
```
## Review
쉽지만 귀찮은 문제.