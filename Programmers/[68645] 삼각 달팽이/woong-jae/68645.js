function solution(n) {
  const triangle = Array.from(Array(n), (_, i) => Array(i + 1).fill(0));
  let count = 1;
  
  const fillEdge = (r, c) => {
      while(1) {
          triangle[r][c] = count++;
          if(r + 1 >= n || triangle[r + 1][c] !== 0) {
              c += 1;
              break;
          };
          r += 1;
      }
      
      if(triangle[r][c] !== 0) return;
      while(1) {
          triangle[r][c] = count++;
          if(c + 1 >= triangle[r].length || triangle[r][c + 1] !== 0) {
              r -= 1;
              c -= 1;
              break;
          };
          c += 1;
      }
      
      if(triangle[r][c] !== 0) return;
      while(1) {
          triangle[r][c] = count++;
          if(r - 1 < 0 || triangle[r - 1][c - 1] !== 0) break;
          r -= 1;
          c -= 1;
      }
  }
  
  for(let i = 0; i < Math.ceil(n / 3); i++) {
      fillEdge(i * 2, i);
  }
  
  return triangle.flat();
}