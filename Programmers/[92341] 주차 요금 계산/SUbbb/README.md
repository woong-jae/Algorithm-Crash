# [92341] 주차 요금 계산

## Algorithm
- Map
- 구현

## Logic

```java
// 차 번호별 입차 시간을 저장하는 map
static Map<String, Integer> carTimeMap = new HashMap<>();
// 차 번호별 누적 주차 시간을 저장하는 map
static Map<String, Integer> accumCarMap = new HashMap<>();
```

- 차 번호별 입차 시간을 저장하는 map을 사용해, 입차 시에는 map에 추가하고, 출차 시에는 입차 시간과 비교하여 주차 시간을 구한다.
- 차 번호별 누적 주차 시간을 저장하는 map을 사용해 차 번호별 주차 시간을 누적합한다.

```java
readRecords(records);

processRemainingCars();

calculateFees();
```

- `readRecords()` 는 주차 기록을 읽으면서 차 번호별 주차 시간을 계산한다.
- `processRemainingCars()` 에서는 출차 기록이 없는 차에 대한 처리를 수행한다.
- `calculateFees()` 에서는 차 번호별 주차 요금을 계산하고 반환할 배열에 담는다.

## Review
- 하라는 대로 하면 되는 문제였으나, 자꾸 37.5점이 나오길래 `map` 에 접근하는 코드들을 다 뜯어보다가 어이없는 실수를 한 것을 확인했다.

```java
// 출차 기록이 없는 차 처리
private static void processRemainingCars() {
    int time = 1439;

    for (Map.Entry<String, Integer> entry : carTimeMap.entrySet()) {
        String carN = entry.getKey();
        time = time - entry.getValue();
        
        accumCarMap.put(carN, accumCarMap.getOrDefault(carN, 0) + time);
    }
}


// 출차 기록이 없는 차 처리
private static void processRemainingCars() {
    for (Map.Entry<String, Integer> entry : carTimeMap.entrySet()) {
        String carN = entry.getKey();
        int time = 1439 - entry.getValue();
        
        accumCarMap.put(carN, accumCarMap.getOrDefault(carN, 0) + time);
    }
}
```

- `time` 값은 1439로 고정되어 있어야 했는데, 반복문을 돌면서 자꾸 감소하도록 구현해놓아서 틀렸던 것이다...