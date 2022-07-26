# [92334] 신고 결과 받기

## Algorithm
- Map

## Logic

```java
// 유저별 신고당한 횟수 저장 map
Map<String, Integer> reportMap = new HashMap<>();
// 유저별 신고한 유저 저장 map
Map<String, ArrayList<String>> userReportMap = new HashMap<>();
// 유저별 메일 받을 횟수 저장 map
Map<String, Integer> mailMap = new HashMap<>();
```

- 유저별로, 신고당한 횟수와 신고한 유저 리스트, 그리고 정지 기준을 넘은 유저를 신고한 횟수를 저장하는 map을 사용한다.

## Review
- 간단한 구현 문제였으나, 비효율적으로 구현한 것 같다.