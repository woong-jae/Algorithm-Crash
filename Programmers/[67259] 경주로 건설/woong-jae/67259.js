function solution(board) {
    const N = board.length;
    const dr = [-1, 0, 1, 0], dc = [0, 1, 0, -1]; // top, right, bottom, left
    const cache = [];
    cache.push(new Array(N).fill(0).map(_ => new Array(N).fill(Infinity)));
    cache.push(new Array(N).fill(0).map(_ => new Array(N).fill(Infinity)));
    
    const pq = [{ r: 0, c: 0, v: 0, d: null }];
    while(pq.length) {
        const {r, c, v, d} = pq.shift();
        
        for(let distanceIndex = 0; distanceIndex < 4; distanceIndex++) {
            if(distanceIndex === d) continue;
            const nr = r + dr[distanceIndex], nc = c + dc[distanceIndex];
            if(isValid(nr, nc, N) && board[nr][nc] !== 1) {
                let nextVal = v + (d === null ? 100 : Math.abs(distanceIndex - d) % 2 ? 600 : 100);
                let type = (distanceIndex + 2) % 2;
                
                if(cache[type][nr][nc] > nextVal) {
                    cache[type][nr][nc] = nextVal;
                    pq.push({ r: nr, c: nc, v: nextVal, d: (distanceIndex + 2) % 4 });
                }
            }
        }
    }
    
    return Math.min(cache[0][N - 1][N - 1], cache[1][N - 1][N - 1]);
}

function isValid(r, c, N) {
    if(0 <= r && r < N && 0 <= c && c < N) return true;
    return false;
}