from decimal import Decimal

def solution(lines):
    answer = []
    time = []
    N = len(lines)

    for log in lines:
        l = log.split(' ')
        S = l[1].split(':')
        T = l[2][0:-1]
        end = Decimal(int(S[0]) * 3600 + int(S[1]) * 60) + Decimal(S[2])

        start = Decimal(end) - Decimal(T) + Decimal('0.001')
        time.append([start, end])

    for i in range(N - 1):
        count = 1
        cur_e = time[i][1]
        range_e = cur_e + Decimal('0.999') # 현재 로그의 끝난 시간
        for j in range(i + 1, N): # 그 다음 로그들 중에서 겹치는거 확인함
            next_s = time[j][0]

            if cur_e >= next_s or range_e >= next_s: # 아예 겹침 or 1초 이내
                count += 1
        answer.append(count)

    if answer:
        return max(answer)
    else:
        return 1