function dequeue(queue) {
  const [node, value, idx] = queue.reduce(
    (acc, [node, value], i) => {
      return acc[1] > value ? [node, value, i] : acc;
    },
    [null, Infinity, 0]
  );
  queue.splice(idx, 1);
  return [node, value];
}

function dijkstra(N, graph, start) {
  const queue = [[start, 0]];
  const minTime = Array(N + 1).fill(Infinity);
  minTime[start] = 0;
  while (queue.length) {
    const [node, time] = dequeue(queue);
    graph[node].forEach(([nextNode, newTime]) => {
      if (minTime[nextNode] <= time + newTime) {
        return;
      }
      minTime[nextNode] = time + newTime;
      queue.push([nextNode, minTime[nextNode]]);
    });
  }
  return minTime;
}

function solution(N, X, graph) {
  const reversedGraph = Array.from(Array(N + 1), () => []);
  graph.forEach((edges, src) => {
    edges.forEach(([dst, time]) => {
      reversedGraph[dst].push([src, time]);
    })
  })
  const goTimes = dijkstra(N, reversedGraph, X);
  const backTimes = dijkstra(N, graph, X);
  let answer = 0;
  for (let i = 1; i < N + 1; i++) {
    answer = Math.max(answer, goTimes[i] + backTimes[i]);
  }
  return answer;
}

const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const input = [];
let N, M, X;

rl.on('line', (line) => {
  input.push(line);
  if (input.length === 1) {
    [N, M, X] = input[0].split(' ').map((c) => +c);
    return;
  }
  if (input.length === M + 1) {
    rl.close();
  }
}).on('close', () => {
  const graph = Array.from(Array(N + 1), () => []);
  input.slice(1).forEach((s) => {
    const [src, dst, time] = s.split(' ');
    graph[+src].push([+dst, +time]);
  });
  console.log(solution(N, X, graph));
  process.exit();
});
