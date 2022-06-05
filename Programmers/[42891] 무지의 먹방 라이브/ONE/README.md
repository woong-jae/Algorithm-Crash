# [42891] 무지의 먹방 라이브
## Algorithm
- **Sorting**

## Logic
- 음식을 먹는데 걸리는 시간의 오름차순으로 정렬한다
- 음식을 먹는데 시간이 적게 걸리는 것부터 먹으면서 K 에서 시간을 빼간다
- 만약 남은 시간 K 가 먹어야 하는 시간보다 적을 때는 K 에서 현재 남은 음식의 나머지 연산을 수행하고  
- 남은 음식들을 번호 순서의 오름차순으로 정렬하여 나머지 연산을 수행한 K번째 인덱스의 음식의 번호를 찾는다

```java
int index = 0;
int previous = 0;
for (Food current : list) {
    long diff = current.time - previous;

    if (diff != 0) {
        long spend = diff * n;

        if (spend <= k) {
            k -= spend;
            previous = current.time;
        }
        else {
            k %= n;
            list.subList(index, list.size()).sort(Comparator.comparingInt(o -> o.num));
            return list.get(index + (int) k).num;
        }
    }
    ++index;
    --n;
}
```

## Review
처음에는 Queue를 이용해서 구현했었는데 역시 효율성 때문에 통과하지 못해서  
다른 접근법을 생각하지 못해 다른 코드를 참고하려했는데 다른 코드도 이해하는데에  
오래 걸려서 유튜브에서 문제에 대한 해결방법 강의를 보고 이해할 수 있었다...
