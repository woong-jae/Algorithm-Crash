function solution(n, stations, w) {
  const coverWidth = (2 * w) + 1;
  
  let result = 0, start = 1;
  stations.forEach(station => {
      if(start >= station - w) {
          start = station + w + 1;
          return;
      }
      
      const length = station - w - start;
      result += Math.ceil(length / coverWidth);
      
      start = station + w + 1;
  });
  
  if(start <= n) {
      result += Math.ceil((n + 1 - start) / coverWidth);
  }
  
  return result;
}