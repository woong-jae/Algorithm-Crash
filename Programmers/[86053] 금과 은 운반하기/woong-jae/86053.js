function solution(a, b, g, s, w, t) {
  let left = 0, right = 4 * (10 ** 14);
  
  const isPossible = (time) => {
      let maxGold = 0, maxSilver = 0, maxWeight = 0;
      
      t.forEach((stt, i) => {
          const moves = time > stt ? Math.floor((time - stt) / (stt * 2)) + 1 : 0;
          const canCarry = moves * w[i];
          
          maxWeight += canCarry > g[i] + s[i] ? g[i] + s[i] : canCarry;
          maxGold += canCarry > g[i] ? g[i] : canCarry;
          maxSilver += canCarry > s[i] ? s[i] : canCarry;
      });
      
      if(maxGold < a || maxSilver < b || maxWeight < (a + b)) return false;
      return true;
  }
  
  while(left + 1 < right) {
      const mid = Math.floor((left + right) / 2);
      if(isPossible(mid)) right = mid;
      else left = mid;
  }
  
  return right;
}