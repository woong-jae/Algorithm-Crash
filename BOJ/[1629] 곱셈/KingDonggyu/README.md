# [1692] 곱셈

## Algorithm

- Divide and Conquer

## Logic

아래 두 법칙을 이용하여 분할 정복을 수행한다.

- 지수 법칙

  - `exp` 가 짝수

    ```
    x^exp = x^(exp/2 + exp/2) = x^(exp/2) * x^(exp/2)
    ```

  - `exp` 가 홀수
    ```
    x^exp = x^(exp/2 + exp/2 + 1) = x^(exp/2) * x^(exp/2) + x
    ```

- 나머지 연산 법칙

  ```
  (x * y) % C = (x % C) * (y % C) % C
  ```

<br />

이를 이용한 코드는 아래와 같다.

```js
const power = (exp) => {
  if (exp === 0) {
    return 1;
  }
  if (exp === 1) {
    return A % C;
  }
  const x = power(Math.floor(exp / 2)) % C;
  return exp % 2 ? (x * x * A) % C : (x * x) % C;
};
```

## Review

처음에 문제를 읽고 만만하게 봤었는데.. 결국 스스로의 힘으로 풀지 못했다.

다른 사람의 접근법을 참고했고, 지수 법칙과 나머지 법칙에 대해 알게되어 이를 활용했다.
