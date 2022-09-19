function solution(A, B) {
  A.sort((a, b) => a - b);
  B.sort((a, b) => b - a);

  return B.reduce((acc, cur) => {
    while (A.length) {
      if (cur > A.pop()) {
        return acc + 1;
      }
    }
    return acc;
  }, 0);
}
