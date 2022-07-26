# [42891] 무지의 먹방 라이브

## Algorithm

- sort

## Logic

```js
const foods = food_times
  .map((time, i) => ({ number: i + 1, time: time }))
  .sort((a, b) => a.time - b.time);
```

- 음식의 번호와 시간을 가진 객체들을 원소로 하는 배열(`foods`)을 생성하고, 이를 시간을 기준으로 오름차순 정렬한다.

```js
for (let i = 0; i < foodCount; i++) {
  const leftoverCount = foodCount - i;
  const eatTime = (foods[i].time - prevTime) * leftoverCount;

  prevTime = foods[i].time;

  if (k < eatTime) {
    const leftoverFoods = foods.slice(i).sort((a, b) => a.number - b.number);
    return leftoverFoods[k % leftoverCount].number;
  }

  k -= eatTime;
}
```

- 정렬된 배열(`foods`)을 순회한다.

  - `(현재 음식의 시간 - 이전 음식의 시간) * 현재 남아있는 음식의 개수` 로 현재 음식을 먹는데 필요한 시간(`eatTime`)을 계산하고, 이를 `k`에 뺀다.

    - 여기서 이전 음식의 시간은 현재까지 소요된 시간을 뜻한다.

  - 이를 순회하며 반복하다 `k`가 `eatTime` 보다 작아지는 경우,

    - `foods`의 `i` 인덱스 이전 객체들을 모두 제거하고, 남은 객체들을 번호를 기준으로 오름차순 정렬한다.

    - 그리고 `k % 남은 음식의 개수` 를 인덱스로 가진 객체의 번호를 리턴한다.

- 위 순회 과정에서 `k` 시간을 다 소요하지 못했으면 -1을 리턴한다.

### 시간 복잡도: O(NlogN)

## Review

많이 헤맸다. 

처음에 전체 음식의 개수를 `k`로 나눈 몫과 나머지를 이용해 배열을 순회하는 방법으로 시도했었다.

그 과정에서 구현에 어려운 부분이 많았고, 이를 연결 리스트로 바꾸었다.

그래도 해결하지 못하여, 결국 다른 사람의 접근 방법을 보았고 이를 참고해 코드를 작성했다.

효율성 테스트가 있기에 정렬하는 방법은 아에 생각도 안했었다..
