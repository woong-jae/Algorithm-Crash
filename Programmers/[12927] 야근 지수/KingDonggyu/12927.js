function solution(n, works) {
  const sorted = works.sort((a, b) => b - a);

  while (n) {
    const max = sorted[0];

    for (let i = 0; i < sorted.length; i++) {
      if (max <= sorted[i]) {
        sorted[i] -= 1;
        n -= 1;
      }
      if (!n) {
        break;
      }
    }
  }

  return sorted.reduce((acc, cur) => {
    if (cur < 0) {
      return acc;
    }
    return acc + cur ** 2;
  }, 0);
}
