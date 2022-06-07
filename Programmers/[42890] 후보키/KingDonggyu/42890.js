function getCombination(arr, selectNum) {
  const results = [];

  if (selectNum === 1) {
    return arr.map((el) => [el]);
  }

  arr.forEach((fixed, idx, arr) => {
    if (fixed === null) {
      return;
    }
    const sliced = arr.slice(idx + 1);
    const combination = getCombination(sliced, selectNum - 1);
    const attached = combination.map((el) => [fixed, ...el]);
    results.push(...attached);
  });

  return results;
}

function isMinimal(keys, arr) {
  for (const key of keys) {
    if (key.every((k) => arr.includes(k))) {
      return false;
    }
  }
  return true;
}

function solution(relation) {
  const attrSize = relation[0].length;
  const attrIdxValues = new Array(attrSize).fill(0).map((_, i) => i);
  const keys = [];

  let answer = 0;

  for (let selectNum = 1; selectNum <= attrSize; selectNum++) {
    const combination = getCombination(attrIdxValues, selectNum);
    combination.forEach((comb) => {
      if (!isMinimal(keys, comb)) {
        return;
      }
      
      const set = new Set();
      relation.forEach((row) => {
        const tuple = comb.reduce((prev, idx) => {
          return prev + row[idx];
        }, '');
        set.add(tuple);
      });

      if (set.size === relation.length) {
        keys.push(comb);
        answer++;
      }
    });
  }

  return answer;
}
