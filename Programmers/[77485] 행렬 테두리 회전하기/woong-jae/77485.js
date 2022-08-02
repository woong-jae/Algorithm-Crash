function solution(rows, columns, queries) {
  const matrix = Array(rows).fill(0).map((_, i) => {
      const rowIndex = i * columns;
      return Array(columns).fill(0).map((_, i) => rowIndex + i + 1) ;
  });
  
  const spin = (x1, y1, x2, y2) => {
      let min = matrix[x1][y1];
      
      let prev = min;
      for(let c = y1 + 1; c <= y2; c++) { // 가로
          [prev, matrix[x1][c]] = [matrix[x1][c], prev];
          min = Math.min(min, prev);
      }
      for(let r = x1 + 1; r <= x2; r++) {
          [prev, matrix[r][y2]] = [matrix[r][y2], prev];
          min = Math.min(min, prev);
      }
      for(let c = y2 - 1; c >= y1; c--) { // 가로
          [prev, matrix[x2][c]] = [matrix[x2][c], prev];
          min = Math.min(min, prev);
      }
      for(let r = x2 - 1; r >= x1; r--) {
          [prev, matrix[r][y1]] = [matrix[r][y1], prev];
          min = Math.min(min, prev);
      }
      
      return min;
  }
  
  return queries.map(query => spin(...query.map(elem => elem - 1)));
}