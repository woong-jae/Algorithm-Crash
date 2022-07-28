# [12973] 짝지어 제거하기
## Algorithm
- stack
## Logic
- 문자열 첫 번째부터 비교하면서 stack에 넣음
- 같은 게 생기면 pop한다.
```javascript
function solution(s) {
  let stack = [];
  for (let i = 0; i < s.length; i++) {
    if (stack[stack.length - 1] !== s[i]) {
      stack.push(s[i]);
    } else {
      stack.pop();
    }
  }
  return stack.length === 0 ? 1 : 0;
}
```

## Review
정규식이랑 투포인터로 풀려다가 시간초과가 계속 떴다. 알고리즘이 스택인 걸 일찍 알았더라면..
옛날에 풀었던 괄호 문제랑 똑같은 알고리즘이다.
