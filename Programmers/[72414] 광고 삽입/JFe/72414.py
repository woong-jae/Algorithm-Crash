def solution(play_time, adv_time, logs):
    tmp = play_time.split(':')
    play_time = int(tmp[0]) * 3600 + int(tmp[1]) * 60 + int(tmp[2])
    time_list = [0 for _ in range(play_time + 1)]
    # log 시간 변환
    for log in logs:
        l = log.split('-')
        for i, v in enumerate(l):
            tmp = v.split(':')
            l[i] = int(tmp[0]) * 3600 + int(tmp[1]) * 60 + int(tmp[2])
        time_list[l[0]] += 1
        time_list[l[1]] -= 1
    # 구간별 시청자 수 저장
    for i in range(1, len(time_list)):
        time_list[i] += time_list[i-1]
    # 누적 시청자 수 저장
    for i in range(1, len(time_list)):
        time_list[i] += time_list[i-1]
    # 광고 시간 변환
    tmp = adv_time.split(':')
    adv_time = int(tmp[0]) * 3600 + int(tmp[1]) * 60 + int(tmp[2])
    # 시청자 수 가장 많은 구간 체크
    most_view = time_list[adv_time-1]
    result = 0
    for i in range(adv_time, play_time):
        if most_view < time_list[i] - time_list[i - adv_time]:
            most_view = time_list[i] - time_list[i - adv_time]
            result = i - adv_time + 1
    # 결과 값 문자열 변환
    hour = '0' + str(result // 3600)
    min = '0' + str(result % 3600 // 60)
    sec = '0' + str(result % 60)
    return hour[-2:] + ':' + min[-2:] + ':' + sec[-2:]