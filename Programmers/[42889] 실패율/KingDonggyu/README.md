# [42889] 실패율

## Algorithm

- sort

## Logic

```js
stages.forEach((stage) => {
  if (stage > N) return;
  challengeCnt[stage - 1]++;
});
```

- 각 stage의 개수를 저장한다.

```js
const stageInfo = [];
let userCnt = stages.length;

challengeCnt.forEach((cnt, i) => {
  stageInfo.push({
    stage: i + 1,
    failureRate: cnt / userCnt,
  });
  userCnt -= cnt;
});
```

- 저장한 stage의 개수를 순회하며 해당 개수를 user의 수(`userCnt`)로 나눈다.

  - 나눈 값 즉, 실패율과 해당 stage 번호를 객체 형식으로 `stageInfo`에 push한다.

  - 나눈 뒤, `userCnt`에 stage의 개수를 뺀다.

- `stageInfo`를 실패율을 기준으로 내림차순 정렬한다.

- 정렬한 `stageInfo`의 state 번호를 차례대로 가지는 배열을 리턴한다.

### 시간복잡도: O(NlogN)

## Review

😎
