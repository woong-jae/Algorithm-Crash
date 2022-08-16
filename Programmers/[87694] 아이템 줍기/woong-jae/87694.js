function solution(rectangle, characterX, characterY, itemX, itemY) {
  const MAX = 51 * 2;
  const d = [[1, 0, -1, 0], [0, 1, 0, -1]];
  
  const createMatrix = (n) => {
      return Array.from(Array(n + 1), () => Array(n + 1).fill(0));
  }
  
  const drawRectangles = (matrix, rectangles) => {
      rectangles.forEach(rectangle => {
          const [x1, y1, x2, y2] = rectangle.map(v => v * 2);
          
          let cur = [y1, x1];
          for(let dir = 0; dir < 4; dir++) {
              while(1) {
                  const [nr, nc] = cur.map((v, i) => v + d[i][dir]);
                  if(y1 > nr || nr > y2 || x1 > nc || nc > x2) break;
                  
                  matrix[nr][nc] = 1;
                  cur = [nr, nc];
              }
          }
      });
      return matrix;
  }
  
  const isInside = (r, c) => {
      for(let rect of rectangle) {
          const [x1, y1, x2, y2] = rect.map(v => v * 2);
          if(y1 < r && r < y2 && x1 < c && c < x2) return true;
      }
      return false;
  }
  
  const ground = drawRectangles(createMatrix(MAX), rectangle);
  const distance = createMatrix(MAX);
  
  const q = [[characterY * 2, characterX * 2]];
  while(q.length > 0) {
      const cur = q.shift();
      const nextDist = distance[cur[0]][cur[1]] + 1;

      for(let dir = 0; dir < 4; dir++) {
          const [nr, nc] = cur.map((v, i) => v + d[i][dir]);
          if(ground[nr][nc] === 0 || distance[nr][nc] !== 0) continue;
          if(isInside(nr, nc)) continue;
          
          distance[nr][nc] = nextDist;
          q.push([nr, nc]);
      }
  }
  
  return distance[itemY * 2][itemX * 2] / 2;
}