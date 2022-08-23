# [12908] 텔레포트 3

## Algorithm

- Bruteforce
- Bitmask

## Logic

현재 위치에서 목적지까지 바로 가는 것과, 텔레포트로 가는 것중 시간이 제일 적게 걸리는 것을 선택한다.

이때 한 번 사용한 텔레포트는 다시 사용하지 않도록 비트마스크로 표시한다.

```js
const minDistance = (from, to, used) => {
  let result = distance(from, to);

  teleports.forEach((teleport, i) => {
    if (used & (1 << i)) return;

    let [x1, y1, x2, y2] = teleport.map((e) => +e);
    if (distance(from, [x1, y1]) > distance(from, [x2, y2])) {
      [x1, y1, x2, y2] = [x2, y2, x1, y1];
    }
    result = Math.min(
      result,
      distance(from, [x1, y1]) + 10 + minDistance([x2, y2], to, used | (1 << i))
    );
  });

  return result;
};
```

## Review

처음에 문제를 읽고 어지러울뻔 했다. 하지만 막상 해보니 크게 어렵지 않았던 문제. 텔레포트로 이동할 때 그냥 가는것보다 크면 못가게하면 더 효율적으로 동작할 것 같다.
