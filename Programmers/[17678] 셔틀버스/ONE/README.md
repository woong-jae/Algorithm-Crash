# [17678] 셔틀버스

## Algorithm
- **Priority Queue**

## Logic
- 시간과 분을 저장할 Time class 생성
- 선입 선출이기 때문에 큐를 사용하고 시간순서로 정렬을 하기 위해 우선순위 큐를 사용
- 콘은 제일 늦게 출근하기 위해 n번째로 오는 마지막 셔틀버스를 타야한다
- 이때 2가지 경우가 있는데
  - 하나는 사람이 가득하면 콘이 마지막 사람보다 1분 빨리 줄을 서야한다
  - 다른 경우는 자리가 남으면 셔틀이 도착하는 시간으로 타면 가장 늦은 시간으로 탈 수 있는 경우

```java
for (int i = 1; i <= n; i++) {
    List<Time> canRideList = new ArrayList<>();

    for (int j = 0; j < m; j++)
        if(!line.isEmpty() && line.peek().canRide(bus))
            canRideList.add(line.poll());

    // 콘은 마지막 셔틀을 타야 가장 늦게 탈 수 있다
    if (i == n) {
        // 사람이 가득하면 콘이 마지막 사람의 시간에서 1분을 빼고 줄을 서야한다
        if (canRideList.size() == m) {
            Time con = canRideList.get(m - 1);
            con.makeTime();
            return con.timeToString();
        }
        // 자리가 남으면 셔틀이 도착하는 시간으로 타면 가장 늦은 시간으로 탈 수 있다
        else
            return bus.timeToString();
    }
    bus.nextBus(t);
}
```

## Review
문제를 이해하는데 시간이 좀 걸렸지만 구현자체는 빨리했던 문제
