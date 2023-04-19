# [70130] 스타 수열
## Algorithm
- **단순 구현**

## Logic
- 먼저 스타 수열을 가능하게 하는 교집합이 되는 숫자 후보들을 구한다
    - 후보의 출현 개수가 많다는 것은 스타 수열의 길이가 길 수도 있다는 뜻
```java
Map<Integer, Long> candidate = Arrays.stream(a)
    .boxed()
    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
```

- 이후 문제의 조건에 따라 두 가지 경우를 확인한다
  - 교집합이 후보 키를 포함해야 한다
  - 두 개의 원소가 서로 달라야 한다
```java
for (int key : candidate.keySet()) {
    if (candidate.get(key) <= answer) {
        continue;
    }

    int count = 0;
    for (int index = 0; index < a.length - 1; index++) {
        if (a[index] == a[index + 1]) {
            continue;
        }
        if (a[index] != key && a[index + 1] != key) {
            continue;
        }
        count++;
        index++;
    }
    answer = Math.max(answer, count);
}
```

## Review
처음엔 재귀로 스타 수열을 다 만드는 풀이를 했는데 역시 시간초과가 나서 다른 사람의 풀이를 참고했다...  
근데 정말 좋은 아이디어인것 같고 다음에 비슷한 유형의 문제가 나온다면 충분히 적용할 수 있을 것 같다!  
좋은 문제!
