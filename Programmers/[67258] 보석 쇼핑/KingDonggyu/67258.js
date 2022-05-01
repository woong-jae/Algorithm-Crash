function solution(gems) {
  const map = new Map();
  const count = new Set(gems).size;

  let answer = [1, gems.length + 1];
  let left = 0;
  let right = -1;

  while (right < gems.length) {
    if (map.size === count) {
      if (answer[1] - answer[0] > right - left) {
        answer = [left + 1, right + 1];
      }
      map.set(gems[left], map.get(gems[left]) - 1);
      if (map.get(gems[left]) === 0) {
        map.delete(gems[left]);
      }
      left++;
    } else {
      right++;
      if (map.get(gems[right])) {
        map.set(gems[right], map.get(gems[right]) + 1);
      } else {
        map.set(gems[right], 1);
      }
    }
  }

  return answer;
}
