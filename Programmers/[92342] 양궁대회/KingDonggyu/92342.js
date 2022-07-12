function solution(n, info) {
  // 중복 조합
  const getRepeatCombination = (arr, count) => {
    if (count === 1) {
      return arr.map((el) => [el]);
    }

    const result = [];

    arr.forEach((fixed, i, arr) => {
      const sliced = arr.slice(i);
      const repeatCombi = getRepeatCombination(sliced, count - 1);
      const attached = repeatCombi.map((c) => [fixed, ...c]);
      result.push(...attached);
    });

    return result;
  };

  const scores = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0];
  const repeatCombi = getRepeatCombination(scores, n);
  const apeachTotal = info.reduce((score, cnt, i) => {
    return cnt ? score + (10 - i) : score + 0;
  }, 0);

  const maxScoreDiff = {
    scoreDiff: 0,
    hitResult: [-1],
  };

  repeatCombi.forEach((hits) => {
    const hitResult = Array(11).fill(0);

    hits.forEach((score) => {
      hitResult[score]++;
    });

    let apeach = apeachTotal;
    let lion = 0;

    hitResult.forEach((cnt, i) => {
      if (info[i]) {
        if (info[i] < cnt) {
          apeach -= 10 - i;
          lion += 10 - i;
        }
        return;
      }
      if (cnt) {
        lion += 10 - i;
      }
    });

    const scoreDiff = lion - apeach;

    if (scoreDiff > maxScoreDiff.scoreDiff) {
      maxScoreDiff.scoreDiff = scoreDiff;
      maxScoreDiff.hitResult = hitResult;
    }
  });

  return maxScoreDiff.hitResult;
}
