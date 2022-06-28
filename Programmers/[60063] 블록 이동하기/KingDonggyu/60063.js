function Robot(left, right, time) {
  this.left = left;
  this.right = right;
  this.time = time;
}

function solution(board) {
  const N = board.length;

  const isArrive = (target) => {
    return target[0] === N - 1 && target[1] === N - 1;
  };

  const isAccessible = (x, y) => {
    return x >= 0 && x < N && y >= 0 && y < N && !board[x][y];
  };

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

  const robotQueue = [new Robot([0, 0], [0, 1], 0)];
  const visited = new Set(['0,0,0,1']);

  // BFS
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
}
