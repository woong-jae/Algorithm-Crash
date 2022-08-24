function solution(s) {
  const n = s.length;
  const reversed = Array.from(s).reverse();
  const dp = Array.from(Array(n), () => Array(n).fill(0));

  let answer = 0;

  for (let x = 0; x < n; x++) {
    for (let y = 0; y < n; y++) {
      if (s[x] !== reversed[y]) {
        continue;
      }

      dp[x][y] = !x || !y ? 1 : dp[x - 1][y - 1] + 1;

      if (answer < dp[x][y] && isPalindrome(s, x, dp[x][y])) {
        answer = dp[x][y];
      }
    }
  }

  return answer;
}

function isPalindrome(s, right, length) {
  let left = right - length + 1;
  while (left < right) {
    if (s[left++] !== s[right--]) {
      return false;
    }
  }
  return true;
}
