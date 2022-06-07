# [42889] 실패율
## Algorithm
- **Sorting**

## Logic
- 스테이지의 번호가 주어지면 해당 스테이지를 포함한 이전 스테이지들의 도달한 사람 수 +1
- 그리고 해당 스테이지의 현재 사람 수도 +1
- 실패율을 각각 구하여 실패율 기준으로 내림차순 정렬
- 실패율이 같다면 번호순서로 오름차순 정렬

```java
for (int stage : stages) {
    for(int i = 1; i <= stage; i++)
        list.get(i - 1).reachNum++;
    list.get(stage - 1).currentNum++;
}

for(Stage stage : list)
    stage.setFailureRate();

list.remove(N);

list.sort((o1, o2) -> {
    if (o1.failureRate == o2.failureRate)
        return o1.num - o2.num;
    else if(o2.failureRate > o1. failureRate)
        return 1;
    else
        return -1;
});
```

## Review
쉬운 문제
