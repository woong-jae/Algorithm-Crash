# [64062] 징검다리 건너기
## Algorithm
- Binary Search
## Logic
`stones` 원소의 최대값이 너무 크기 떄문에 문제를 결정 문제로 바꿔서 풀면 더 쉽게 풀 수 있다.

0와 200,000,000부터 이분 탐색을 하면서 건널 수 있는지 확인하면 된다.

```js
let left = 0, right = 200000000;
while(left + 1 < right) {
    const mid = Math.floor((left + right) / 2);
    if(canCross(mid)) left = mid;
    else right = mid;
}
return left;
```
## Review
원소의 최대값이 엄청 크다는 것을 캐치해서 이분 탐색을 떠올려 쉽게 풀 수 있었다.

주어진 제한사항이 뭔가 너무 크다면 이분탐색을 쓰자.