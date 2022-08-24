function solution(n) {
  return _solution(n, n);
}

function _solution(open, close) {
  if (!open) {
    return 1;
  }

  if (close < open) {
    return 0;
  }

  return _solution(open - 1, close) + _solution(open, close - 1);
}
