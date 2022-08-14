# [76502] 괄호 회전하기 - Python

## 🔍 Algorithm
**Stack**

## 💻 Logic

- **`deque`에 문자열을 담고, `rotate(-1)`로 왼쪽으로 회전하면서 올바른 괄호 문자열인지 체크**  

```Python
def check(q):
    stack = [q[0]]
    for i in range(1, len(q)):
        # stack 비어있으면 바로 append
        if len(stack) == 0: 
            stack.append(q[i])
            continue
        # 닫힌 괄호인 경우는 stack의 top 확인해서 같은 열린 괄호면 pop
        if q[i] == ')' and stack[-1] == '(': stack.pop()
        elif q[i] == '}' and stack[-1] == '{': stack.pop()
        elif q[i] == ']' and stack[-1] == '[': stack.pop()
        # 나머지 경우는 append
        else: stack.append(q[i])
    # 마지막에 stack이 비어있으면 True, 값이 있으면 False 반환
    if len(stack) == 0: return True
    else: return False
```

- **문자 하나씩 스택 top과 비교**  
    - `stack`이 비어있으면 바로 **append**  
    - 닫힌 괄호인 경우에는 `stack`의 **top** 확인해서 -> 같은 열린 괄호면 **pop**  
    - 나머지 경우는 **append**  
- **마지막에 `stack`이 비어있으면 **True**, 값이 있으면 **False** 반환**


## 📝 Review

딕셔너리 이용해서 소, 중, 대괄호마다 딕셔너리를 만들어서 풀려고 했는데 그렇게 풀면 예외 처리를 많이 해야돼서 스택을 사용해서 푸는 방식으로 틀었다.  
그래도 이런 문제는 여러번 풀어봐서 풀이 바꿔도 빨리 풀 수 있었는 듯!
