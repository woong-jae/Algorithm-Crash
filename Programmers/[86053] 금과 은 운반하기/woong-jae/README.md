# [86053] 금과 은 운반하기

## Algorithm

- Binary search

## Logic

금 `a` kg과 은 `b` kg을 전달할 수 있는 가장 빠른 시간을 구하는 문제를 판별 문제로 바꾼다.
즉, 시간이 주어졌을 때 금 `a` kg과 `b` kg를 옮길 수 있는지 확인한다.

최대 시간은 (최대 금) _ (최대 은) _ (운반 무게가 최소) _ (최대 왕복 시간) = 2 _ (10 ** 9) _ 2 _ (10 ** 5) = 4 \* (10 \*\* 14)다.
0과 최대 시간 사이에서 이분 탐색으로 가장 빠른 시간을 구한다.

```js
while (left + 1 < right) {
  const mid = Math.floor((left + right) / 2);
  if (isPossible(mid)) right = mid;
  else left = mid;
}
```

판별할 때는 최대 옮길 수 있는 금의 수, 최대 옮길 수 있는 은의 수, 최대 옮길 수 있는 무게를 구해야한다.
최대 옮길 수 있는 금과 은의 수 각각이 a와 b보다 크고, 최대 옮길 수 있는 무게가 (a + b)보다 크면 옮길 수 있다고 판별할 수 있다.

```js
const isPossible = (time) => {
  let maxGold = 0,
    maxSilver = 0,
    maxWeight = 0;

  t.forEach((stt, i) => {
    const moves = time > stt ? Math.floor((time - stt) / (stt * 2)) + 1 : 0;
    const canCarry = moves * w[i];

    maxWeight += canCarry > g[i] + s[i] ? g[i] + s[i] : canCarry;
    maxGold += canCarry > g[i] ? g[i] : canCarry;
    maxSilver += canCarry > s[i] ? s[i] : canCarry;
  });

  if (maxGold < a || maxSilver < b || maxWeight < a + b) return false;
  return true;
};
```

## Review

이분 탐색을 해야하는 문제인건 바로 알았다. 판별하는 부분을 어떻게 구해야할지 몰라서 '질문하기'를 봤다.
좀만 더 생각해봤으면 알 수 있었을 것 같은 아이디어였다. 아쉽다... 그래도 이분탐색 사용하는건 알았으니 다행이다.
