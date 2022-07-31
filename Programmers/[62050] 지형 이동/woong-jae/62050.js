function solution(land, height) {
  const N = land.length;
  const dr = [-1, 0, 1, 0], dc = [0, 1, 0, -1];
  
  class UnionFind {
      constructor(n) {
          this.root = Array(n).fill(0).map((_, i) => i);
      }
      find(u) {
          if(u === this.root[u]) return u;
          return this.root[u] = this.find(this.root[u]);
      }
      union(u, v) {
          u = this.find(u);
          v = this.find(v);
          if(u === v) return;
          
          this.root[v] = u;
      }
  }
  
  const isValid = (r, c) => {
      if(0 <= r && r < N && 0 <= c && c < N) return true;
      return false;
  }
  
  const divide_land = () => {
      const divided_land = Array.from(Array(N), () => Array(N).fill(null));
      
      let count = 0;
      for(let r = 0; r < N; r++) {
          for(let c = 0; c < N; c++) {
              if(divided_land[r][c]) continue;
              divided_land[r][c] = ++count;
              
              const q = [[r, c]];
              while(q.length) {
                  const cur = q.shift();
                  for(let dir = 0; dir < 4; dir++) {
                      const nr = cur[0] + dr[dir], nc = cur[1] + dc[dir];
                      if(
                          !isValid(nr, nc) 
                          || divided_land[nr][nc]
                          || Math.abs(land[cur[0]][cur[1]] - land[nr][nc]) > height
                      ) continue;
                      divided_land[nr][nc] = count;
                      q.push([nr, nc]);
                  }
              }
          }
      }
      
      return [divided_land, count];
  }
  
  const get_edges = (divided_land) => {
      const edges = [];
      
      for(let r = 0; r < N; r++) {
          for(let c = 0; c < N; c++) {
              for(let dir = 0; dir < 4; dir++) {
                  const nr = r + dr[dir], nc = c + dc[dir];
                  if(
                      !isValid(nr, nc)
                      || divided_land[nr][nc] === divided_land[r][c]
                  ) continue;
                  
                  const diff = Math.abs(land[r][c] - land[nr][nc]);
                  edges.push([divided_land[nr][nc], divided_land[r][c], diff]);
              }
          }
      }
      
      return edges;
  }
  
  const [devided_land, count] = divide_land(land);
  const edges = get_edges(devided_land);
  
  edges.sort((a, b) => a[2] - b[2]);
  const disjoint_set = new UnionFind(count + 1);
  return edges.reduce((prev, cur) => {
      const [from, to, weight] = cur;
      if(disjoint_set.find(from) === disjoint_set.find(to)) return prev;
      disjoint_set.union(to, from);
      return prev + weight;
  }, 0);
}