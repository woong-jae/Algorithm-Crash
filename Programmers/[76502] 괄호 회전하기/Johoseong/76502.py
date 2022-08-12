import copy
def solution(s):
    answer = 0
    N = len(s)
    new = s

    for _ in range(N):
        stack = ''
        cur = copy.deepcopy(new)
        while cur:
            stack += cur[0]
            cur = cur[1:]
            if stack[-2:] == '[]' or stack[-2:] == '()' or stack[-2:] == '{}':
                stack = stack[:-2]
                continue
        if not stack:
            answer += 1

        new = new + new[0]
        new = new[1:]

    return answer