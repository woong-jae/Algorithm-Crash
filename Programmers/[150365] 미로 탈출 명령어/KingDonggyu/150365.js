function checkIsOutOfBounds(n, m, x, y) {
  return !x || x > n || !y || y > m;
}

function solution(n, m, x, y, r, c, k) {
  const stack = [[x, y, []]];

  // 상 우 좌 하
  const characters = ['u', 'r', 'l', 'd'];
  const dx = [-1, 0, 0, 1];
  const dy = [0, 1, -1, 0];

  while (stack.length) {
    const [a, b, string] = stack.pop();

    const remain = k - string.length;
    const shortest = Math.abs(a - r) + Math.abs(b - c);

    if (string.length > k || remain < shortest || (remain - shortest) % 2) {
      continue;
    }

    if (a === r && b === c && string.length === k) {
      return string.join('');
    }

    for (let i = 0; i < 4; i++) {
      const [na, nb] = [a + dx[i], b + dy[i]];

      if (checkIsOutOfBounds(n, m, na, nb)) {
        continue;
      }

      stack.push([na, nb, [...string, characters[i]]]);
    }
  }

  return 'impossible';
}
