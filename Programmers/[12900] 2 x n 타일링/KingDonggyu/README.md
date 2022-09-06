# [12900] 2 x n 타일링

## Algorithm

- Dynamic Programming

## Logic

배열에 메모제이션하는 피보나치 수열을 수행한다.

### 시간 복잡도 : O(N)

## Review

처음에 아래 코드로 제출했더니 효율성에서 시간초과가 났다.

```js
function solution(n) {
  const fibo = [1, 1];
  for (let i = 2; i <= n; i++) {
    fibo[i] = (fibo[i - 1] + fibo[i - 2]) % 1000000007;
  }
  return fibo[n];
}
```

그래서 조금이나마 연산을 덜하도록 아래와 같이 고치니 통과했다..? 🤔

```js
function solution(n) {
  const fibo = [1, 1, 2, 3, 5, 8];
  for (let i = 6; i <= n; i++) {
    fibo[i] = (fibo[i - 1] + fibo[i - 2]) % 1000000007;
  }
  return fibo[n];
}
```