function solution(s) {
  let dp = Array.from(Array(s.length + 1), () => Array(s.length + 1).fill(null));
  const isPalindrome = (start, end) => {
      if(dp[start][end] !== null) return dp[start][end];
      if(start >= end) return true;
      if(s[start] !== s[end]) return false;
      
      return dp[start][end] = isPalindrome(start + 1, end - 1);
  }
  
  let result = 1;
  for(let i = 0; i < s.length; i++) {
      const char = s[i];
      
      let sameIndex = i + result - 1;
      while(1) {
          sameIndex = s.indexOf(char, sameIndex + 1);
          if(sameIndex === -1) break;
          
          if(!isPalindrome(i, sameIndex)) continue;
          result = sameIndex - i + 1;
      }
  }
  return result;
}