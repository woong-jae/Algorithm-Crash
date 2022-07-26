# [17679] 프렌즈4블록

## Algorithm

- Simulation

- Set

## Logic

**1. 블록 지우기**

```js
function clearBlock(m, n, board) {
  const clearSet = new Set();

  for (let x = 0; x < m - 1; x++) {
    for (let y = 0; y < n - 1; y++) {
      const shape = board[x][y];
      if (!shape) continue;
      if (
        shape === board[x][y + 1] &&
        shape === board[x + 1][y] &&
        shape === board[x + 1][y + 1]
      ) {
        clearSet
          .add(`${x},${y}`)
          .add(`${x},${y + 1}`)
          .add(`${x + 1},${y}`)
          .add(`${x + 1},${y + 1}`);
      }
    }
  }

  for (const str of clearSet) {
    const [x, y] = str.split(',');
    board[x][y] = 0;
  }

  return clearSet.size;
}
```

- 같은 모양의 블록이 2 x 2 형태로 4개 붙어있을 경우, 해당 4가지의 블록 인덱스를 문자열 형태로 바꾸어 Set에 넣는다.

  - 이때, 인덱스가 10 이상이 될 수 있으므로, 문자열 변환 시 row와 col 인덱스 사이에 콤마(,)를 넣는다.

- board의 모든 원소에 대한 탐색을 마친 후, Set의 있는 문자열들을 순회하며, 인덱스를 얻은 후 해당 인덱스 값에 0을 넣는다.

- Set의 크기를 리턴한다.

<br />

**2. 블록 이동하기**

```js
function shiftBlock(m, n, board) {
  for (let y = 0; y < n; y++) {
    let bottom = m;
    for (let x = m - 1; x >= 0; x--) {
      if (!board[x][y] && bottom === m) {
        bottom = x;
        continue;
      }

      if (board[x][y] && bottom < m) {
        board[bottom][y] = board[x][y];
        board[x][y] = 0;
        bottom--;
      }
    }
  }
}
```

- row를 역순으로 탐색한다.

- 최초의 0을 발견하면 해당 위치를 `bottom`에 저장해둔다.

- 0을 발견한 이후 부터, 0이 아닌 값이 나오면 `bottom` 위치의 board에 해당 값을 넣고, 해당 위치에 0을 넣는다.

  - 그리고 `bottom`을 -1 한다.

  - 이를 반복한다.

### 시간 복잡도 : O(N^3)

## Review

단순 구현 문제였기에, 어렵지 않았다.

코드를 깔끔하게 리팩토링 하는데 더 시간을 투자했다.