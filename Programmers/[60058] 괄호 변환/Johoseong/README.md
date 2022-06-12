# [60058] 괄호 변환
## Algorithm
- 재귀, stack

## Logic
- 문제 조건에 따라서 괄호 나누기, 괄호 올바르게 바꾸기 과정을 재귀로 반복하면 됨.
1. 주어진 괄호들을 u, v로 나눔
```python
def divide(p):
    left, right = 0, 0
    for i in p:
        if i == '(': left += 1
        else: right += 1
        if left == right: # 괄호 세트 찾았으면 멈춤
            break
    return p[:(left + right)], p[(left + right):]
```
2. 두개로 나눠진 괄호들이 올바른 괄호인지 판단
- ```stack```을 사용해서, ')'와 '('가 알맞게 짝지어져있는지 체크함
```python
def is_correct(p):
    stack = []
    for i in p:
        if i == '(': 
            stack.append(i)
        else:
            if not stack: return False 
            stack.pop()
    return True
```
3. 만약 올바르지 않은 괄호배열이라면 문제 설명대로 올바르게 바꿔줌
```python
def convert(u):
    tmp = ''
    for l in u:
        if l == '(': tmp += ')'
        else: tmp += '('
    return tmp
```

## Review
나름 무난한 문제였는데 레벨 2치곤 어려웠다. 재귀가 핵심 아이디어인듯.