# [17687] n진수 게임
## Algorithm
- 없음
## Logic
toString 메서드를 사용하면 쉽게 `n` 진수를 얻을 수 있다.

이를 바탕으로 시키는 대로 구현하면 된다.
```js
let number = 0, current = "", turn = 0;
while(answer.length < t) {
    if(!current) {
        current = number.toString(n).toUpperCase();
        number++;
    }
    if(turn === p - 1) answer.push(current[0]);
    current = current.slice(1);
    turn = (turn + 1) % m;
}
```
## Review
이번주는 리뷰에 딱히 적을 말이 없는 것 같다. 컷!