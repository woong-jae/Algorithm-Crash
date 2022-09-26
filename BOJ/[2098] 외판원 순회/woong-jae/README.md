# [2098] 외판원 순회

## Algorithm

- DP
- Bitmask

## Logic

1. 0에서 a를 먼저 거치는 TSP는, (a에서 0과 a를 제외한 TSP) + (0 부터 a까지 거리)와 같다.

```js
const dp = (start, cur, visited) => {
  if (visited === 2 ** n - 1) {
    return adjMatrix[cur][start];
  }
  const key = `${start}, ${cur}, ${visited}`;
  if (cache.has(key)) return cache.get(key);

  let result = Infinity;
  adjMatrix[cur].forEach((cost, next) => {
    if (visited & (1 << next)) return;
    result = Math.min(result, cost + dp(start, next, visited | (1 << next)));
  });

  cache.set(key, result);
  return result;
};
```

0에서 부터 TSP와 0과 n사이중 어느 곳에서 TSP를 한 값은 같다.

## Review
0에서 시작하는 것 하나만 해도 되는걸 생각하지 못해서 메모리 초과가 났다. 문제 자체는 빨리 풀었는데, 메모리 초과 때문에 엄청 삽질했다...
