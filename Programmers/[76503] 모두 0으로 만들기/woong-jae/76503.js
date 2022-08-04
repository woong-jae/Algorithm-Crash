function solution(a, edges) {
  if(a.reduce((prev, cur) => prev + cur, 0) !== 0) return -1;
  
  const adj_list = Array.from(Array(a.length), () => []);
  edges.forEach(([a, b]) => {
      adj_list[a].push(b);
      adj_list[b].push(a);
  });
  
  const visited = Array(a.length).fill(false);
  const weight_moves = a.map(w => [w, 0]);
  const children = Array.from(Array(a.length), () => []);
  
  const stack = [[0, 0]];
  visited[0] = true;
  while(stack.length) {
      const [cur, index] = stack.pop();
      if(adj_list[cur].length === 0) continue;
      
      let flag = false;
      for(let nextIndex = index; nextIndex < adj_list[cur].length; nextIndex++) {
          const next = adj_list[cur][nextIndex];
          if(visited[next]) continue;
          visited[next] = true;
          stack.push([cur, nextIndex]);
          stack.push([next, 0]);
          children[cur].push(next);
          
          flag = true;
          break;
      }
      if(flag) continue;
      
      children[cur].forEach(child => {
          weight_moves[cur][0] += weight_moves[child][0];
          weight_moves[cur][1] += weight_moves[child][1] + Math.abs(weight_moves[child][0]);
      });
  }
  
  return weight_moves[0][1];
}