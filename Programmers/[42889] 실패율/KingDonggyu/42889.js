function solution(N, stages) {
  const challengeCnt = new Array(N).fill(0);

  stages.forEach((stage) => {
    if (stage > N) return;
    challengeCnt[stage - 1]++;
  });

  const stageInfo = [];
  let userCnt = stages.length;

  challengeCnt.forEach((cnt, i) => {
    stageInfo.push({
      stage: i + 1,
      failureRate: cnt / userCnt,
    });
    userCnt -= cnt;
  });

  stageInfo.sort((a, b) => {
    return b.failureRate - a.failureRate;
  });

  return stageInfo.map((obj) => obj.stage);
}
