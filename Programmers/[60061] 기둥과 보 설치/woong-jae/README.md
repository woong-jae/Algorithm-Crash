# [60061] 기둥과 보 설치
## Algorithm
- 구현
## Logic
### 1. 삽입할 때
삽입할 때는 문제에 나와있는 보와 기둥의 설치 조건을 만족하면 설치해주면 된다.
```js
const buildFrame = (x, y, a, b) => {
    const key = `${x},${y}`;
    if(!isValid(x, y, a, b)) return;
    if(a) {
        rowFrames.set(key, a);
    }
    else {
        colFrames.set(key, a);
    }
}
```

### 2. 삭제할 때
삭제할 때는 먼저 삭제한 후, 이웃한 보나 기둥들이 조건을 만족하고 있는지 검사한다.

- 보를 삭제할 때는 현재 삭제하는 보와 이어진 기둥 2개, 보 2개를 검사하면 된다.
- 기둥을 삭제할 때는 현재 기둥과 이어진 보 2개와 기둥 1개를 검사하면 된다.

```js
const deleteFrame = (x, y, a, b) => {
    const key = `${x},${y}`;
    if(a) { // 보
        rowFrames.delete(key);
        if(!isValid(x - 1, y, 1, b) 
           || !isValid(x + 1, y, 1, b)
           || !isValid(x + 1, y, 0, b)
           || !isValid(x, y, 0, b)) {
            rowFrames.set(key, a);
        }
    }
    else { // 기둥
        colFrames.delete(key);
        if(!isValid(x, y + 1, 0, b) 
           || !isValid(x - 1, y + 1, 1, b) 
           || !isValid(x, y + 1, 1, b)) {
            colFrames.set(key, a);
        }
    }
}
```

이때 검사할 때 추가적으로 검사해야 하는 것이 있다. 바로 검사할 보와 기둥이 실제로 있는지 확인해야 한다.

```js
const isValid = (x, y, a, b) => {
    const key = `${x},${y}`;
    if(a) { // 보
        if(b === 0 && !rowFrames.has(key)) return true; // 삭제할 때 검사는 보가 있어야 가능
        if(colFrames.has(`${x},${y - 1}`) || colFrames.has(`${x + 1},${y - 1}`)) return true; // 설치할 곳에 기둥이 연결됨
        if(rowFrames.has(`${x - 1},${y}`) && rowFrames.has(`${x + 1},${y}`)) return true; // 설치할 곳에 보 2개와 연결됨
    }
    else { // 기둥
        if(b === 0 && !colFrames.has(key)) return true; // 삭제할 때 검사는 기둥이 있어야 가능
        if(y === 0) return true; // 기둥이 지면에 붙어있음
        if(colFrames.has(`${x},${y - 1}`)) return true; // 설치할 곳 밑에 기둥이 있음
        if(rowFrames.has(`${x - 1},${y}`) || rowFrames.has(`${x},${y}`)) return true; // 설치할 곳 밑에 보가 있음
    }
    return false;
}
```

## Review
삭제할 때 검사할 보와 기둥이 실제로 있는지 확인해야 한다는 점을 생각하지 못해서 한참을 해맸다. 차근차근 생각해야 하는데 코드도 너무 더러웠어서 그게 어려웠던 것 같다.

코드를 정리하고 나니 흐름을 훨씬 잘 볼 수 있었고 문제점을 찾았다. 코드 정리하는 습관이 중요한 것 같다.