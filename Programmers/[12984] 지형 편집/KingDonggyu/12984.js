function solution(land, P, Q) {
  land = land.flat();

  const getCost = (height) => {
    return land.reduce((acc, cur) => {
      [0, 1].forEach((h, i) => {
        acc[i] += cur < height + h
          ? ((height + h) - cur) * P
          : (cur - (height + h)) * Q;
      })
      return acc;
    }, [0, 0]);
  };

  let answer;
  let left = Math.min(...land);
  let right = Math.max(...land);

  while (left <= right) {
    const mid = Math.floor((left + right) / 2);
    const [cost1, cost2] = getCost(mid);

    if (cost1 < cost2) {
      right = mid - 1;
      answer = cost1;
      continue;
    }

    left = mid + 1;
    answer = cost2;

    if (cost1 === cost2) {
      break;
    }
  }

  return answer;
}

