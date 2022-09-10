# [68645] 삼각 달팽이

## Algorithm

\-

## Logic

```js
while (count > 0) {
  for (let i = 0; i < count; i++) {
    triangle[++row][col] = ++number;
  }
  for (let i = 0; i < count - 1; i++) {
    triangle[row][++col] = ++number;
  }
  for (let i = 0; i < count - 2; i++) {
    triangle[--row][--col] = ++number;
  }
  count -= 3;
}
```

- 왼쪽 채우기, 아래 채우기, 오른쪽 채우기를 순서대로 반복한다.

- `count`는 첫 왼쪽 채우기 횟수이며, 패턴에 따라 한 사이클 후 3을 뺀다.

### 시간 복잡도 : O(NlogN)

## Review

패턴을 파악하니 쉽게 풀었다.
