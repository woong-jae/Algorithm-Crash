class DisjointSet {
  constructor(size, priority) {
    this.root = new Array(size);
    this.priority = [...priority];
    for (let i = 0; i < size; i++) {
      this.root[i + 1] = i + 1;
    }
  }

  find(x) {
    if (this.root[x] === x) {
      return x;
    }
    return (this.root[x] = this.find(this.root[x]));
  }

  union(x, y) {
    let rootX = this.find(x);
    let rootY = this.find(y);

    if (!this.priority.includes(rootX) && this.priority.includes(rootY)) {
      [rootX, rootY] = [rootY, rootX];
    }

    if (rootX !== rootY) {
      this.root[rootY] = rootX;
      return true;
    }
    return false;
  }
}

function solution(N, M, truthInfo, parties) {
  if (truthInfo.length === 1) {
    return parties.length;
  }

  const truths = truthInfo.slice(1).map((x) => +x);
  const disjointSet = new DisjointSet(+N, truths);

  parties = parties.map((party) => {
    party = party.split(' ').map((x) => +x);
    if (party[0] < 2) {
      return party;
    }
    for (let i = 1; i < party.length - 1; i++) {
      disjointSet.union(party[i], party[i + 1]);
    }
    return party;
  });

  let answer = 0;
  parties.forEach((party) => {
    party.slice(1).filter((number) => {
      return !truths.includes(disjointSet.find(number));
    }).length && answer++;
  });

  return answer;
}

const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const input = [];

rl.on('line', (line) => {
  input.push(line);
}).on('close', () => {
  const [N, M] = input[0].split(' ');
  const truthInfo = input[1].split(' ');
  const parties = input.slice(2);
  console.log(solution(N, M, truthInfo, parties));
  process.exit();
});
