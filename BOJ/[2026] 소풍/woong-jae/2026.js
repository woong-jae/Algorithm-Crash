const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

const input = [];
rl.on('line', function (line) {
  input.push(line.split(' '));
}).on("close", function () {
  console.log(solution());
  process.exit();
});

function solution() {
  const [K, N] = input[0].map(e => +e);
  const friends = input.slice(1);

  const adjGraph = Array.from(Array(N + 1), () => Array(N + 1).fill(false));
  friends.forEach(([from, to]) => {
    adjGraph[+from][+to] = true;
    adjGraph[+to][+from] = true;
  });

  const dfs = (start) => {
    let finished = false;
    let answer = -1;
    const visited = new Set([start]);

    const helper = (cur, picked) => {
      if (finished) return;
      if (picked.length === K) {
        answer = [...picked];
        finished = true;
        return;
      };

      for (let next = cur + 1; next <= N; next++) {
        if (visited.has(next)) continue;

        const isFriend = picked.reduce((acc, pick) => acc && adjGraph[pick][next], true);
        if (isFriend) {
          visited.add(next);
          picked.push(next);
          helper(next, picked);
          picked.pop();
        }
      }
    }

    helper(start, [start]);

    return answer;
  }

  for (let start = 1; start <= N; start++) {
    answer = dfs(start);
    if (answer !== -1) return answer.join("\n");
  }

  return -1;
}