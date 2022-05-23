function solution(m, n, board) {
    let answer = 0;
    const dr = [-1, 0, 1, 0], dc = [0, 1, 0, -1];
    
    const isValid = (row, col) => {
        if(0 <= row && row < m && 0 <= col && col < n) return true;
        return false;
    }
    
    const mark = (row, col, marked) => {
        const target = board[row][col];
        for(let direction = 0; direction < 4; direction++) {
            let r1 = row + dr[direction], c1 = col + dc[direction];
            let r2 = row + dr[(direction + 1) % 4], c2 = col + dc[(direction + 1) % 4];
            let r3 = row + dr[direction] + dr[(direction + 1) % 4], c3 = col + dc[direction] + dc[(direction + 1) % 4];
            if(!isValid(r1, c1) || !isValid(r2, c2)) continue;
            if(target === board[r1][c1] && target === board[r2][c2] && target === board[r3][c3]) {
                marked[row][col] = marked[r1][c1] = marked[r2][c2] = marked[r3][c3] = true;
            }
        }
    }   
    
    board = board.map(row => row.split(""));
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
    
    return answer;
}