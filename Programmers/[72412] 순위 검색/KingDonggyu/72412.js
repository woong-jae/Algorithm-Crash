function createInfoMap(info) {
  const patterns = [
    [1, 1, 1, 1], [0, 0, 0, 0],
    [0, 1, 1, 1], [1, 0, 0, 0],
    [1, 0, 1, 1], [0, 1, 0, 0],
    [1, 1, 0, 1], [0, 0, 1, 0],
    [1, 1, 1, 0], [0, 0, 0, 1],
    [0, 0, 1, 1], [1, 1, 0, 0],
    [0, 1, 0, 1], [1, 0, 1, 0],
    [0, 1, 1, 0], [1, 0, 0, 1],    
  ];

  const infoMap = new Map();

  info.forEach((str) => {
    const arr = str.split(' ');
    const score = arr.pop();
    const key = arr.reduce((a, b) => a + b[0], '');

    patterns.forEach((pattern) => {
      let patternKey = '';
      pattern.forEach((state, i) => {
        patternKey += state ? key[i] : '-';
      });

      infoMap.has(patternKey)
        ? infoMap.get(patternKey).push(+score)
        : infoMap.set(patternKey, [+score]);
    });
  });

  for (const key of infoMap.keys()) {
    infoMap.get(key).sort((a, b) => a - b);
  }

  return infoMap;
}

function searchInfoMap(infoMap, str) {
  const arr = str.split(' and ');
  arr.push(...arr.pop().split(' '));

  const score = +arr.pop();
  const key = arr.reduce((a, b) => a + b[0], '');

  if (!infoMap.has(key)) {
    return 0;
  }

  return getScoreCount(infoMap.get(key), score);
}

// Binary Search
function getScoreCount(values, score) {
  let left = 0;
  let right = values.length - 1;

  while (left <= right) {
    const mid = Math.floor((left + right) / 2);
    if (values[mid] < score) {
      left = mid + 1;
    } else {
      right = mid - 1;
    }
  }

  return values.length - left;
}

function solution(info, query) {
  const answer = [];
  const infoMap = createInfoMap(info);

  query.forEach((str, i) => {
    answer[i] = searchInfoMap(infoMap, str);
  });

  return answer;
}
