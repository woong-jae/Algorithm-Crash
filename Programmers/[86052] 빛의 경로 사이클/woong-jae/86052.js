function solution(grid) {
  grid = grid.map(row => row.split("").map(col => [col, Array(4).fill(false)]));
  const dr = [-1, 0, 1, 0], dc = [0, 1, 0, -1];
  const R = grid.length, C = grid[0].length;
  
  const getNext = (r, c, dir) => {
      const [state] = grid[r][c];
      
      let ndir;
      if(state === "S") {
          ndir = dir;
      }
      else if(state === "L") {
          ndir = ((dir + 4) - 1) % 4;
      }
      else {
          ndir = (dir + 1) % 4;
      }
      
      let nr = r + dr[ndir], nc = c + dc[ndir];
      if(nr < 0 || nr >= R) nr = (nr + R) % R;
      if(nc < 0 || nc >= C) nc = (nc + C) % C;
      
      return [nr, nc, ndir];
  }
  
  const calcPath = (r, c, dir) => {
      let result = 1;
      
      let next = getNext(r, c, dir);
      while(1) {
          const [nr, nc, ndir] = next;
          const nextVisited = grid[nr][nc][1];
          if(nextVisited[ndir]) break;
          
          nextVisited[ndir] = true;
          next = getNext(nr, nc, ndir);
          result++;
      }
      
      return result;
  }
  
  const paths = [];
  for(let r = 0; r < R; r++) {
      for(let c = 0; c < C; c++) {
          const [visited] = grid[r][c];
          
          for(let dir = 0; dir < 4; dir++) {
              if(visited[dir]) continue;
              
              visited[dir] = true;
              paths.push(calcPath(r, c, dir));
          }
      }
  }
  return paths.sort((a, b) => a - b);
}