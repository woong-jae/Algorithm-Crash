from collections import deque
def solution(stack):
    stack = deque(stack)
    que = deque()
    
    while stack:
        last = stack.pop()
        if que and que[0] == last:
            que.popleft()
            continue
        que.insert(0, last)

    if que: answer = 0
    else: answer = 1
    return answer