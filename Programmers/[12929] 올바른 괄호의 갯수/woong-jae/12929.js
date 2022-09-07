function solution(n) {
  let result = 0;

  const stack = [];
  const helper = (used) => {
    if (used === n) {
      result++;
      return;
    }

    if (used < n) {
      stack.push("(");
      helper(used + 1);
      stack.pop();
    }
    const top = stack.length - 1;
    if (top >= 0 && stack[top] === "(") {
      stack.pop();
      helper(used);
      stack.push("(");
    }
  }
  helper(0);

  return result;
}