function solution(V, edges) {
  const graph = Array.from(Array(V + 1), () => []);
  edges.forEach((edge) => {
    edge = edge.split(' ').map((x) => +x);
    for (let i = 1; i < edge.length - 1; i += 2) {
      graph[edge[0]].push([edge[i], edge[i + 1]]);
    }
  });

  const [node] = dfs(graph, 1);
  const [_, cost] = dfs(graph, node);
  return cost;
}

function dfs(graph, startNode) {
  let farthestNode = [null, 0];
  const stack = [[startNode, 0, new Set([startNode])]];

  while (stack.length) {
    const [node, cost, visited] = stack.pop();
    if (farthestNode[1] < cost) {
      farthestNode = [node, cost];
    }
    graph[node].forEach(([nextNode, newCost]) => {
      if (visited.has(nextNode)) {
        return;
      }
      const nextVisited = new Set([...Array.from(visited)]);
      nextVisited.add(nextNode);
      stack.push([nextNode, cost + newCost, nextVisited]);
    })
  }

  return farthestNode;
}

const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const input = [];

rl.on('line', (line) => {
  input.push(line);
  input.length - 1 === +input[0] && rl.close();
}).on('close', () => {
  console.log(solution(+input[0], input.slice(1)));
  process.exit();
});
