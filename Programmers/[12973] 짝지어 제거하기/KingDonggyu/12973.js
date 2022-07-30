function solution(s) {
  const stack = [];

  for (const ch of s) {
    const top = stack.length - 1;
    if (top > -1 && stack[top] === ch) {
      stack.pop();
      continue;
    }
    stack.push(ch);
  }

  return stack.length ? 0 : 1;
}
