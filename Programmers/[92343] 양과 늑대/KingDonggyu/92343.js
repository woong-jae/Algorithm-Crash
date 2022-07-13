function solution(info, edges) {
  const tree = Array.from(Array(info.length), () => []);

  edges.forEach(([a, b]) => {
    tree[a].push(b);
    tree[b].push(a);
  });

  const getState = (num, sheep, wolf, visited) => {
    return [num, sheep, wolf].join('') + visited.join('');
  };

  const visited = Array(info.length).fill(0);
  visited[0] = 1;

  // [노드 번호, 양의 수, 늑대 수, 노드 방문 여부] Stack
  const stack = [[0, 1, 0, visited]];

  // '노드 번호 + 양의 수 + 늑대 수 + 방문 여부 문자열' Set
  const states = new Set([getState(0, 1, 0, visited)]);

  let answer = 0;

  while (stack.length) {
    const [num, sheep, wolf, visited] = stack.pop();

    answer = Math.max(answer, sheep);

    tree[num].forEach((child) => {
      let nextSheep = sheep;
      let nextWolf = wolf;

      if (!visited[child]) {
        info[child] ? nextWolf++ : nextSheep++;
      }

      const newVisited = [...visited];
      newVisited[child] = 1;

      const state = getState(child, nextSheep, nextWolf, newVisited);

      if (nextSheep <= nextWolf || states.has(state)) {
        return;
      }

      stack.push([child, nextSheep, nextWolf, newVisited]);
      states.add(state);
    });
  }

  return answer;
}
