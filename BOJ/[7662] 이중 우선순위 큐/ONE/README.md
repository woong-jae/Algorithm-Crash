# [7662] 이중 우선순위 큐
## Algorithm
- **Priority Queue**, **Map**

## Logic
- 우선 순위큐를 2개 사용한다 (오름차순 정렬, 내림차순 정렬)
- 숫자를 키, 해당 숫자의 개수를 저장하는 Map 을 사용
- 삽입은 우선순위 큐 2개 모두에 추가하고 map에도 추가한다
- 삭제할 때는 해당하는 큐를 poll()하고 Map에서 해당 숫자의 개수를 가져와
  - 0개이면 해당 숫자가 존재하지 않으므로 다음 숫자를 queue에서 poll()하여 반복수행한다
  - 1개라면 맵에서 해당 숫자를 삭제하고 숫자를 반환
  - 2개 이상이라면 맵에서 해당 숫자의 개수를 1개 줄이고 해당 숫자를 반환한다

```java
private static void insert(int n) {
    ascendingQueue.add(n);
    descendingQueue.add(n);
    map.put(n, map.getOrDefault(n, 0) + 1);
}

private static int delete(Queue<Integer> queue, Map<Integer, Integer> map) {
    int result = 0, count;

    while (!queue.isEmpty()) {
        result = queue.poll();
        count = map.getOrDefault(result, 0);

        if (count == 0) continue; // 해당 숫자가 존재하지 않으면 map 에서 제거 하고 다음 조건에 해당되는 수를 찾는다

        if (count == 1) // 1개 밖에 없을 경우 맵에서 삭제
            map.remove(result);
        else
            map.put(result, count - 1); // 2개 이상일 경우 개수를 1개 줄인다
        break;
    }
    return result;
}
```

## Review
처음에는 우선순위 큐 2개만을 가지고 삽입과 삭제를 구현했는데 시간초과가 발생했다  
그래서 알고리즘 분류에 Map이 있는걸 봤고 Map을 이용해 시간을 단축 시킬수 있다는 생각이 들어 사용했고 통과할 수 있었다  
발상의 전환 문제!