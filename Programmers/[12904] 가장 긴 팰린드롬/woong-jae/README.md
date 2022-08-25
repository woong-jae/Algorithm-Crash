# [12904] 가장 긴 펠린드롬

## Logic

- DP

## Algorithm

첫과 끝이 같은 문자열이 펠린드롬이려면, `첫 + 1`과 `끝 - 1`이 펠린드롬이면 된다.

```js
const isPalindrome = (start, end) => {
  if (dp[start][end] !== null) return dp[start][end];
  if (start >= end) return true;
  if (s[start] !== s[end]) return false;

  return (dp[start][end] = isPalindrome(start + 1, end - 1));
};
```

## Review
DP를 사용해서 쉽게 풀 수 있었지만 다른 더 좋은 방법이 있을 것 같다. 한 번 살펴봐야겠다.
