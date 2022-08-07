from collections import deque
def solution(s):
    answer = []

    for a in s:
        tmp = a
        stack = ''
        cnt = 0
        
        for t in tmp: # 110 없애기
            stack += t
            if stack[-3:] == '110':
                stack = stack[:-3]
                cnt += 1
        que = deque()
        stack = deque(list(stack))
        while stack: # 0 만나면 break (더 이상 앞에 110 넣어서 이득 없음)
            t = stack.pop()
            if t == '0':
                stack.append('0')
                break
            que.append(t)

        while cnt: # 110붙이고 나머지 1들 붙임
            cnt -= 1
            stack.append('1')
            stack.append('1')
            stack.append('0')
        stack += que
        answer.append(''.join(stack))
    return answer