def solution(n, t, m, timetable):
    answer = ''
    cur_time, last_time = 540, 540
    # 시간 분 단위로 변환 후, 내림차순 정렬
    for i, time in enumerate(timetable):
        timetable[i] = int(time[:2]) * 60 + int(time[3:])
    timetable.sort(reverse=True)
    # 시간대별 셔틀 확인
    for i in range(n):
        full = True
        for j in range(m):
            # 셔틀에 탈 수 없으면, full을 False로 바꾸고 break
            if len(timetable) == 0 or timetable[-1] > cur_time:
                full = False
                break
            last_time = timetable.pop()
        cur_time += t
    if full: last_time -= 1 # 마지막 셔틀이 가득 차있으면, 제일 뒷 사람 시간 - 1
    else: last_time = cur_time - t  # 가득 차있지 않으면, 현재 셔틀 시간
    # 시간 문자열 변환
    hour = last_time // 60
    min = last_time % 60
    if hour < 10: answer += '0' + str(hour)
    else: answer += str(hour)
    answer += ':'
    if min < 10: answer += '0' + str(min)
    else: answer += str(min)
    
    return answer