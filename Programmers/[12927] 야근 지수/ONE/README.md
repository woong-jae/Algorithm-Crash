# [12927] 야근 지수
## Algorithm
- **Priority Queue**

## Logic
- 남은 각각의 작업량들의 수가 고르게 작도록 해야한다
  - 가장 큰 것 부터 한 시간마다 작업량이 1 줄도록 하려면 우선순위큐를 사용해 내림차순 정렬되게 해서 가장 큰 작업부터 줄이고 다시 큐에 넣는 작업을 N 동안 반복

```java
Queue<Integer> remainWorks = new PriorityQueue<>(Comparator.reverseOrder());

for (int work : works) {
    remainWorks.add(work);
}

while (n-- > 0) {
    int work = remainWorks.poll();
    remainWorks.add(--work);
}

return remainWorks.stream()
    .filter(work -> work > 0)
    .mapToLong(work -> (long) work * work)
    .sum();
```

## Review
우선순위큐를 이용하는 쉬운문제
