# [92341] 주차 요금 계산
## Algorithm
- **Map**

## Logic
- 주차장의 차와 해당의 입차 시간을 나타낼 parkingLot Map을 생성
- 누적 시간을 저장할 <차번호, 시간> Map 생성
- 입차 했을 때는 parkingLot에 현재 시각과 차번호를 저장
- 출차할때는 parkingLot에서 가져온 입차 시간과 주어진 출차시간으로 시간을 계산하여 누적맵에 더하고 parkingLot에서 해당 차번호를 제거한다
- 출차 기록이 없는 차를 계산하기 위해 parkingLot에 남아있는 차로 출차시간 "23:59" 으로 두고 누적 시간을 계산
- 누적합을 가지고 차번호를 정렬하여 주어진 조건에 따라 요금을 계산

```java
Map<String, Time> parkingLot = new HashMap<>();
Map<String, Integer> accumulateMap = new HashMap<>();

for (String record : records) {
    StringTokenizer st = new StringTokenizer(record);
    int time = timeToInt(st.nextToken());
    String number = st.nextToken();
    String move = st.nextToken();

    if (move.equals("IN"))
        parkingLot.put(number, new Time(time));
    else {
        accumulateMap.put(number,
                accumulateMap.getOrDefault(number, 0) + parkingLot.get(number).calculate(time));
        parkingLot.remove(number);
    }
}

for (String number : parkingLot.keySet())
    accumulateMap.put(number,
            accumulateMap.getOrDefault(number, 0) + parkingLot.get(number).calculate(timeToInt("23:59")));

int index = 0;
int[] answer = new int[accumulateMap.size()];

for(String number : accumulateMap.keySet().stream().sorted(Comparator.naturalOrder())
        .collect(Collectors.toCollection(LinkedHashSet::new)))
    answer[index++] = feeHandling(accumulateMap.get(number), fees);
```

## Review
쉬운문제!