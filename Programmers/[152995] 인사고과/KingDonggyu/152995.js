function solution(scores) {
  let answer = 1;
  let maxScoreB = 0;
  const myScore = scores[0];

  scores.sort((a, b) => (a[0] === b[0] ? a[1] - b[1] : b[0] - a[0]));

  for (let score of scores) {
    if (score[1] < maxScoreB) {
      if (myScore === score) {
        return -1;
      }
      continue;
    }

    if (myScore[0] + myScore[1] < score[0] + score[1]) {
      answer += 1;
    }

    maxScoreB = score[1];
  }

  return answer;
}
