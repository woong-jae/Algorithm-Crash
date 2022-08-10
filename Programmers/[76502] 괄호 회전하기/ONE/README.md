# [76502] 괄호 회전하기
## Algorithm
- **Stack**

## Logic
- 시작 위치(x)를 0 부터 n - 1 까지 옮겨가며 확인한다
- 문자엸에서 문자는 % n 연산을 통해 가져온다
- 가져온 문자를 확인해서 여는 괄호일 경우 스택에 추가만 해준다
- 닫는 괄호일 경우에 스택이 비었거나 스택의 제일위가 같은 종류의 괄호가 아니라면 올바른 문자열이 아니기때문에 return false
- 같은 종류의 괄호라면 스택에서 pop 해서 제거해준다
- 반복문을 전부 수행후에 스택이 비어있지 않다면(여는 문자열의 개수가 더 많은 경우) 올바르지 않은 문자열이므로 return false

```java
private boolean check(int n, int x, String s) {
    Stack<Character> stack = new Stack<>();
    for (int i = x; i < n + x; i++) {
        char c = s.charAt(i % n);
        switch (c) {
            case '[':
            case '{':
            case '(':
                stack.push(c); break;
            case ']':
                if (stack.isEmpty() || stack.peek() != '[')
                    return false;
                else
                    stack.pop(); break;
            case '}':
                if (stack.isEmpty() || stack.peek() != '{')
                    return false;
                else
                    stack.pop(); break;
            case ')':
                if (stack.isEmpty() || stack.peek() != '(')
                    return false;
                else
                    stack.pop(); break;
        }
    }
    return stack.isEmpty();
}
```

## Review
처음에는 여는 괄호(+1), 닫는 괄호(-1)해서 음수가 되는경우와 다 끝났을때 0이 아닌경우로 구현했었는데  
14번만 답이 틀려 반례를 생각해보니 '({[)}]'와 같은 경우에는 내가 짠 코드에서는 올바르다고 인식이 되었다 
그래서 스택을 써야한다는 걸 금방 알았고 쉽게 구현할수 있었다 굳!
