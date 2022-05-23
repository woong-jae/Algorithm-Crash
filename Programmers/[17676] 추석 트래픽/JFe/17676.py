from decimal import Decimal
def solution(lines):
    answer, log = 0, []
    for l in lines:
        l = l.split(' ')    # 로그 split
        time = l[1].split(':')  # 로그 시간 부분 split
        last_time = Decimal(time[0])*3600 + Decimal(time[1])*60 + Decimal(time[2])  # 응답 완료 시간 초 단위로 변환
        start_time = last_time - Decimal(l[2][:-1]) + Decimal('0.001')  # 시작 시간 = 응답 완료 시간 - 처리 시간
        log.append((start_time, last_time))
    for i in range(len(log)):
        count = 0
        t = log[i][1]   # 응답 완료 시간
        for j in range(i, len(log)):
            # i 인덱스 뒤 로그들 시작 시간이 (i 로그의 응답 완료 시간 + 0.999s) 이하면 count 추가
            if log[j][0] <= t + Decimal('0.999'):
                count += 1
        answer = max(answer, count) # 최댓값 저장
    return answer