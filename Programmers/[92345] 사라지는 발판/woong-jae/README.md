# [92346] 사라지는 발판
## Algorithm
- Minmax
- Bruteforce
## Logic
현재 위치에서 갈 수 있는 곳으로 갔을 때, 상대방의 이길 수 있는 여부를 확인한다.

현재 위치에서 움직일 수 있는 곳이 없다면 진 것이다. 혹은, 상대의 위치와 내 위치가 같다면 내가 이긴 것이다.

모든 경우의 수를 확인했을 때, 상대가 모두 진다면 내가 무조건 이긴다는 의미기 때문에 상대의 최대 움직임을 선택한다.
상대가 이길 수도 있다면, 최소 움직임을 선택한다.

```js
const simulate = (aloc, bloc) => {
    if(finished(aloc)) return [false, 0];
    if(aloc[0] === bloc[0] && aloc[1] === bloc[1]) return [true, 1];
    
    let canWin = false;
    let maxTurn = 0, minTurn = Infinity;
    
    board[aloc[0]][aloc[1]] = 0;
    for(let dir = 0; dir < 4; dir++) {
        const [nr, nc] = aloc.map((pos, i) => pos + d[dir][i]);
        if(!valid([nr, nc]) || board[nr][nc] === 0) continue;
        
        const [isWin, turn] = simulate(bloc, [nr, nc]);
        
        if(!isWin) {
            canWin = true;
            minTurn = Math.min(minTurn, turn);
        }
        else {
            maxTurn = Math.max(maxTurn, turn);
        }
    }
    board[aloc[0]][aloc[1]] = 1;
    
    return [canWin, (canWin ? minTurn : maxTurn) + 1];
}
```

## Review
뭐 어떻게 풀어야 할 지 감도 안잡힌 문제. 예전에 인공지능 시간에 배운 게임 이론을 사용하는 것 같은 문제라고는 생각이 들었는데,
게임 이론을 어떻게 구현하는지 몰라서 그냥 포기했다. 상대의 조건에 따라 뭔가 결정된다는 것을 알아야 풀 수 있는 문제인 것 같다.
실제 이런 문제가 나오면 못풀것 같다...ㅋㅋ

참고한 풀이: https://yjyoon-dev.github.io/kakao/2022/01/23/kakao-2022-blind-07/
