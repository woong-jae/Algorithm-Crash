function solution(N, road, K) {
  const graph = Array.from(Array(N + 1), () => []);

  road.forEach(([village1, village2, time]) => {
    graph[village1].push([village2, time]);
    graph[village2].push([village1, time]);
  });

  const stack = [[1, 0]];
  const minTime = Array(N + 1).fill(Infinity);
  minTime[1] = 0;

  while (stack.length) {
    const [u, time] = stack.pop();

    graph[u].forEach(([v, t]) => {
      if (minTime[v] < time + t) {
        return;
      }
      stack.push([v, time + t]);
      minTime[v] = time + t;
    });
  }

  return minTime.filter((t) => t <= K).length;
}
