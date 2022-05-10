function solution(s) {
  const lexicon = new Map();

  lexicon
    .set('zero', 0)
    .set('one', 1)
    .set('two', 2)
    .set('three', 3)
    .set('four', 4)
    .set('five', 5)
    .set('six', 6)
    .set('seven', 7)
    .set('eight', 8)
    .set('nine', 9);

  let answer = '';
  let string = '';

  for (const ch of s) {
    if (+ch || ch === '0') {
      answer += ch;
      continue;
    }

    string += ch;

    if (lexicon.has(string)) {
      answer += lexicon.get(string);
      string = '';
    }
  }

  return +answer;
}
