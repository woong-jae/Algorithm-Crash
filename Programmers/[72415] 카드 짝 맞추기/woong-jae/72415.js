function solution(board, r, c) {
    const dr = [-1, 0, 1, 0], dc = [0, 1, 0, -1];
    
    const valid = ([row, col]) => {
        if(0 <= row && row < 4 && 0 <= col && col < 4) return true;
        return false;
    }
    
    const minControl = (from, to, notPicked) => {
        if(from[0] === to[0] && from[1] === to[1]) return 0;
        
        const bfs = Array.from(Array(4), () => Array(4).fill(Infinity));
        
        const q = [from];
        bfs[from[0]][from[1]] = 0;
        while(q.length) {
            const cur = q.shift();
            const count = bfs[cur[0]][cur[1]];
            
            for(let dir = 0; dir < 4; dir++) {
                const vector = [dr[dir], dc[dir]];
                
                let next = cur.map((elem, i) => elem + vector[i]);
                let card = 0, move = 0;
                while(valid(next)) {
                    const [nr, nc] = next;
                    next = next.map((elem, i) => elem + vector[i]);
                    
                    let cand = count;
                    if(
                        (board[nr][nc] === 0 || !notPicked.has(board[nr][nc]))
                        && valid(next)
                    ) cand += card + ++move;
                    else cand += ++card;
                    
                    if(cand > bfs[nr][nc]) break;
                    
                    bfs[nr][nc] = cand;
                    q.push([nr, nc]);
                }
            }
        }
        
        return bfs[to[0]][to[1]];
    }
    
    const move = (cur, position, notPicked) => {
        const [a, b] = position;
        
        const aControl = minControl(cur, a, notPicked) + minControl(a, b, notPicked) + 2;
        const bControl = minControl(cur, b, notPicked) + minControl(b, a, notPicked) + 2;
        
        return aControl > bControl ? [a, bControl] : [b, aControl];
    }
    
    const simulate = (cur, cards, controlCount) => {
        if(cards.size === 0) return controlCount;
        
        let minControl = Infinity;
        Array.from(cards).forEach(([target, position]) => {
            if(!cards.has(target)) return;
            const [next, control] = move(cur, position, cards);

            cards.delete(target);
            minControl = Math.min(simulate(next, cards, controlCount + control), minControl);
            cards.set(target, position);
        });
        
        return minControl;
    }
    
    const cards = new Map();
    board.forEach((row, rowIdx) => row.forEach((elem, colIdx) => {
        if(elem === 0) return;
        if(!cards.has(elem)) cards.set(elem, []);
        cards.get(elem).push([rowIdx, colIdx]);
    }));
    
    return simulate([r, c], cards, 0);
}

console.log(solution([[3,0,0,2],[0,0,1,0],[0,1,0,0],[2,0,0,3]], 0, 1, 16));