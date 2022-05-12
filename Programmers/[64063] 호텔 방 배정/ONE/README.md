# [64063] 호텔 방 배정
## Algorithm
- **Union-Find**
## Logic
- 만약 찾는 방의 번호가 `Map`에 없다면 해당방의 다음 번호를 `Value`로 넣고 `Map`에 넣어준다
- 이미 찾는 방의 번호가 `Map`에 있다면 해당 방의 번호의 `Value`값으로 find 해서 빈방을 찾는다

```java
private long find(long num) {
    if (!map.containsKey(num)) {
        map.put(num, num + 1);
        return num;
    }

    long room = find(map.get(num));
    map.put(num, room);

    return room;
}
```

## Review
많이 풀어본 형식의 `Union-Find`라 금방해결 할 수 있었다
