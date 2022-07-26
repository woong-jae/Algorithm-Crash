def solution(n, t, m, timetable):
    answer = ''
    start = 540 # 시작 시간 (9:00)
    last = start + t * (n - 1)
    bus_time = dict()
    crew_time = []

    for _ in range(n): # 버스 시간표 만듦
        bus_time[start] = []
        start += t
    for time in timetable:
        crew_time.append(60 * int(time[0:2]) + int(time[3:]))

    crew_time.sort() # 크루 도착시간 정렬

    for crew in crew_time: # 버스 시간표에 크루 배분 (줄세움)
        for bus in bus_time:
            if len(bus_time[bus]) == m:
                continue
            if crew <= bus:
                bus_time[bus].append(crew)
                break
    
    last_line = bus_time[last]
    if len(last_line) < m: # 맨 마지막 버스의 줄이 m 안넘음 -> 버스 출발 시간에 도착
        hour = str(last // 60)
        minute = str(last % 60)
    else: # 맨 마지막 버스의 줄이 m 넘음 -> 마지막에 선 사람보다 1분 일찍 도착
        end = last_line.pop()
        hour = str((end - 1) // 60)
        minute = str((end - 1) % 60)

    if len(hour) == 1: hour = "0" + hour
    if len(minute) == 1: minute = "0" + minute

    answer = hour + ":" + minute

    return answer