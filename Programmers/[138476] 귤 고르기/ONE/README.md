# [138476] 귤 고르기
## Algorithm
- **Sorting**

## Logic
- 귤을 같은 크기끼리 묶고 개수들을 내림차순으로 정렬한다
```java
List<Long> groups = Arrays.stream(tangerine)
    .boxed()
    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
    .values()
    .stream()
    .sorted(Comparator.reverseOrder())
    .collect(Collectors.toList());
```

- k 개 만큼의 개수가 될 때까지 귤의 개수를 빼고 크기 종류의 개수를 센다
```java
int count = 0;
for (long group : groups) {
    count++;
    if (k - group <= 0) {
        break;
    }
    k -= group;
}
return count;
```

## Review
쉬운 문제