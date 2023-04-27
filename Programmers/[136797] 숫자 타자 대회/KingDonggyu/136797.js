function calculateTime(hand, target) {
  // 제자리에서 누르기
  if (hand === target) {
    return 1;
  }

  const keyboard = {
    1: [0, 0],
    2: [0, 1],
    3: [0, 2],
    4: [1, 0],
    5: [1, 1],
    6: [1, 2],
    7: [2, 0],
    8: [2, 1],
    9: [2, 2],
    0: [3, 1],
  };

  let x = Math.abs(keyboard[hand][0] - keyboard[target][0]);
  let y = Math.abs(keyboard[hand][1] - keyboard[target][1]);

  let time = 0;

  // 대각선으로 이동하기
  while (x && y) {
    x -= 1;
    y -= 1;
    time += 3;
  }

  // 상하좌우로 이동하기
  time += (x + y) * 2;

  return time;
}

function getTimes() {
  const result = [];

  for (let hand = 0; hand < 10; hand++) {
    const times = [];

    for (let target = 0; target < 10; target++) {
      times.push(calculateTime(hand, target));
    }

    result.push(times);
  }

  return result;
}

function solution(numbers) {
  const times = getTimes();
  const dp = Array.from(Array(numbers.length + 1), () =>
    Array.from(Array(10), () => Array(10).fill(Infinity))
  );

  dp[0][4][6] = 0;

  for (let i = 0; i < numbers.length; i++) {
    const num = numbers[i];

    for (let left = 0; left < 10; left++) {
      for (let right = 0; right < 10; right++) {
        const prevTime = dp[i][left][right];

        if (left === right || prevTime === Infinity) {
          continue;
        }

        if (dp[i + 1][num][right] > prevTime + times[left][num]) {
          dp[i + 1][num][right] = prevTime + times[left][num];
        }

        if (dp[i + 1][left][num] > prevTime + times[right][num]) {
          dp[i + 1][left][num] = prevTime + times[right][num];
        }
      }
    }
  }

  return Math.min(...dp[numbers.length].flat());
}
