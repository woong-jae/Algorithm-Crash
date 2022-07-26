function solution(key, lock) {
    const M = key.length, N = lock.length;
    
    const spinClockWise = key => {
        const spinned = [];
        for(let col = 0; col < M; col++) {
            const spinnedRow = [];
            for(let row = M - 1; row >= 0; row--) {
                spinnedRow.push(key[row][col]);
            }
            spinned.push(spinnedRow);
        }
        return spinned;
    }
    
    const isValid = (row, col) => {
        if(0 <= row && row < N && 0 <= col && col < N) return true;
        return false;
    }
    
    const isMatch = (key, empty, leftTop) => {
        const emptySet = new Set(empty.map(([row, col]) => `${row}${col}`));
        
        for(let row = 0; row < M; row++) {
            for(let col = 0; col < M; col++) {
                const nr = leftTop[0] + row, nc = leftTop[1] + col;
                if(!key[row][col] || !isValid(nr, nc)) continue;
                if(!emptySet.has(`${nr}${nc}`)) return false;
                emptySet.delete(`${nr}${nc}`);
            }
        }
        return emptySet.size ? false : true;
    }
    
    const canOpen = key => {
        const empty = [];
        lock.forEach((row, rowIndex) => row.forEach((col, colIndex) => {
            if(col === 0) empty.push([rowIndex, colIndex]);
        }));
        
        for(let row = -M + 1; row < N; row++) {
            for(let col = -M + 1; col < N; col++) {
                if(isMatch(key, empty, [row, col])) return true;
            }
        }
        return false;
    }
    
    for(let spin = 0; spin < 4; spin++) {
        key = spinClockWise(key);
        if(canOpen(key)) return true;
    }
    
    return false;
}