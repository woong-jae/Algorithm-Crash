function solution(s) {
  if(s.length % 2 === 1) return 0;
  const pair = new Map([["[", "]"], ["{", "}"], ["(", ")"]]);
  
  const rotatedString = i => {
      return s.slice(i) + s.slice(0, i);
  }
  
  const isCorrect = s => {
      const stack = [];
      for(let char of s) {
          const top = stack.length - 1;
          if(top >= 0 && pair.get(stack[top]) === char) {
              stack.pop();
          }
          else {
              stack.push(char);
          }
      }
      return stack.length === 0;
  }
  
  let result = 0;
  for(let i = 0; i < s.length; i++) {
      if(isCorrect(rotatedString(i))) result++;
  }
  return result;
}