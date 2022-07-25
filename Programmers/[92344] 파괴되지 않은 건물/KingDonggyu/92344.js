function solution(board, skill) {
  const [N, M] = [board.length, board[0].length];
  const degreeSum = Array.from(Array(N + 1), () => Array(M + 1).fill(0));

  skill.forEach(([type, r1, c1, r2, c2, degree]) => {
    if (type === 1) {
      degree *= -1;
    }
    degreeSum[r1][c1] += degree;
    degreeSum[r2 + 1][c1] += -degree;
    degreeSum[r1][c2 + 1] += -degree;
    degreeSum[r2 + 1][c2 + 1] += degree;
  });

  // 행 누적합
  for (let x = 0; x <= N; x++) {
    for (let y = 1; y <= M; y++) {
      degreeSum[x][y] += degreeSum[x][y - 1];
    }
  }

  let answer = 0;

  // 열 누적합
  for (let y = 0; y <= M; y++) {
    for (let x = 1; x <= N; x++) {
      if (degreeSum[x - 1][y] + board[x - 1][y] > 0) {
        answer++;
      }
      degreeSum[x][y] += degreeSum[x - 1][y];
    }
  }

  return answer;
}
