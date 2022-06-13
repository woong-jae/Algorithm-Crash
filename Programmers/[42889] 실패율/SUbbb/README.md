# [42889] 실패율

## Algorithm
- 정렬

## Logic

```java
for (int i = 0; i < N; i++)
    userStage.add(new Stage(i + 1));

for (int s : stages) {
    if (s > N) continue;
    userStage.get(s - 1).incFail();
}

for (Stage s : userStage) {
    if (users == 0) break;
    double tmp = s.fail / users;
    users -= s.fail;
    s.setFail(tmp);
}
```

- `ArrayList<Stage>` 를 사용해 도달한 인원 수와 실패율을 저장
- 난이도별 도달 인원 수를 0으로 초기화한 후, 실패율 계산을 위한 도달했지만 클리어하지 못한 인원 수를 계산
- 앞 난이도부터, **클리어하지 못한 인원 수 / 전체 인원**로 실패율을 계산한다.
  - 이후 전체 인원은 해당 난이도에 도달했지만 클리어하지 못한 인원 수만큼 차감한다.

## Review
- 쉬운 문제였으나 좀 더 효율적인 자료구조를 빠른 시간 내에 떠올리지 못한 게 문제였다.