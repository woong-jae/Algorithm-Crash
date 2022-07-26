function solution(info, edges) {
  const adj_list = Array.from(Array(info.length), () => []);
  edges.forEach(([from, to]) => {
    adj_list[from].push(to);
    adj_list[to].push(from);
  });

  const max_counts = Array.from(Array(info.length), () => [0, 0]);

  const getNextCounts = (next, sheep, wolf, visited) => {
    const next_counts = [sheep, wolf];
    if ((visited & (1 << next)) === 0) next_counts[info[next]]++;
    return next_counts;
  }

  const canGo = (next, counts, visited) => {
    if (counts[0] <= counts[1]) return false;

    if (visited & (1 << next)) {
      if (counts[0] < max_counts[next][0]) return false;
      if (counts[0] === max_counts[next][0] && counts[1] >= max_counts[next][1]) return false;
    }

    return true;
  }

  const q = [[0, 1, 0, 1]];
  while (q.length) {
    const [cur, sheep, wolf, visited] = q.shift();
    if (sheep < max_counts[cur][0]) continue;

    adj_list[cur].forEach(next => {
      const next_counts = getNextCounts(next, sheep, wolf, visited);
      if (!canGo(next, next_counts, visited)) return;

      q.push([next, ...next_counts, visited | (1 << next)]);
      max_counts[next] = next_counts;
    });
  }

  return max_counts.reduce((prev, cur) => Math.max(prev, cur[0]), 1);
}