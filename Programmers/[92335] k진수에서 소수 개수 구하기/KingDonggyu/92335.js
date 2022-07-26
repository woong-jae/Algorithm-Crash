function solution(n, k) {
  const isPrimeNumber = (num) => {
    if (num === 1) {
      return false;
    }
    for (let i = 2; i * i <= num; i++) {
      if (num % i === 0) {
        return false;
      }
    }
    return true;
  };

  let answer = 0;

  n.toString(k).split('0').forEach((num) => {
    if (num) {
      isPrimeNumber(+num) && answer++;
    }
  });

  return answer;
}
