## :mag: Algorithm

Dictionary

## :round_pushpin: Logic

```js
const map = new Map();
const count = new Set(gems).size;

let answer = [1, gems.length + 1];
let left = 0;
let right = -1;
```

- `Set`을 이용하여 보석 종류의 개수를 파악한다.

- `left`에서 `right`까지의 구간 안에 존재하는 보석에 대한 `Map`(딕셔너리)을 생성한다.

  - 키 = 보석 이름, 값 = 보석의 개수

```js
while (right < gems.length) {
  if (map.size === count) { // (1)
    if (answer[1] - answer[0] > right - left) {
      answer = [left + 1, right + 1];
    }
    map.set(gems[left], map.get(gems[left]) - 1);
    if (map.get(gems[left]) === 0) {
      map.delete(gems[left]);
    }
    left++;
  } else { // (2)
    right++;
    if (map.get(gems[right])) {
      map.set(gems[right], map.get(gems[right]) + 1);
    } else {
      map.set(gems[right], 1);
    }
  }
}
```

- (1) : `map` 안에 모든 종류의 보석이 있는 경우

  - 구간(`right - left`)가 `answer` 원소의 차 보다 작을 경우, `answer`을 업데이트한다.

  - 시작 지점의 보석을 `map`에 -1 한 후, `left`를 한 칸씩 옮긴다.

    - 만약, 보석 개수가 0이 될 경우, 해당 키를 제거한다.

- (2) : `map` 안에 모든 종류의 보석이 없는 경우

  - 모든 종류의 보석이 있을 때까지 `right`를 한 칸씩 옮긴다.

  - 이미 있는 보석을 발견하면 기존 개수에 +1 한다.

### 시간 복잡도

O(2N) = `left`가 N이 될 때까지 증가 + `right`가 N이 될 때까지 증가

O(2N)이 최악의 시간이 되므로, 시간 복잡도는 **O(N)** 이다.

## :memo: Review

딕셔너리를 만들어 이전 구간에 대한 정보를 사용하는 아이디어를 떠올려 적용했지만..

계속 시간초과가 나왔다.

방법이 틀린줄 알고 다른 방법을 생각해보며, 계속 새로운 시도를 해보느라 엄청난 시간을 소요했다.

그러다, 첫번째 방법을 다시 시도해보았고 Object가 아닌 Map을 사용하니 해결되었다. 당황스러웠다.