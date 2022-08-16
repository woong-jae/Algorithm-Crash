function solution(n,a,b) {
  if(b < a) [a, b] = [b, a];
  
  let round = 1;
  while(1) {
      if((a % 2 === 1) && (a + 1 === b)) break;
      a = Math.floor((a + 1) / 2);
      b = Math.floor((b + 1) / 2);
      round++;
  }
  return round;
}