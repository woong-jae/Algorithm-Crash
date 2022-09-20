# [1562] 계단 수

## Algorithm

- DP
- Bitmask

## Logic

1~9로 시작해서 길이가 N인 계단수를 만든다.

이때 사용한 수를 비트 마스크로 기록한다. 길이가 N이 됐을 때, 비트 마스크로 모든 수를 사용했는지 확인한다.
비트 마스크가 $2^10 - 1$이라면 모든 수를 사용한 것이다.

```js
const dp = (cur, length, used) => {
  if (length <= 0) {
    return used === 2 ** 10 - 1 ? 1 : 0;
  }
  const key = `${cur}, ${length}, ${used}`;
  if (cache.has(key)) {
    return cache.get(key);
  }

  let result = 0;
  if (cur - 1 >= 0) {
    result = result + dp(cur - 1, length - 1, used | (1 << (cur - 1)));
  }
  if (cur + 1 < 10) {
    result = result + dp(cur + 1, length - 1, used | (1 << (cur + 1)));
  }

  result %= 1000000000;
  cache.set(key, result);
  return result;
};
```

## Review

비트마스크를 쓰는 방법만 알고있다면 쉽게 풀 수 있는 문제인 것 같다.