# [17676] 추석 트래픽

## Algorithm

Greedy

## Logic

```js
lines.forEach((line) => {
  // 시작 시간, 끝 시간 초기화
  const startTime = new Date(line.slice(0, 23));
  const endTime = new Date(startTime.getTime());

  // 처리량을 초 단위, 밀리초 단위로 분할
  const T = +line.slice(24, line.length - 1);
  const seconds = Math.floor(T);
  const milliseconds = (T % 1) * 1000;

  // 시작 시간 구하기
  startTime.setSeconds(startTime.getSeconds() - seconds - 1);
  startTime.setMilliseconds(startTime.getMilliseconds() - milliseconds + 2);

  // 초당 처리량 구하기
  for (let i = timeline.length - 1; i >= 0; i--) {
    if (startTime > timeline[i].end) break;
    answer = Math.max(answer, ++timeline[i].count);
  }

  timeline.push({ end: endTime, count: 1 });
});
```

- 각 line에 대해 시작 시간과 끝 시간을 구한다. (Date 생성자 이용)

  - 시작 시간은 처리량 T를 초와 밀리초 단위로 나누고, 이를 이용하여 초/밀리초를 업데이트하여 구한다.

  - 또한, 1초 간격의 양 끝에 두 line이 걸쳐있는 경우를 고려하기 위해 시작 시간을 1초 더 뺀다.

- `timeline` 배열을 순회하며 각 원소의 `end`(끝 시간)가 시작 시간보다 크거나 같으면 `count`(초당 처리량)을 +1 한다.

  - 효율성을 위해 역순으로 순회하며, 조건에 맞지 않을 경우 break 한다.

  - `timeline` 원소들의 `count` 중 가장 큰 것이 정답이다.

- `timeline`에 {해당 line의 끝 시간, 1(count)} 를 push 한다.

- lines를 모두 순회할 때까지 반복한다.

### 시간 복잡도: O(NlogN)

## Review

입력 형식을 보고, 입력 데이터를 Date 생성자에 넣으면 Date 객체로 변환될 것을 알았다.

이를 활용하여 쉽게 시작 시간을 구할 수 있었고, 역순 순회하며 초당 처리량을 구하여 해결했다.
