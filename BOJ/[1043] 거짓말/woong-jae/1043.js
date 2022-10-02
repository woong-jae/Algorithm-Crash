const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

const input = [];
rl.on('line', function (line) {
  input.push(line.split(" "));
}).on("close", function () {
  console.log(solution(...input[0].map(e => +e), input[1].slice(1).map(e => +e), input.slice(2).map(row => row.slice(1).map(e => +e))));
  process.exit();
});

function solution(N, M, knowTruth, attends) {
  if (knowTruth.length === 0) return M;

  class DisjointSet {
    constructor(n) {
      this.root = Array.from(Array(n), (_, i) => i);
    }
    find(u) {
      if (u === this.root[u]) return u;
      return this.root[u] = this.find(this.root[u]);
    }
    union(u, v) {
      u = this.find(u);
      v = this.find(v);
      if (u === v) return;
      this.root[v] = u;
    }
  }

  const personToParty = Array.from(Array(N + 1), () => []);
  attends.forEach((people, partyIndex) => {
    people.forEach(person => personToParty[person].push(partyIndex));
  });

  const partyDisjointSet = new DisjointSet(M);
  personToParty.forEach(parties => {
    for (let from = 0; from < parties.length; from++) {
      for (let to = from + 1; to < parties.length; to++) {
        partyDisjointSet.union(parties[from], parties[to]);
      }
    }
  });

  const knowTruthParty = new Set();
  knowTruth.forEach(person => {
    personToParty[person].forEach(partyIndex => knowTruthParty.add(partyDisjointSet.find(partyIndex)));
  });

  let result = 0;
  for (let i = 0; i < M; i++) {
    if (knowTruthParty.has(partyDisjointSet.find(i))) continue;
    result++;
  }
  return result;
}