# [60058] 괄호 변환
## Algorithm
- 구현
## Logic
시키는 대로 구현하면 된다.

올바른 문자열인지 확인하는 부분은 문자열을 처음부터 순회하면서 `(`이면 +1, `)`이면 -1을 카운트해주다가 카운트가 음수가 되면 올바르지 않은 문자열이다.
```js
const isCorrect = p => {
    let count = 0;
    for(const bracket of p) {
        if(bracket === "(") count++;
        else count--;
        if(count < 0) return false;
    }
    return true;
}
```

## Review
쉬운 문제.