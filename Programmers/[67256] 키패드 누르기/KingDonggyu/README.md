## :mag: Algorithm

-

## :round_pushpin: Logic

```js
const row = [null, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3];
const col = [null, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2];
```

- 각 번호에 대한 row, col 값을 가진 배열을 선언한다.

- 이떄, **0은 숫자 11과 같다고 가정한다.**

```js
numbers.forEach((num) => {
  if (num === 0) num = 11;

  let nextHand = 'L';
  // 오른손을 사용하는 경우만 확인
  if (col[num] === 1) {
    const leftDist = abs(row[num] - row[left]) + abs(col[num] - col[left]);
    const rightDist = abs(row[num] - row[right]) + abs(col[num] - col[right]);

    if (leftDist > rightDist || 
        (leftDist === rightDist && hand === 'right')) {
      nextHand = 'R';
    }
  } else if (col[num] === 2) {
    nextHand = 'R';
  }

  if (nextHand === 'L') left = num;
  else right = num;
  answer += nextHand;
});
```

- 오른손을 사용하는 경우만 고려한다. 해당 경우들에 해당되지 않으면 왼손을 사용한 것으로 가정.

- 만약 col이 1인 수(2, 4, 8, 11(0))이 주어지면 row의 차이, col의 차이를 이용하여 왼손과 오른손을 결정한다.

<br />

**시간 복잡도: O(N)**

## :memo: Review

처음엔 매 반복 때마다 각 숫자에 해당하는 좌표를 구하는 방식으로 해결했다.

이후, 각 수에 해당하는 row와 col을 표현하는 배열을 직접 정의하는게 더 효율적이라 생각했고, 코드를 수정하니 테스트 20의 시간이 **0.70ms에서 0.33ms로** 줄었다.