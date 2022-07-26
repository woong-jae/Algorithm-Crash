function solution(board) {
    const n = board.length;
    const dr = [-1, 0, 1, 0], dc = [0, 1, 0, -1];
    
    const arrived = ([r, c]) => (n - 1 === r && n - 1 === c);
    
    const isValid = ([nr, nc]) => {
        if(0 <= nr && nr < n && 0 <= nc && nc < n && board[nr][nc] !== 1) return true;
        return false;
    }
    const getKey = (left, right) => left.join(",") + "," + right.join(",");
    
    const move = ([r, c], dir) => [r + dr[dir], c + dc[dir]];
    
    const getMoves = (left, right) => {
        const moves = [];
        for(let dir = 0; dir < 4; dir++) {
            const nextLeft = move(left, dir), nextRight = move(right, dir);
            if(!isValid(nextLeft) || !isValid(nextRight)) continue;
            moves.push([nextLeft, nextRight]);
        }
        return moves;
    }
    
    const getSpins = (left, right) => {
        const spins = [];
        const bases = [left, right];
        
        if(left[0] === right[0]) { // 가로일때
            const tops = bases.map(base => move(base, 0));
            if(tops.reduce((prev, cur) => prev && isValid(cur), true)) {
                tops.forEach((top, i) => spins.push([top, bases[i]]));
            }
            const bottoms = bases.map(base => move(base, 2));
            if(bottoms.reduce((prev, cur) => prev && isValid(cur), true)) {
                bottoms.forEach((bottom, i) => spins.push([bases[i], bottom]));
            }
        }
        else { // 세로일때
            const lefts = bases.map(base => move(base, 3));
            if(lefts.reduce((prev, cur) => prev && isValid(cur), true)) {
                lefts.forEach((left, i) => spins.push([left, bases[i]]));
            }
            const rights = bases.map(base => move(base, 1));
            if(rights.reduce((prev, cur) => prev && isValid(cur), true)) {
                rights.forEach((right, i) => spins.push([bases[i], right]));
            }
        }
        
        return spins;
    }
    
    const visited = new Set(getKey([0, 0], [0, 1]));
    const q = [[[0, 0], [0, 1], 0]];
    while(q.length) {
        const [left, right, time] = q.shift(); // [top, bottom]
        if(arrived(left) || arrived(right)) return time;
        
        const nextPositions = getMoves(left, right).concat(getSpins(left, right));
        nextPositions.forEach(([left, right]) => {
            const key = getKey(left, right);
            if(visited.has(key)) return;
            visited.add(key);
            q.push([left, right, time + 1]);
        });
    }
}