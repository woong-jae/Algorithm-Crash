# [17680] 캐시

## Algorithm
- 큐

## Logic

```java
for (String city : cities) {
    city = city.toLowerCase();
    // 이미 캐시에 있다면
    if (cache.contains(city)) {
        cache.remove(city);
        time++;
    } else {
        // 캐시에 없는데, 더 이상 캐시 공간도 없는 경우, 처음 들어온 값을 삭제
        if (cache.size() >= cacheSize) cache.poll();
        time += 5;
    }
    cache.add(city);
}
```
- `Queue` 를 사용해 들어온 도시순으로 캐싱한다.
- `Hit` 이 발생한다면, 해당 도시를 지우고 다시 맨 뒤에 추가한다.

## :black_nib: **Review**

- 매우 쉬운 문제였지만, LRU 알고리즘을 대충 생각하고 풀었다가 시간 날려버렸다. 이런...