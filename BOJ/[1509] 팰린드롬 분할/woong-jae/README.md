# [1509] 팰린드롬 분할

## Algorithm

- DP

## Logic

1. (left ~ right) 범위가 팰린드롬인지 나타내는 `isPalindrome` 배열을 만든다.

```js
const isPalindrome = Array.from(Array(N), () => Array(N).fill(false));
for (let mid = 0; mid < N; mid++) {
  let left = mid,
    right = mid;
  while (left >= 0 && right < N && input[left] === input[right]) {
    isPalindrome[left--][right++] = true;
  }

  (left = mid), (right = mid + 1);
  while (left >= 0 && right < N && input[left] === input[right]) {
    isPalindrome[left--][right++] = true;
  }
}
```

2. 최소 팰린드롬 분할은 (0 ~ x) 가 팰린드롬일 때, `1 + (x ~ N - 1)의 최소 팰린드롬 분할` 이다.

```js
const dp = Array(N + 1).fill(Infinity);
dp[N - 1] = 1;
dp[N] = 0;
for (let start = N - 2; start >= 0; start--) {
  for (let end = start; end < N; end++) {
    if (!isPalindrome[start][end]) continue;
    dp[start] = Math.min(dp[start], dp[end + 1] + 1);
  }
}
```

## Review
DP를 두 번 적용해야 하는 문제는 처음 풀어봤다. 이전에 가장 긴 팰린드롬이 떠올라서 그나마 빨리 풀 수 있었던 것 같다.
