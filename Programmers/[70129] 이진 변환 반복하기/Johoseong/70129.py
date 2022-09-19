def solution(s):
    answer = [0, 0] # 횟수, 0 개수

    while s != '1':
        tmp = ''
        for i in s: # 1. 0 제거
            if i == '0':
                answer[1] += 1
                continue
            tmp += i

        l = len(tmp) # 2. 길이 구함
        s = format(l, 'b') # 3. 길이->2진수 변환
        answer[0] += 1

    return answer