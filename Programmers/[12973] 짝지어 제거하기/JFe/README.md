# [12973] 짝지어 제거하기 - Python

## 🔍 Algorithm
**Stack**

## 💻 Logic

```Python
    for i in range(len(s)):
        # stack이 비어있지 않고, stack의 top 값이 현재 문자와 같으면 추가하지 않고 기존 값도 pop
        if len(stack) != 0 and stack[-1] == s[i]:
            stack.pop()
            continue
        # stack이 비어있거나, stack의 top 값이 현재 문자와 다르면 현재 문자 추가
        stack.append(s[i])
```
- **stack이 비어있지 않고, stack의 top 값이 현재 문자와 같으면**  
    현재 문자 append 하지 않고 기존 값도 pop  
- **stack이 비어있거나, stack의 top 값이 현재 문자와 다르면**  
    현재 문자 stack에 append  


## 📝 Review

예전에도 비슷한 문제를 풀어봤어서 바로 스택을 사용해서 풀어야겠다고 생각하고 풀었다.  
대표적인 스택 사용하는 문제..!  