function isCorrectString(u) {
  let check = 0;
  for (const bracket of u) {
    check = bracket === '(' ? check + 1 : check - 1;
    if (--check < 0) return false;
  }
  return true;
}

function splitBalancedString(w) {
  let u = '';
  let leftBracketCnt = 0;
  let rightBracketCnt = 0;

  for (let i = 0; i < w.length; i++) {
    if (w[i] === '(') leftBracketCnt++;
    else rightBracketCnt++;

    u += w[i];

    if (leftBracketCnt === rightBracketCnt) {
      return { u, v: w.slice(i + 1) };
    }
  }
  return { u: u, v: "" }
}

function getCorrectString(w, joined) {
  // 올바른 괄호 문자열 확인
  if (isCorrectString(w)) {
    return w;
  }

  // 두 균형잡힌 괄호 문자열 분할
  let { u, v } = splitBalancedString(w);

  // u가 올바른 괄호 문자열인지 확인
  if (isCorrectString(u)) {
    return getCorrectString(v, joined + u);
  }

  // v에 대해 재귀 수행
  v = getCorrectString(v, '')

  // 문자열 생성
  let str = '(' + v + ')';

  for (const bracket of u.slice(1, - 1)) {
    str += bracket === '(' ? ')' : '(';
  }

  // 그대로 둔 올바른 문자열(u)에 이어 붙이기
  return joined + str;
}

function solution(p) {
  return getCorrectString(p, '');
}