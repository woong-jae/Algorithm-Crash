function solution(k, tangerine) {
  const type = new Map();

  tangerine.forEach((t) => {
    if (type.has(t)) {
      type.set(t, type.get(t) + 1);
      return;
    }
    type.set(t, 1);
  });

  const sortedTangerines = Array.from(type.entries()).sort(
    (a, b) => b[1] - a[1]
  );

  if (sortedTangerines[0][1] >= k) {
    return 1;
  }

  let answer = 0;
  let count = 0;

  for (let i = 0; i < type.size; i++) {
    count += sortedTangerines[i][1];
    answer += 1;

    if (count >= k) {
      break;
    }
  }

  return answer;
}
