function solution(s) {
  if (s.length === 1) {
    return 1;
  }

  const getCompressed = (unit) => {
    let compressed = '';
    let prev = s.slice(0, unit);
    let repeatCount = 1;

    for (let i = 1; i <= Math.ceil(s.length / unit); i++)  {
      const sliced = s.slice(i * unit, (i + 1) * unit);

      if (prev === sliced) {
        repeatCount++;
        continue;
      }
      
      if (repeatCount > 1) {
        compressed += repeatCount.toString();
      }

      compressed += prev;
      prev = sliced;
      repeatCount = 1;
    }

    return compressed;
  };

  let answer = Infinity;

  for (let i = 1; i <= Math.floor(s.length / 2); i++) {
    const compressed = getCompressed(i);
    answer = Math.min(answer, compressed.length);
  }

  return answer;
}
