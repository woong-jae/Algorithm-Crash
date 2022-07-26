# [64063] 호텔 방 배정
## Algorithm
- UnionFind
## Logic
우리는 어떤 방이 주어졌을 때 넣어 줄 빈방을 구해야한다.

만약 어떤 방이 빈 방이라면 그냥 주면 되지만, 차있다면 가장 가까운 빈방을 찾아야한다.

가장 간단한 방법은 어떤 방부터 오른쪽으로 순회하면서 찾는 방법이다.

하지만 잘 생각해보면, 우리의 목표는 현재 방에서 오른쪽으로 이어진 방들의 가장 오른쪽에 있는 방을 찾는 것, 즉, 현재 집합의 root를 찾는 것으로 바꿔 생각할 수 있다.
그렇다면 우리는 방을 찾을 때 현재 방이 해당하는 집합의 root인 방을 찾아(find) 할당해주고 root를 갱신해주면 된다.
Root를 갱신했는데 옆방이 다른 집합이면 그 집합과 합쳐야(union) 한다.

이는 모두 UnionFind의 기본 동작이다.

주의해야 할 것은 `k`의 크기가 너무 크기 때문에 배열로 union find를 할 수 없다. 따라서 sparse한 방을 저장하기 위해 `Map`을 사용했다.

```js
function solution(k, room_number) {
    const answer = [];
    const disjointSet = new UnionFind();
    
    room_number.forEach(room => {
        let emptyRoom = disjointSet.find(room);
        answer.push(emptyRoom);
        disjointSet.merge(emptyRoom, emptyRoom + 1);
    });
    
    return answer;
}
```

## Review
이어진 방을 보고 union find라는 것을 알아내면 쉽게 풀 수 있다.

빈 방을 찾는 것이 어떻게 보면 open addressing을 직접 하는 것 같다.