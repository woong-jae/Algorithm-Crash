# [60063] 블록 이동하기
## Algorithm
- Bruteforce
## Logic
로봇이 이동할 수 있는 경우의 수는 다음과 같다.

1. 상하좌우 이동
2. 회전
    - 현재 가로일 경우: 현재 위치를 기준으로 위쪽 세로로 두 개, 아래쪽 세로로 두 개
    - 현재 세로일 경우: 현재 위치를 기준으로 왼쪽 가로로 두 개, 오른쪽 가로로 두 개

```js
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
```

현재 위치부터 이동할 수 있는 경우의 수로 BFS를 진행한다.

이때 방문한 위치는 `Set`에 `{leftRow},{leftCol},{rightRow},{rightCol}`을 키로 기록한다.
Left와 right의 순서는 가로일 경우 좌 그리고 우, 세로일 경우 위 그리고 아래 순서로 기록한다.

```js
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
```

## Review
생각보다 빨리 풀었던 문제. 이번주 문제는 완전탐색이 많아서 어지러운 것 같다.
이번 문제같은 경우 기능별로 나눠서 차근차근 구현해서 오류가 나도 빨리 해결할 수 있었다.