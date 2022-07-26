function solution(str1, str2) {
  const pattern = /^[a-zA-Z]*$/;
  const map = new Map();

  str1 = str1.toLowerCase();
  str2 = str2.toLowerCase();

  const updateMap = (str, i, isStr1) => {
    const element = str[i] + str[i + 1];

    if (!pattern.test(element)) {
      return;
    }

    if (map.has(element)) {
      const count = map.get(element);
      map.set(element, {
        str1: isStr1 ? count.str1 + 1 : count.str1,
        str2: isStr1 ? count.str2 : count.str2 + 1,
      });
      return;
    }

    map.set(element, {
      str1: isStr1 ? 1 : 0,
      str2: isStr1 ? 0 : 1,
    });
  };

  for (let i = 0; i < Math.max(str1.length, str2.length - 1); i++) {
    if (i < str1.length - 1) {
      updateMap(str1, i, true);
    }

    if (i < str2.length - 1) {
      updateMap(str2, i, false);
    }
  }

  let union = 0;
  let intersection = 0;

  for (const count of map.values()) {
    union += Math.max(count.str1, count.str2);
    intersection += Math.min(count.str1, count.str2);
  }

  if (!union && !intersection) {
    return 65536;
  }

  return Math.floor((intersection / union) * 65536);
}
