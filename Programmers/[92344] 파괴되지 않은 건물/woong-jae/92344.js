function solution(board, skill) {
  const N = board.length, M = board[0].length;
  const prefixSum = Array(N * M).fill(0);
  
  const mark = (r1, c1, r2, c2, degree) => {
      prefixSum[r1 * M + c1] += degree;
      if(c2 + 1 < M) prefixSum[r1 * M + c2 + 1] += -1 * degree;
      if(r2 + 1 < N) prefixSum[(r2 + 1) * M + c1] += -1 * degree;
      if(c2 + 1 < M && r2 + 1 < N) prefixSum[(r2 + 1) * M + c2 + 1] += degree;
  }
  
  skill.forEach(([type, r1, c1, r2, c2, degree]) => {
      mark(r1, c1, r2, c2, degree * (type === 1 ? -1 : 1));
  });
  
  for(let r = 0; r < N; r++) {
      for(let c = 1; c < M; c++) {
          prefixSum[r * M + c] += prefixSum[r * M + c - 1];
      }
  }
  
  for(let c = 0; c < M; c++) {
      for(let r = 1; r < N; r++) {
          prefixSum[r * M + c] += prefixSum[(r - 1) * M + c];
      }
  }
  
  return board.flat().reduce((prev, cur, idx) => prev + (cur + prefixSum[idx] > 0 ? 1 : 0), 0);
}