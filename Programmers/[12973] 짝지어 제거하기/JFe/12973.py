def solution(s):
    stack = []
    for i in range(len(s)):
        # stack이 비어있지 않고, stack의 top 값이 현재 문자와 같으면 추가하지 않고 기존 값도 pop
        if len(stack) != 0 and stack[-1] == s[i]:
            stack.pop()
            continue
        # stack이 비어있거나, stack의 top 값이 현재 문자와 다르면 현재 문자 추가
        stack.append(s[i])
    if len(stack) == 0: return 1
    else: return 0