# [72415] 카드 짝 맞추기
## Algorithm
- Bruteforce
- BFS
## Logic
카드를 지우는 모든 순서대로 지워보고 가장 작은 조작 횟수를 찾는 것이 기본 아이디어다.

```js
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
```

제일 핵심에 되는 부분이 `move` 함수다. 이 함수는 현재 위치에서 두 카드를 지울 때, 최소 조작횟수와 그 때 마지막 위치를 반환해준다.
두 카드를 지우는 것은 `(현 시작점, a) + (a, b)`로 분해해서 단순화한다.

```js
const move = (cur, position, notPicked) => {
    const [a, b] = position;
    
    const aControl = minControl(cur, a, notPicked) + minControl(a, b, notPicked) + 2;
    const bControl = minControl(cur, b, notPicked) + minControl(b, a, notPicked) + 2;
    
    return aControl > bControl ? [a, bControl] : [b, aControl];
}
```

최소 조작횟수는 BFS를 통해 찾을 수 있다. BFS를 좀 변형해야한다. 

한 위치에서 상하좌우의 전체 줄에 대해 확인해야 한다. 한 줄을 검사하면서 현재 위치의 숫자가 `0`이거나 이미 뽑은 카드라면 방향키만을 움직여야하고, 아직 안뽑은 카드나 마지막 벽이라면 `이때까지 오면서 만난 카드 수 + 1`이 필요한 조작횟수다.

```js
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
```

## Review
전체적인 틀은 빨리 잡았는데 최소 조작횟수를 구하는 방법이 어려웠다. BFS를 사용하긴 해야겠는데 좀 변형적이여서 생각해내는데 오래 걸린 것 같다.
또 다 구현해놓고 자잘한 실수가 많아서 고생했다. 그래도 문제를 작은 단위로 쪼개 구현해놔서 그나마 빨리 찾은 것 같다.