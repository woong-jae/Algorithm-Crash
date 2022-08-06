function solution(s) {
  const delete110 = x => {
      const stack = [];
      let count = 0;
      
      for(const char of x) {
          stack.push(char);
          if(stack.length < 3) continue;
          
          const top = stack.length - 1;
          if(stack[top] === "0" && stack[top - 1] === "1" && stack[top - 2] === "1"){
              for(let i = 0; i < 3; i++) stack.pop();
              count++;
          }
      }
      return [stack.join(""), count];
  }
  
  return s.map(x => {
      const [filtered_x, count] = delete110(x);
      
      const insertIndex = filtered_x.lastIndexOf("0") + 1;
      
      return filtered_x.slice(0, insertIndex) + "110".repeat(count) + filtered_x.slice(insertIndex);
  });
}