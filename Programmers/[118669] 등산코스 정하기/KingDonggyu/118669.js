function solution(n, paths, gates, summits) {
  const graph = Array.from(Array(n + 1), () => []);
  const isSummit = Array(n + 1).fill(false);

  paths.forEach(([a, b, time]) => {
    graph[a].push([b, time]);
    graph[b].push([a, time]);
  });

  summits.forEach((summit) => {
    isSummit[summit] = true;
  });

  const queue = [];
  const visited = Array(n + 1).fill(Infinity);

  gates.forEach((gate) => {
    queue.push(gate);
    visited[gate] = 0;
  });

  while (queue.length) {
    const now = queue.shift();

    if (isSummit[now]) {
      continue;
    }

    graph[now].forEach(([next, time]) => {
      const intensity = Math.max(time, visited[now]);

      if (intensity < visited[next]) {
        visited[next] = intensity;
        queue.push(next);
      }
    });
  }

  let answer = [0, Infinity];

  for (let i = 1; i < n + 1; i++) {
    if (!isSummit[i]) {
      continue;
    }
    if (answer[1] > visited[i]) {
      answer = [i, visited[i]];
    }
  }

  return answer;
}
