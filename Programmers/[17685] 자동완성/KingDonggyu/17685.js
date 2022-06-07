function solution(words) {
  const inputCount = new Array(words.length).fill(0);

  words.sort((a, b) => (a < b ? -1 : 1));

  for (let x = 0; x < words.length - 1; x++) {
    let y = 0;
    while (y < words[x].length && words[x][y] === words[x + 1][y]) {
      y++;
    }

    inputCount[x] = Math.max(inputCount[x], words[x].length === y ? y : y + 1);
    inputCount[x + 1] = Math.max(inputCount[x + 1], y + 1);
  }

  return inputCount.reduce((a, b) => a + b);
}
