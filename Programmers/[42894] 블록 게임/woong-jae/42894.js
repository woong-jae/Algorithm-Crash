function solution(board) {
    let answer = 0;
    const N = board.length;
    
    const deleteBlock = (row, col, width, height) => {
        for(let r = row; r < row + height; r++) {
            for(let c = col; c < col + width; c++) {
                board[r][c] = 0;
            }
        }
    }
    
    const canFill = (row, col) => {
        for(let r = 0; r < row; r++) {
            if(board[r][col]) return false;
        }
        return true;
    }
    
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
        
    return answer;
}