// 열쇠 회전
function rotateKey(key) {
  const M = key.length;
  const rotated = [];

  for (let y = 0; y < M; y++) {
    const col = [];
    for (let x = M - 1; x >= 0; x--) {
      col.push(key[x][y]);
    }
    rotated.push(col);
  }
  return rotated;
}

// 홈 또는 돌기 패턴 얻기
function getPattern(target, state, start = null) {
  const pattern = {
    start: start,
    direction: []
  };

  target.forEach((row, x) => {
    row.forEach((value, y) => {
      if (value != state) {
        return;
      }
      if (!pattern.start) {
        pattern.start = [x, y];
        return;
      }
      const dx = x - pattern.start[0];
      const dy = y - pattern.start[1];
      if (dx || dy) {
        pattern.direction.push([dx, dy]);
      }
    });
  });

  return pattern;
}

// 자물쇠 풀기
function unlock(key, lock, pattern) {
  const M = key.length;
  const N = lock.length;

  const isIndexOut = (x, y, n) => {
    if (x < 0 || x >= n || y < 0 || y >= n) {
      return true;
    }
    return false;
  }

  // 자물쇠 돌기와 열쇠 돌기가 만나는지 확인
  const checkJutMeet = ({ keyStart, lockStart }) => {
    const [x, y] = lockStart;
    const keyPattern = getPattern(key, true, keyStart);

    for (const [dx, dy] of keyPattern.direction) {
      const [nextX, nextY] = [x + dx, y + dy];
      if (isIndexOut(nextX, nextY, N)) {
        continue;
      }
      if (lock[nextX][nextY]) {
        return false;
      }
    }
    return true;
  }

  // 자물쇠 홈을 모두 채울 수 있는지 확인
  const checkPattern = (x, y) => {
    for (const [dx, dy] of pattern.direction) {
      const [nextX, nextY] = [x + dx, y + dy];
      if (isIndexOut(nextX, nextY, M) || !key[nextX][nextY]) {
        return false;
      }
    }

    if (checkJutMeet({
      keyStart: [x, y],
      lockStart: pattern.start 
    })) {
      return true;
    }
    return false;
  }

  // 해당 열쇠로 자물쇠를 풀 수 있는지 확인
  for (let x = 0; x < M; x++) {
    for (let y = 0; y < M; y++) {
      if (!key[x][y]) {
        continue;
      }

      if (checkPattern(x, y)) {
        return true;
      }
    }
  }
  return false;
}

function solution(key, lock) {
  // 자물쇠 홈 패턴 얻음
  const unlockPattern = getPattern(lock, false);
  let answer = false;

  // 자물쇠 홈이 없음
  if (!unlockPattern.start) {
    return true;
  }

  for (let i = 0; i < 4; i++) {
    if (i > 0) {
      key = rotateKey(key);
    }
    if (unlock(key, lock, unlockPattern)) {
      answer = true;
      break;
    }
  }

  return answer;
}