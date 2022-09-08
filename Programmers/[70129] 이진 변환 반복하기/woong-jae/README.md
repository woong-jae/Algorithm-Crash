# [70129] 이진 변환 반복하기

## Algorithm

- 구현

## Logic

1. 이진 변환 함수를 구현한다.

```js
const binaryTransfer = (s) => {
  const result = s
    .split("")
    .filter((c) => {
      if (c === "0") {
        deleteCount += 1;
        return false;
      }
      return true;
    })
    .join("");

  return result.length.toString(2);
};
```

2. 반복 횟수와 제거한 0의 개수를 계산한다.

## Review
너무 쉬운 문제.
