function solution(alp, cop, problems) {
  let maxAlp = alp;
  let maxCop = cop;

  for (let i = 0; i < problems.length; i++) {
    if (maxAlp < problems[i][0]) {
      maxAlp = problems[i][0];
    }

    if (maxCop < problems[i][1]) {
      maxCop = problems[i][1];
    }
  }

  const dp = Array.from(Array(maxAlp + 1), () =>
    Array(maxCop + 1).fill(Infinity)
  );

  dp[alp][cop] = 0;

  for (let x = alp; x <= maxAlp; x++) {
    for (let y = cop; y <= maxCop; y++) {
      if (x < maxAlp) {
        dp[x + 1][y] =
          dp[x][y] + 1 < dp[x + 1][y] ? dp[x][y] + 1 : dp[x + 1][y];
      }

      if (y < maxCop) {
        dp[x][y + 1] =
          dp[x][y] + 1 < dp[x][y + 1] ? dp[x][y] + 1 : dp[x][y + 1];
      }

      for (const [alpReq, copReq, alpRwd, copRwd, cost] of problems) {
        if (x < alpReq || y < copReq) {
          continue;
        }

        let anotherX = x + alpRwd;
        let anotherY = y + copRwd;

        if (anotherX > maxAlp) {
          anotherX = maxAlp;
        }

        if (anotherY > maxCop) {
          anotherY = maxCop;
        }

        dp[anotherX][anotherY] =
          dp[anotherX][anotherY] < dp[x][y] + cost
            ? dp[anotherX][anotherY]
            : dp[x][y] + cost;
      }
    }
  }

  return dp[maxAlp][maxCop];
}
