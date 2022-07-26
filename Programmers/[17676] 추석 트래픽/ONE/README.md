# [17676] 추석 트래픽
## Algorithm
- **Greedy**

## Logic
- 시간들을 모두 ms 단위로 바꾸어 한번에 다 더한후 **요청시간**과 **응답시간**으로 구분하고 `Log`와 `timeList`에 넣는다
- 시간들 별로 로그들을 검사하여 3가지 경우로 나누어 해당 경우라면 카운트
  - 로그의 요청시간이 시간의 1초 범위 내에 있을 때
  - 로그의 응답시간이 시간의 1초 범위 내에 있을 때
  - 로그의 요청시간이 시간의 1초 범위를 포함하고 있을 때


```java
for (int time : timeList) {
    int left = time;
    int right = time + 999;
    int count = 0;

    for (Log log : logList) {
        if ((log.requestTime >= left && log.requestTime <= right)
                || (log.responseTime >= left && log.responseTime <= right)
                || (log.requestTime <= left && log.responseTime >= right))
            count++;
    }
    answer = Math.max(answer, count);
}
```

## Review
문제에 대한 아이디어는 빨리 떠올라서 시간의 요청시간과 응답시간을 기준으로  
조건을 찾아야 하는 것을 알고 있었는데, 마지막 조건인 로그가 시간 범위를   
포함한 경우를 생각하지 못해, 계속 2,3,18번만 실패했고 질문을 참고하여 해결했다  
아까운 문제
