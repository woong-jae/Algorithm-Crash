# [87391] 공 이동 시뮬레이션

## Algorithm

- 없음

## Logic

쿼리를 역추적하면서 공이 있을 수 있는 범위를 구한다.

```js
for (let [command, dx] of queries) {
  switch (command) {
    case 0:
      if (left !== 0) left += dx;

      right += dx;
      if (right > m - 1) right = m - 1;

      break;
    case 1:
      left -= dx;
      if (left < 0) left = 0;

      if (right !== m - 1) right -= dx;

      break;
    case 2:
      if (top !== 0) top += dx;

      bottom += dx;
      if (bottom > n - 1) bottom = n - 1;

      break;
    case 3:
      top -= dx;
      if (top < 0) top = 0;

      if (bottom !== n - 1) bottom -= dx;

      break;
  }

  if (top >= n || bottom < 0 || left < 0 || right >= m) return 0;
}
```

## Review
처음에는 쿼리를 역추적하면서 DFS와 DP로 구현을 했는데, 1~3번 빼고 다 시간초과가 났다.

풀이 방법을 도저히 알 수가 없어서 결국 답을 봤다. 이런걸 어떻게 생각할까... 범위로 구한다니.
신박한 문제였다.