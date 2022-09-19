function solution(s) {
  let deleteCount = 0;
  let iterationCount = 0;
  
  const iteration = (s) => {
      const result = s.split("").filter(c => {
          if(c === "0") {
              deleteCount += 1;
              return false;
          }
          return true;
      }).join("");
      
      return result.length.toString(2);
  }
  
  while(s !== "1") {
      iterationCount += 1;
      s = iteration(s);
  }
  
  return [iterationCount, deleteCount];
}