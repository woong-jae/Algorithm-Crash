import re
def solution(m, musicinfos):
    answer, length = "(None)", 0
    # '#' 들어간 음 변환
    for org, rep in zip(['A#', 'C#', 'D#', 'F#', 'G#'], ['H', 'I', 'J', 'K', 'L']):
        m = m.replace(org, rep)
    pattern = re.compile(m)
    for info in musicinfos:
        info = info.split(',')
        info[0] = info[0].split(':')
        info[1] = info[1].split(':')
        time = (int(info[1][0])*60 + int(info[1][1])) - (int(info[0][0])*60 + int(info[0][1]))
        # '#' 들어간 음 변환
        for org, rep in zip(['A#', 'C#', 'D#', 'F#', 'G#'], ['H', 'I', 'J', 'K', 'L']):
            info[3] = info[3].replace(org, rep)
        message = ''
        # time만큼 악보 정보 반복해서 message 저장
        for i in range(time):
            message += info[3][i%len(info[3])]
        # pattern이 있고, 현재 저장된 길이보다 크면 answer 변경
        if pattern.search(message) and length < time:
            answer = info[2]
            length = time
    return answer