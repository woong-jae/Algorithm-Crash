function factorial(number) {
  if (number === 1) {
    return 1;
  }

  const result = number * factorial(number - 1);
  return result;
}

function solution(n, k) {
  const answer = [];
  let caseCount = factorial(n);
  let people = Array(n)
    .fill(0)
    .map((_, i) => i + 1);

  while (answer.length < n) {
    caseCount = Math.floor(caseCount / people.length);

    const index = Math.floor((k - 1) / caseCount);
    const selectedPerson = people.splice(index, 1)[0];

    answer.push(selectedPerson);
    k = k % caseCount;
  }

  return answer;
}
