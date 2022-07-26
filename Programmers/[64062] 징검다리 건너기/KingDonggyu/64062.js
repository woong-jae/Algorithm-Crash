function solution(stones, k) {
  let left = 1;
  let right = 200000000;

  while (left + 1 < right) {
    const mid = Math.floor((left + right) / 2);
    let unableCount = 0;

    for (const stone of stones) {
      if (stone > mid) {
        unableCount = 0;
        continue;
      }

      if (++unableCount === k) {
        break;
      }
    }

    if (unableCount === k) {
      right = mid;
    } else {
      left = mid;
    }
  }

  return right;
}