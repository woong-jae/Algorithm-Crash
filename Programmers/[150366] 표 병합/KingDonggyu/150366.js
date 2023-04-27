function encodeKey(r, c) {
  return `${r},${c}`;
}

function decodeKey(key) {
  return key.split(',');
}

function updateTableByMergeMap(table, r, c, mergeMap) {
  const key = encodeKey(r, c);

  if (!mergeMap.has(key)) {
    return;
  }

  Array.from(mergeMap.get(key)).forEach((k) => {
    const [x, y] = decodeKey(k);

    if (table[x][y] === table[r][c]) {
      return;
    }

    table[x][y] = table[r][c];
    updateTableByMergeMap(table, x, y, mergeMap);
  });
}

function updateValue(table, r, c, value, mergeMap) {
  table[r][c] = value;
  updateTableByMergeMap(table, r, c, mergeMap);
}

function updateValueToAnotherValue(table, value1, value2, mergeMap) {
  table.forEach((row, x) => {
    row.forEach((_, y) => {
      if (x && y && table[x][y] === value1) {
        table[x][y] = value2;
        updateTableByMergeMap(table, x, y, mergeMap);
      }
    });
  });
}

function merge(table, r1, c1, r2, c2, mergeMap) {
  const value = table[r1][c1] || table[r2][c2];

  table[r1][c1] = value;
  table[r2][c2] = value;

  [
    [r1, c1],
    [r2, c2],
  ].forEach(([r, c]) => {
    const key = encodeKey(r, c);

    const otherKey =
      r === r1 && c === c1 ? encodeKey(r2, c2) : encodeKey(r1, c1);

    if (mergeMap.has(key)) {
      mergeMap.get(key).add(otherKey);
    } else {
      mergeMap.set(key, new Set([otherKey]));
    }

    updateTableByMergeMap(table, r, c, mergeMap);
  });
}

function unmerge(table, r, c, mergeMap) {
  const key = encodeKey(r, c);

  if (!mergeMap.has(key)) {
    return;
  }

  const merged = Array.from(mergeMap.get(key));

  mergeMap.delete(key);

  merged.forEach((k) => {
    const [x, y] = decodeKey(k);
    table[x][y] = null;
    unmerge(table, x, y, mergeMap);
  });
}

function print(table, r, c) {
  if (!table[r][c]) {
    return 'EMPTY';
  }
  return table[r][c];
}

function solution(commands) {
  const answer = [];
  const mergeMap = new Map();
  const table = Array.from(Array(51), () => Array(51).fill(null));

  commands.forEach((cmd) => {
    const c = cmd.split(' ');

    switch (c[0]) {
      case 'UPDATE':
        if (c.length === 4) {
          updateValue(table, c[1], c[2], c[3], mergeMap);
          break;
        }
        updateValueToAnotherValue(table, c[1], c[2], mergeMap);
        break;
      case 'MERGE':
        merge(table, c[1], c[2], c[3], c[4], mergeMap);
        break;
      case 'UNMERGE':
        const value = table[c[1]][c[2]];
        unmerge(table, c[1], c[2], mergeMap);
        table[c[1]][c[2]] = value;
        break;
      default:
        answer.push(print(table, c[1], c[2]));
    }
  });

  return answer;
}
