function solution(cookie) {
  const N = cookie.length;
  
  let result = 0;
  for(let mid = 0; mid < N - 1; mid++) {
      let left = mid, right = mid + 1;
      let leftSum = cookie[left], rightSum = cookie[right];
      
      while(1) {
          if(leftSum === rightSum) result = Math.max(result, rightSum);
          if(leftSum <= rightSum) {
              if(left === 0) break;
              leftSum += cookie[--left];
          }
          else {
              if(right === N - 1) break;
              rightSum += cookie[++right];
          }
      }
  }
  
  return result;
}