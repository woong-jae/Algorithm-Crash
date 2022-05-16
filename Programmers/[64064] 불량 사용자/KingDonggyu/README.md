## :mag: Algorithm

DFS

## :round_pushpin: Logic

1. `banned_id` 별 매치되는 `user_id` 인덱스를 가지는 배열을 생성한다.

```js
const match = new Array(banned_id.length).fill(0).map((_) => []);
banned_id.forEach((ban, banIndex) => {
  user_id.forEach((user, userIndex) => {
    if (ban.length !== user.length) {
      return;
    }
    for (let i = 0; i < ban.length; i++) {
      if (ban[i] !== '*' && ban[i] !== user[i]) {
        return;
      }
    }
    match[banIndex].push(userIndex);
  });
});
```

<br />

2. DFS를 수행한다.

- `visited`를 업데이트하며 중복되는 값 발생 시 탐색을 멈춘다.

  - 0 = 방문 x, 1 = 방문 o

- `banned_id.length` 만큼의 깊이 탐색이 일어났을 경우, 탐색을 멈추고 `visited` 를 문자열로 변환한 값을 set에 넣는다.

- 모든 경우의 탐색이 종료되면 set의 size를 리턴한다.

```js
const set = new Set();
const findBanList = (banIndex, visited) => {
  if (banIndex === banned_id.length) {
    set.add(+visited.join(''));
    return;
  }
  match[banIndex].forEach((userIndex) => {
    if (visited[userIndex]) {
      return;
    }
    visited[userIndex] = 1;
    findBanList(banIndex + 1, visited);
    visited[userIndex] = 0;
  });
};
findBanList(0, new Array(user_id.length).fill(0));
return set.size;
```

### 시간 복잡도: O(N^2)

## :memo: Review

처음에는 DFS를 수행하며 set에 방문한 값을 넣으며 set의 길이가 탐색 깊이와 같은지 여부를 따지는 식으로 했다.

그리고 마지막에 set을 정렬하여 map에 넣고, 다음 탐색 시 map에 이미 같은 값이 있는지 따진 후, map의 길이를 정답으로 리턴했다.

이번에 다시 풀어보면서, 매번 정렬하는 것은 매우 비효율적이기에 `visited` 배열을 이용하여 방문 여부를 따지고, 이를 set에 넣는 형식으로 변경하니 테스트 케이스 5번 시간을 209.98ms에서 24.86m로 줄일 수 있었다.
