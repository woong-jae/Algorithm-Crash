## :mag: Algorithm

Union-Find

## :round_pushpin: Logic

```js
const getRoomNumber = (x) => {
  if (map.has(x)) {
    const next = getRoomNumber(map.get(x));
    // value를 빈 방으로 업데이트
    map.set(x, next + 1);
    return next;
  }
  // 빈 방 찾음
  map.set(x, x + 1);
  return x;
};

room_number.forEach((num) => {
  const emptyRoom = getRoomNumber(num);
  answer.push(emptyRoom);
});
```

1. 원하는 방이 이미 배정되어 있는지 확인한다. => map에 있는지 확인한다.

2. map에 없으면 'key: 방번호, value: 방번호 + 1'을 map에 추가한다.

- value를 '방번호 + 1'로 하는 이유는 해당 방을 원하는 다른 고객이 접근했을 때 다음 방을 곧바로 배정해주기 위함이다.

3. map에 있으면 value에 저장되어 있는 방이 빈 방인지 다시 map에 조회한다. => **Find**

- map에 없으면 2 를 수행하고, 있으면 3 을 수행한다. (빈 방을 배정받을 때까지 반복)

- 반복하면서, 다음 조회에 빈 방을 빠르게 배정하기 위해 map에 저장되어 있는 value들을 빈 방을 가리키도록 업데이트한다. => **Union**

### 시간 복잡도: O(NlogN)

## :memo: Review

방이 이미 배정되어 있다면 원하는 방보다 번호가 **큰** 방을 배정한다는 점에서, 해당 방 보다 크면서 빈 방을 가리키는 cache를 구현해야겠다고 쉽게 떠올릴 수 있었다.