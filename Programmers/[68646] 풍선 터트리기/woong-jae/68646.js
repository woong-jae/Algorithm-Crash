function solution(a) {
  const segmentTree = Array(4 * a.length);
  
  const init = (start, end, node) => {
      if(start === end) return segmentTree[node] = start;
      
      const mid = Math.floor((start + end) / 2);
      
      const left = init(start, mid, node * 2);
      const right = init(mid + 1, end, node * 2 + 1);
      return segmentTree[node] = (a[left] < a[right] ? left : right);
  }
  
  function query(left, right) {
      function helper(start, end, node, left, right) {
          if (end < left || right < start) return null;
          if (left <= start && end <= right) return segmentTree[node];
          
          const mid = Math.floor((start + end) / 2);
          
          const leftMinIndex = helper(start, mid, node * 2, left, right);
          const rightMinIndex = helper(mid + 1, end, node * 2 + 1, left, right);
          
          if(!(leftMinIndex && rightMinIndex)) return leftMinIndex || rightMinIndex;
          
          return a[leftMinIndex] < a[rightMinIndex] ? leftMinIndex : rightMinIndex;
      }
      return helper(0, a.length - 1, 1, left, right);
  }
  
  init(0, a.length - 1, 1);
  
  const mid = query(0, a.length - 1);
  let leftEnd = mid, rightStart = mid;
 
  let result = 1;
  while(leftEnd > 0) {
      leftEnd = query(0, leftEnd - 1);
      result++;
  }
  while(rightStart < a.length - 1) {
      rightStart = query(rightStart + 1, a.length - 1);
      result++;
  }
  
  return result;
}