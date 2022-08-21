function solution([K, N, F], ...friend) {
  const graph = Array.from(Array(N + 1), () => []);

  friend.forEach(([f1, f2]) => {
    graph[f1].push(f2);
    graph[f2].push(f1);
  });

  graph.forEach((arr) => {
    arr = arr.sort((a, b) => a - b);
  });

  const dfs = (num, selected, visited) => {
    if (selected.length === K) {
      console.log(selected.join('\n'));
      process.exit();
    }

    for (const v of graph[num]) {
      if (visited[v]) {
        continue;
      }

      if (selected.every((s) => graph[v].includes(s))) {
        visited[v] = true;
        dfs(v, [...selected, v], visited);
      }
    }
  };

  for (let i = 1; i < N + 1; i++) {
    if (graph[i].length >= K - 1) {
      const visited = Array(N + 1).fill(false);
      visited[i] = true;
      dfs(i, [i], visited);
    }
  }

  console.log(-1);
}

const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const input = [];

rl.on('line', (line) => {
  input.push(line.split(' ').map((x) => +x));
  input.length === +input[0][2] + 1 && rl.close();
}).on('close', () => {
  solution(...input);
});
