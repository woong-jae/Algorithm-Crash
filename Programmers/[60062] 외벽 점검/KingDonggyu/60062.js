const getPermutation = (arr, count) => {
  if (count === 1) {
    return arr.map((el) => [el]);
  }

  const result = [];

  arr.forEach((fixed, idx, arr) => {
    const sliced = [...arr.slice(0, idx), ...arr.slice(idx + 1)];
    const permu = getPermutation(sliced, count - 1);
    const attached = permu.map((p) => [fixed, ...p]);
    result.push(...attached);
  });

  return result;
};

function solution(n, weak, dist) {
  const circularWeak = [...weak, ...weak.map((x) => x + n)];

  circularWeak.pop();
  dist.sort((a, b) => b - a);

  // 최소 인원부터 최대 인원까지 모든 경우의 수
  for (let count = 1; count <= dist.length; count++) {
    // 해당 인원들로 접근 가능한 모든 경우의 수
    for (const permu of getPermutation(dist, count)) {
      for (let start = 0; start < weak.length; start++) {
        // 첫번째 취약 지점부터 유효한 범위만 채택
        let linearWeak = circularWeak.slice(start, start + weak.length);
        // 점검 수행
        for (const p of permu) {
          const checkRange = linearWeak[0] + p;
          // 점검 완료한 지점은 제외
          linearWeak = linearWeak.filter((w) => w > checkRange);
          // 모든 취약 지점 점검 완료
          if (!linearWeak.length) {
            return count;
          }
        }
      }
    }
  }

  return -1;
}
