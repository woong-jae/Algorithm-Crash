def convert_time(time):
    h, m, s = time.split(':')
    return int(h) * 3600 + int(m) * 60 + int(s)

def convert_str(time):
    h = time // 3600
    h = '0' + str(h) if h < 10 else str(h)
    time = time % 3600
    m = time // 60
    m = '0' + str(m) if m < 10 else str(m)
    time = time % 60
    s = '0' + str(time) if time < 10 else str(time)
    return h + ':' + m + ':' + s

def solution(play_time, adv_time, logs):
    play_time = convert_time(play_time)
    adv_time = convert_time(adv_time)               
    all_time = [0 for _ in range(play_time + 1)]

    for l in logs:
        start, end = l.split('-')
        start = convert_time(start)
        end = convert_time(end)
        all_time[start] += 1
        all_time[end] -= 1

    for i in range(1, len(all_time)): # 구간 별
        all_time[i] = all_time[i] + all_time[i - 1]

    for i in range(1, len(all_time)): # 누적
        all_time[i] = all_time[i] + all_time[i - 1]

    most_view = 0
    max_time = 0                          
    for i in range(adv_time - 1, play_time): # 누적 대비 시청자수 max 찾기
        if i >= adv_time:
            if most_view < all_time[i] - all_time[i - adv_time]:
                most_view = all_time[i] - all_time[i - adv_time]
                max_time = i - adv_time + 1
        else:
            if most_view < all_time[i]:
                most_view = all_time[i]
                max_time = i - adv_time + 1

    return convert_str(max_time)

print(solution("02:03:55", "00:14:15", ["01:20:15-01:45:14", "00:25:50-00:48:29", "00:40:31-01:00:00", "01:37:44-02:02:30", "01:30:59-01:53:29"]))