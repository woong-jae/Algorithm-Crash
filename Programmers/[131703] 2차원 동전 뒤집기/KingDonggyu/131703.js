function filpRow(coins, row) {
  for (let col = 0; col < coins[0].length; col++) {
    coins[row][col] = coins[row][col] ? 0 : 1;
  }
}

function flipColumn(coins, col) {
  for (let row = 0; row < coins.length; row++) {
    coins[row][col] = coins[row][col] ? 0 : 1;
  }
}

function solution(beginning, target) {
  const N = beginning.length;
  const M = beginning[0].length;
  const MAX = N * M + 1;

  let answer = MAX;
  const visited = Array(N + M).fill(false);

  function dfs(count) {
    if (answer <= count) {
      return;
    }

    for (let row = 0; row < N; row++) {
      for (let col = 0; col < M; col++) {
        if (beginning[row][col] === target[row][col]) {
          continue;
        }

        // 행 뒤집기
        if (!visited[row]) {
          visited[row] = true;
          filpRow(beginning, row);

          dfs(count + 1);

          visited[row] = false;
          filpRow(beginning, row);
        }

        // 열 뒤집기
        if (!visited[N + col]) {
          visited[N + col] = true;
          flipColumn(beginning, col);

          dfs(count + 1);

          visited[N + col] = false;
          flipColumn(beginning, col);
        }

        // 실패
        return;
      }
    }

    // 성공
    answer = count;
  }

  dfs(0);

  if (answer < MAX) {
    return answer;
  }

  return -1;
}
