function solution(a, b, g, s, w, t) {
  // basic algorithm: Binary Search(이분 탐색)
  // Find the exact time which is between min and max by binary search
  let answer = 10e5 * 4 * 10e9;

  // set start and end for binary search
  let start = 0;
  let end = 10e5 * 4 * 10e9;

  while (start <= end) {
    // set mid value for binary search
    let mid = Math.floor((start + end) / 2);
    let gold = 0;
    let silver = 0;
    let add = 0;

    for (let i = 0; i < s.length; i++) {
      let now_g = g[i];
      let now_s = s[i];
      let now_w = w[i];
      let now_t = t[i];

      let move_cnt = Math.floor(mid / (now_t * 2));
      if (mid % (now_t * 2) >= t[i]) move_cnt++;

      // basic theory:
      // suppose g is min of G and s is min of S
      // suppose G is max of G and S is max of S
      // a <= G and b <= S, a+b <= G + s = g + S
      // because sum of G and S makes a straight line with a slope of -1
      gold += now_g < move_cnt * now_w ? now_g : move_cnt * now_w;
      silver += now_s < move_cnt * now_w ? now_s : move_cnt * now_w;
      add +=
        now_g + now_s < move_cnt * now_w ? now_g + now_s : move_cnt * now_w;
    }

    // end condition
    if (gold >= a && silver >= b && add >= a + b) {
      end = mid - 1;
      answer = Math.min(mid, answer);
    } else {
      start = mid + 1;
    }
  }

  return answer;
}

console.log(solution(10, 10, [100], [100], [7], [10]));
