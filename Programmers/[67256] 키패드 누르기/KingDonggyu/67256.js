function solution(numbers, hand) {
  const abs = Math.abs;
  const row = [null, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3];
  const col = [null, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2];

  let answer = '';
  let left = 10;  // *
  let right = 12; // #

  numbers.forEach((num) => {
    if (num === 0) num = 11;

    let nextHand = 'L';
    // 오른손을 사용하는 경우만 확인
    if (col[num] === 1) { 
      const leftDist = abs(row[num] - row[left]) + abs(col[num] - col[left]);
      const rightDist = abs(row[num] - row[right]) + abs(col[num] - col[right]);

      if (leftDist > rightDist || 
        (leftDist === rightDist && hand === 'right')) {
          nextHand = 'R';
      }
    } else if (col[num] === 2) { 
      nextHand = 'R';
    }

    if (nextHand === 'L') left = num;
    else right = num;
    answer += nextHand;
  });

  return answer;
}
