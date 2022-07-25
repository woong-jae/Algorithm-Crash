function solution(lottos, win_nums) {
  const getRank = (cnt) => {
    return cnt < 2 ? 6 : 7 - cnt;
  };

  win_nums = new Set(win_nums);

  let sameCnt = 0;
  let zeroCnt = 0;

  lottos.forEach((lotto) => {
    win_nums.has(lotto) && sameCnt++;
    !lotto && zeroCnt++;
  });

  const higestRank = getRank(sameCnt + zeroCnt);
  const lowestRank = getRank(sameCnt);

  return [higestRank, lowestRank];
}
