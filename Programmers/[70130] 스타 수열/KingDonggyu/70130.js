function solution(a) {
  if (a < 2 || a % 2) {
    return 0;
  }

  const countMap = a.reduce((acc, cur) => {
    if (!acc.has(cur)) {
      acc.set(cur, 0);
    }
    acc.set(cur, acc.get(cur) + 1);
    return acc;
  }, new Map());

  const sorted = Array.from(countMap.entries()).sort((a, b) => b[1] - a[1]);

  let answer = 0;

  for (const [num, count] of sorted) {
    if (count <= answer) {
      continue;
    }

    let star = 0;

    for (let i = 0; i < a.length; i++) {
      if (i === a.length - 1) {
        continue;
      }

      if (a[i] === a[i + 1]) {
        continue;
      }

      if (a[i] !== num && a[i + 1] !== num) {
        continue;
      }

      star += 1;
      i += 1;
    }

    answer = Math.max(answer, star);
  }

  return answer * 2;
}
