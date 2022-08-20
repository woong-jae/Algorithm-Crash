# [12908] A+B

## Algorithm

- BFS

- Backtracking

## Logic

- 한 좌표에서 점프하는데 걸리는 시간을 아래와 같이 구한다.

  ```js
  const jump = (x1, y1, x2, y2) => {
    return Math.abs(x1 - x2) + Math.abs(y1 - y2);
  };
  ```

- 텔레포트 이동으로 걸리는 시간을 아래와 같이 구한다.

  - 기존의 시간 + 텔레포트 좌표까지 점프 시간 + 10

  ```js
  let t1 = time + jump(x, y, x1, y1) + 10;
  ```

- 위 사항들을 조합하여 아래와 같이 BFS + 백트레킹을 수행한다.

  - 텔레포트 지점마다 최소 시간을 저장하고, 해당 시간보다 크면 continue한다.

  ```js
  while (queue.length) {
    const [x, y, time] = queue.shift();

    if (visited.get(`${x} ${y}`) < time) {
      continue;
    }

    visited.set(`${x} ${y}`, time);
    answer = Math.min(answer, time + jump(x, y, xe, ye));

    teleport.forEach(([x1, y1, x2, y2]) => {
      let t1 = time + jump(x, y, x1, y1) + 10;
      let t2 = time + jump(x, y, x2, y2) + 10;
      
      t1 < answer && queue.push([x2, y2, t1]);
      t2 < answer && queue.push([x1, y1, t2]);
    });
  }
  ```

## Review

2차원 배열을 선언하는 것이 매우 비효율적이라 생각했기에, 절댓값을 이용한 시간 계산 방법을 떠올렸다.

시간 계산 방법을 정하니 이후 로직은 술술 나왔다.
