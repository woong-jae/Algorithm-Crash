# [92341] 주차 요금 계산

## Algorithm

- Map

## Logic

(모든 시간은 분 단위로 변환하여 사용한다.)

- Key: 차량 번호, Value: { entryTime: 입차 시간, totalTime: 누적 시간 }` 형태의 Map을 생성한다.

  - 입차한 경우 `entryTime`에 입차 시간을 담는다.

  - 출차한 경우 출차 시간에 `entryTime`을 뺀 값을 `totalTime`에 더하고 `entryTime`을 null로 설정한다.

- 생성한 Map을 차량 번호 오름차순으로 순회하며 요금을 계산한다.

  - 만약 `entryTime`이 null이 아니면, '23:59' 시간에 `entryTime`을 뺀 값을 `totalTime`에 더한다.

  - `totalTime`에 대해 요금을 계산한다.

### 시간 복잡도 : O(NlogN)

## Review

문제에서 친절하게 보여준 주차 요금 계산법을 그대로 이행하면 된다.
