# [60063] 블록 이동하기

## Algorithm

- BFS

- hash

## Logic

**로봇 회전**

```js
const rotate = (left, right) => {
  const results = [];
  [1, -1].forEach((d) => {
    // 가로 -> 세로
    if (left[0] === right[0]) {
      if (
        isAccessible(left[0] + d, left[1]) &&
        isAccessible(right[0] + d, right[1])
      ) {
        results.push([[right[0] + d, right[1]], right]);
        results.push([left, [left[0] + d, left[1]]]);
      }
      return;
    }
    // 세로 -> 가로
    if (
      isAccessible(left[0], left[1] + d) &&
      isAccessible(right[0], right[1] + d)
    ) {
      results.push([[right[0], right[1] + d], right]);
      results.push([left, [left[0], left[1] + d]]);
    }
  });
  return results;
};
```

- 로봇이 가로인 경우와 세로인 경우를 나누어 회전한다.

  - 가로: 회전하여 세로가 되는 경우 4가지를 고려한다.

  - 세로: 회전하여 가로가 되는 경우 4가지를 고려한다.

  - 이때, 로봇의 왼쪽 부분 회전과 오른쪽 부분 회전은 서로 회전 경로가 겹치기 때문에, 각각의 회전 후 새로 도착하는 칸이 벽이 아닌지만 확인하면 된다.

<br />

**로봇 이동**

```js
const move = (left, right) => {
  const dx = [-1, 1, 0, 0];
  const dy = [0, 0, -1, 1];
  const results = [];

  for (let d = 0; d < 4; d++) {
    const nextLeft = [left[0] + dx[d], left[1] + dy[d]];
    const nextRight = [right[0] + dx[d], right[1] + dy[d]];

    if (isAccessible(...nextLeft) && isAccessible(...nextRight)) {
      results.push([nextLeft, nextRight]);
    }
  }
  return results;
};
```

- 상하좌우로 이동한다.

<br />

**BFS**

```js
const robotQueue = [new Robot([0, 0], [0, 1], 0)];
const visited = new Set(['0,0,0,1']);

while (robotQueue.length) {
  const robot = robotQueue.shift();

  if (isArrive(robot.left) || isArrive(robot.right)) {
    return robot.time;
  }

  const nextRobots = [
    ...move(robot.left, robot.right),
    ...rotate(robot.left, robot.right),
  ];

  nextRobots.forEach(([left, right]) => {
    const key = left.join(',') + ',' + right.join(',');
    if (!visited.has(key)) {
      robotQueue.push(new Robot(left, right, robot.time + 1));
      visited.add(key);
    }
  });
}
```

- queue를 이용하여 BFS를 수행한다.

  - 가장 먼저 `[N - 1, N - 1]` 위치에 도달하는 로봇의 `time` 프로퍼티를 반환한다.

- 이때, 같은 형태로 중복 방문하는 경우를 없애기 위해 Set 자료구조를 이용한다.

  - 로봇의 위치 배열을 문자열로 변환하여 Set에 저장한다.

### 시간 복잡도 : O(NlogN)

## Review

너무 힘들었다.

로봇의 방향 정보를 업데이트하며 회전하는 방법으로 수행했었는데, 그렇게 하자 코드가 장황해졌다.

틀린 결과가 나와 해당 원인을 찾으려 했지만 장황해진 코드로 인해 찾기 어려웠다.

다른 사람의 코드를 참고할까 했지만, 조금만 더 하면 해결할 수 있을 것 같았고 가로, 세로인 경우만 고려하여 회전하는 방법을 떠올려 해결할 수 있었다.

이 문제를 해결하는데 엄청나게 많은 시간이 소요됐다.. 얼른 다른 사람의 해결법을 참고하는 것이 더 나았을지도..
