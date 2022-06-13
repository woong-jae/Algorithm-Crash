# [60059] 자물쇠와 열쇠
## Algorithm
- Bruteforce
## Logic
`key`를 4번 회전시키면서 자물쇠를 채울 수 있는지 확인한다.

```js
for(let spin = 0; spin < 4; spin++) {
    key = spinClockWise(key);
    if(canOpen(key)) return true;
}
```

자물쇠를 채울 수 있는지 확인할 때는 '키의 우측 하단을 자물쇠의 왼쪽 상단'부터 시작해서 '키의 좌측 상단이 자물쇠의 우측 하단'까지 움직이며 확인한다.
키의 왼쪽 상단을 기준으로 했다.

```js
for(let row = -M + 1; row < N; row++) {
    for(let col = -M + 1; col < N; col++) {
        if(isMatch(key, empty, [row, col])) return true;
    }
}
```

`empty`는 자물쇠의 빈 부분을 `[row, col]` 형태로 가지고 있다.

`isMatch`는 각 위치에서 키가 자물쇠를 풀 수 있는지 확인한다.
`empty`를 `${row}${col}`를 키로 같는 Set으로 바꾼 후, Set을 모두 비울 수 있으면 일치하는지 알 수 있다.

```js
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
```

## Review
블록 게임과 비슷한 구현문제인 것 같다. 배열의 크기가 커봤자 20*20이라서 그냥 완전탐색 문제라고 생각했다.
이런 문제를 풀 때는 필요한 기능들을 작은 단위들로 쪼개가며 구현하면 효율적으로 풀 수 있는 것 같다.
