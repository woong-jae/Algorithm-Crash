function solution(n) {
  return _solution(0, n, n);
}

function _solution(v, open, close) {
  if (!open && !close) {
    return 1;
  }

  if (v < 0) {
    return 0;
  }

  let result = 0;

  if (open > 0) {
    result += _solution(v + 1, open - 1, close);
  }

  if (close > 0) {
    result += _solution(v - 1, open, close - 1);
  }

  return result;
}
