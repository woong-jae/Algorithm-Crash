# [64061] 크레인 인형뽑기 게임
## Algorithm
- Stack
## Logic
주어진 `board`가 일반적으로 주어지는 것과 다르게 주어져서 어디가 최상단인지 직접 구해야한다.

```js
    const tops = new Array(board[0].length).fill(board.length);
    for(let col = 0; col < board[0].length; col++) {
        for(let row = board.length - 1; row >= 0; row--) {
            const item = board[row][col];
            if(!item) break;
            tops[col] = row;
        }
    }
```

각 열 별로 최상단을 구해 `tops`에 저장한 후 `moves`에 따라 인형들을 바구니(`stack`)에 담아준다.
만약 넣을 인형이 스택의 상단과 같은 인형이면 `pop`을 한 후 사라진 인형 개수에 2를 추가해준다.

```js
    const stack = [];
    moves.forEach(move => {
        const top = tops[move - 1];
        if(top < board.length) {
            const item = board[top][move - 1];
            if(stack.length && stack[stack.length - 1] === item) {
                stack.pop();
                answer += 2;
            }
            else {
                stack.push(item);
            }
            tops[move - 1]++;
        }
    });
```

최상단을 찾을 때 이중 `for`문을 사용하기 때문에 시간복잡도는 O(N^2)이 된다.
## Review
쉬운 문제. 최상단을 O(N)에 구할 수 있는 방법이 있을까...