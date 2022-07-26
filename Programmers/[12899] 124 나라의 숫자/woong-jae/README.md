# [12899] 124 나라의 숫자
## Algorithm
- 없음
## Logic
3진법의 수를 만드는 문제다.

숫자가 1부터 시작하기 때문에 대응되는 수를 한 칸씩 미뤄주면 된다.

```js
const num_map = [4, 1, 2];
while(n > 0) {
    answer.push(num_map[n % 3]);
    n = Math.floor((n - 1) / 3);
}
```
## Review
2진법 구하는 방법을 알면 쉽게 풀 수 있는 문제인 것 같다.