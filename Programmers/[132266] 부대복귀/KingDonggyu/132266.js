function solution(n, roads, sources, destination) {
  const graph = Array.from(Array(n + 1), () => []);

  roads.forEach(([a, b]) => {
    graph[a].push(b);
    graph[b].push(a);
  });

  const queue = [destination];
  const visited = Array(n + 1).fill(Infinity);

  visited[destination] = 0;

  while (queue.length) {
    const started = queue.shift();

    graph[started].forEach((arrived) => {
      const time = visited[started] + 1;

      if (visited[arrived] <= time) {
        return;
      }

      visited[arrived] = time;
      queue.push(arrived);
    });
  }

  return sources.map((s) => (visited[s] === Infinity ? -1 : visited[s]));
}
