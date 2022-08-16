from collections import deque

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

def solution(s):
    answer = 0
    q = deque(s)
    for i in range(1, len(s)):
        if check(q): answer += 1
        q.rotate(-1)    # deque 왼쪽으로 회전
    return answer