function solution(land, P, Q) {
  land = land.flat().sort((a, b) => a - b);
  const totalBlocks = land.reduce((acc, cur) => acc + cur);
  
  let answer = Infinity, blockSum = 0;
  land.forEach((height, width) => {
      const toAdd = height * width - blockSum;
      const toDelete = totalBlocks - blockSum - (land.length - width) * height;

      const cost = toAdd * P + toDelete * Q;

      answer = Math.min(answer, cost);

      blockSum += height;
  });
  return answer;
}