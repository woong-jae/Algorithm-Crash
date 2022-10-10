# [1918] 후위 표기식 
## Algorithm
- **Stack**

## Logic
- 피연산자는 바로 StringBuilder에 append 한다
- 각 연산자를 **우선순위**에 따라 스택을 이용해 pop add 한다
  - 추가하려는 연산자가 Stack의 제일 위에 있는 연산자보다 우선순위가 낮다면 우선순위가 높은 연산자가 없을때까지 pop 하고 스택에 넣어준다
  - ')'의 경우 '('가 나올때까지 pop해준다

```java
for (int i = 0; i < expression.length(); i++) {
    char c = expression.charAt(i);
    switch (c) {
        case '+':
        case '-':
        case '*':
        case '/':
            while (!stack.isEmpty() && priority.get(stack.peek()) >= priority.get(c))
                sb.append(stack.pop());
            stack.add(c);
            break;
        case '(':
            stack.add(c);
            break;
        case ')':
            while(!stack.isEmpty() && stack.peek() != '(')
                sb.append(stack.pop());
            stack.pop();
            break;
        default:
            sb.append(c);
    }
}
```

## Review
손으로 식써가면서 규칙을 찾을 수 있었다  
처음보고 옛날에 수업에서 배웠던 것 같은 문제인것 같은데 기억은 안났다 ㅎㅎ..