# [12987] 숫자 게임

## Algorithm

-

## Logic
B가 가장 큰 점수를 얻기 위해서는 A의 숫자를 가장 작은 숫자로 만족해야한다.
그렇기 때문에 A의 순서는 상관없다.

A와 B를 오름차순으로 정렬한다.

A의 각 숫자에 대해, B의 숫자를 순회하면서 큰 것이 있으면 센다.

```js
let answer = 0,
  indexA = 0;
for (let i = 0; i < B.length; i++) {
  if (A[indexA] < B[i]) {
    answer += 1;
    indexA += 1;
  }
}
```

## Review
쉬운 문제...