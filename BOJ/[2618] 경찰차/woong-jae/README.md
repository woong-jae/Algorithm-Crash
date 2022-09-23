# [2618] 경찰차

## Algorithm

- DP

## Logic

1. 현재 case에서 경찰차1을 골랐을 때와 경찰차2를 골랐을 때를 비교해서 더 작은 것을 고른다.

```js
const dp = (car1, car2, caseIndex) => {
  if (caseIndex === cases.length) {
    return 0;
  }
  const key = `${car1}, ${car2}, ${caseIndex}`;
  if (cache.has(key)) return cache.get(key)[0];

  const currentCase = cases[caseIndex];

  const car1Position = car1 === -1 ? [1, 1] : cases[car1];
  const car2Position = car2 === -1 ? [N, N] : cases[car2];

  const car1MovingDist =
    calcDistance(car1Position, currentCase) + dp(caseIndex, car2, caseIndex + 1);
  const car2MovingDist =
    calcDistance(car2Position, currentCase) + dp(car1, caseIndex, caseIndex + 1);

  const result = Math.min(car1MovingDist, car2MovingDist);
  cache.set(key, [result, car1MovingDist < car2MovingDist ? 1 : 2]);
  return result;
};
```

2. 결과를 저장할 때, 어떤 차를 골랐는지 저장해서 나중에 경로를 찾을 수 있게 한다.

```js
let car1 = -1,
  car2 = -1;
const path = [];
for (let i = 0; i < W; i++) {
  const key = `${car1}, ${car2}, ${i}`;
  const picked = cache.get(key)[1];
  path.push(picked);
  picked === 1 ? (car1 = i) : (car2 = i);
}
```

## Review
어떻게 꾸역꾸역 풀긴 했는데, 엄청 비효율적으로 푼 것 같다. 지금은 cache의 최대 크기가 1000 ^ 3인데, 아마 1000 ^ 2로 풀 수 있을 것 같다.

다른 사람 풀이를 참고해봐야겠다. 플레 문제는 역시 쉽지 않구만...