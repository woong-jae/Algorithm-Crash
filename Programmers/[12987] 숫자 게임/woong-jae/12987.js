function solution(A, B) {
  A.sort((a, b) => a - b);
  B.sort((a, b) => a - b);
  
  let answer = 0, indexA = 0;
  for(let i = 0; i < B.length; i++) {
      if(A[indexA] < B[i]) {
          answer += 1;
          indexA += 1;
      }
  }
  return answer;
}