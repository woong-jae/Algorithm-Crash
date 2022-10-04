const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

const input = [];
rl.on('line', function (line) {
  input.push(line.split(" "));
}).on("close", function () {
  console.log(solution(+input[0][0], input.slice(1).map(row => row.slice(0, -1).map(e => +e))));
  process.exit();
});

function solution(N, edges) {
  const nodes = Array.from(Array(N), () => []);
  edges.forEach(([nodeIndex, ...edge]) => {
    nodeIndex = +nodeIndex;
    for (let i = 0; i < edge.length; i += 2) {
      const to = edge[i] - 1, weight = edge[i + 1];
      nodes[nodeIndex - 1].push([to, weight]);
    }
  });

  const getMaxDistanceFrom = (start) => {
    const visited = Array(N).fill(false);
    visited[start] = true;

    let result = [0, -1];
    const q = [[start, 0]];
    for (const [node, acc] of q) {
      nodes[node].forEach(([next, weight]) => {
        if (visited[next]) return;
        visited[next] = true;
        q.push([next, acc + weight]);

        if (result[0] < acc + weight) {
          result = [acc + weight, next];
        }
      });
    }
    return result;
  }

  const [_, farthestNode] = getMaxDistanceFrom(0);
  const result = getMaxDistanceFrom(farthestNode);
  return result[0];
}