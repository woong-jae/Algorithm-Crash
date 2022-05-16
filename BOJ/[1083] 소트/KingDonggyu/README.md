# [1083] 소트

## Algorithm

- Bubble Sort

## Logic

```js
for (let x = 0; x < N; x++) {
  const max = { index: x, value: A[x] };

  // x부터 S의 범위 내 최대 값을 찾는다
  for (let y = x + 1; y <= x + S; y++) {
    if (y >= N) break;

    if (max.value < A[y]) {
      max.index = y;
      max.value = A[y];
    }
  }

  // 최대 값의 위치부터 x의 위치까지 거품 정렬
  for (let i = max.index; i > x; i--) {
    [A[i], A[i - 1]] = [A[i - 1], A[i]];
    S--;
  }

  if (!S) break;
}
```

- 지정한 x부터 x + S까지의 범위 내 최대 값을 찾는다.

- 최대 값의 위치부터 x의 위치까지 거품 정렬을 수행한다.

- x++ 하며 이를 반복한다.

## Review

N의 최대 값이 50으로 주어졌으므로, 단순 반복문을 통해 최대 값을 찾고 정렬하면 되겠다고 생각했다.