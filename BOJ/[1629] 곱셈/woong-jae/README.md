# [1629] 곱셈

## Algorithm

- Divide and conquer

## Logic

지수가 `N`일 때, `N/2 + N/2` 로 분할해서 결과를 구한다.

`N`이 홀수라면, `N/2 + N/2 * A`로 지수를 짝수로 분리해주면 된다.

```js
const dnq = (exponent) => {
  if (exponent === 1) return A % C;

  const half = dnq(Math.floor(exponent / 2)) % C;
  if (exponent % 2 === 0) return (half * half) % C;
  return (half * half * A) % C;
};
```

## Review
지수로 분할정복 상상도 못했다. 아침부터 어지러웠음...