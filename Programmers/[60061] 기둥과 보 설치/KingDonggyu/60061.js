function solution(n, build_frame) {
  const wall = Array(n + 1).fill(0)
    .map((_) => Array(n + 1).fill(0)
    .map((_) => [false, false]));

  const checkPillar = (x, y) => {
    // 바닥 위
    if (!y) {
      return true;
    }
    // 기둥 위
    if (y > 0 && wall[x][y - 1][0]) {
      return true;
    }
    // 보 위
    if (
      wall[x][y][1] || 
      (x > 0 && wall[x - 1][y][1])
    ) {
      return true;
    }
  };

  const checkBeam = (x, y) => {
    // 기둥 위
    if (
      (y > 0 && wall[x][y - 1][0]) || 
      (x < n && wall[x + 1][y - 1][0])
    ) {
      return true;
    }
    // 보 사이
    if (
      x > 0 && wall[x - 1][y][1] && 
      x < n && wall[x + 1][y][1]
    ) {
      return true;
    }
  };

  const checkDelete = () => {
    for (let x = 0; x < n + 1; x++) {
      for (let y = 0; y < n + 1; y++) {
        if (wall[x][y][0] && !checkPillar(x, y)) {
          return false;
        }
        if (wall[x][y][1] && !checkBeam(x, y)) {
          return false;
        }
      }
    }
    return true;
  };

  build_frame.forEach(([x, y, a, b]) => {
    if (!b) {
      wall[x][y][a] = false;
      if (!checkDelete()) {
        wall[x][y][a] = true;
      }
      return;
    }

    const install = a ? checkBeam(x, y) : checkPillar(x, y);

    if (install) {
      wall[x][y][a] = true;
    }
  });

  const answer = [];

  for (let x = 0; x < n + 1; x++) {
    for (let y = 0; y < n + 1; y++) {
      wall[x][y][0] && answer.push([x, y, 0]);
      wall[x][y][1] && answer.push([x, y, 1]);
    }
  }

  return answer;
}
