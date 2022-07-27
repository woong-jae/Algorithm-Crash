function solution(s) {
  const stack = [];
  
  for(const char of s) {
      const top = stack.length - 1;
      if(top >= 0 && stack[top] === char) {
          stack.pop();
          continue;
      }
      stack.push(char);
  }
  
  return stack.length ? 0 : 1;
}