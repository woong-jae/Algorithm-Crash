function solution(N, road, K) {
  const adjList = Array.from(Array(N + 1), () => []);
  road.forEach(([a, b, c]) => {
      adjList[a].push([b, c]);
      adjList[b].push([a, c]);
  });
  
  const dist = Array(N + 1).fill(Infinity);
  dist[1] = 0;
  
  const q = [[1, 0]];
  while(q.length) {
      const [cur, accDist] = q.shift();
      if(accDist > dist) continue;
      
      adjList[cur].forEach(([next, w]) => {
          if(accDist + w >= dist[next]) return;
          dist[next] = accDist + w;
          q.push([next, accDist + w]);
      });
  }
  
  return dist.reduce((acc, cur) => acc += (cur <= K ? 1 : 0), 0);
}