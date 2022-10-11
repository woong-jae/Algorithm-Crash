# [1918] 후위 표기식 - Python

## 🔍 Algorithm
**Stack**

## 💻 Logic

```Python
for e in exp:
    # 알파벳인 경우 (피연산자인 경우)
    if e.isalpha():
        postfix += e
        continue
    # * or / 인 경우 : * or / 아닌 값이 나올 때까지 pop한 후, append
    if e == '*' or e == '/':
        while stack:
            if stack[-1] != '*' and stack[-1] != '/': break
            postfix += stack.pop()
        stack.append(e)
    # + or - 인 경우 : ( 아닌 값이 나올 때까지 pop한 후, append
    elif e == '+' or e == '-':
        while stack:
            if stack[-1] == '(': break
            postfix += stack.pop()
        stack.append(e)
    # ( 인 경우 : 바로 append
    elif e == '(':
        stack.append(e)
    # ) 인 경우 : (가 나올 때까지 pop한 후, (도 pop
    else:
        while stack:
            if stack[-1] == '(': 
                stack.pop()
                break
            postfix += stack.pop()
```
- **알파벳**인 경우 : 피연산자인 경우 => `postfix` 문자열에 바로 추가  
- `*` or `/` 인 경우 : `*` or `/` 아닌 값이 나올 때까지 **pop**하고 `postfix` 문자열에 추가한 후, `stack`에 **append**  
- `+` or `-` 인 경우 : `(` 아닌 값이 나올 때까지 **pop**하고 `postfix` 문자열에 추가한 후, `stack`에 **append**  
- `(` 인 경우 : `stack`에 바로 **append**  
- `)` 인 경우 : `(`가 나올 때까지 **pop**하고 `postfix` 문자열에 추가한 후, `(`도 **pop**  


## 📝 Review

무려 4년 전에 자료구조 공부하면서 C로 풀었었던 문제,,  
그래도 그 때보다 발전했네  