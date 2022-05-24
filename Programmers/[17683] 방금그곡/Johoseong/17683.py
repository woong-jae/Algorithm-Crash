def solution(m, musicinfos):
    answer = "(None)"
    m = m.replace('C#', 'H').replace('D#', 'I').replace('F#', 'J').replace('G#', 'K').replace('A#', 'L') # 샾 붙은 애들 구별하려고 다른 알파벳으로 바꿈
    t = -1

    for i in musicinfos:
        s, e, title, melody = i.split(',')
        melody = melody.replace('C#', 'H').replace('D#', 'I').replace('F#', 'J').replace('G#', 'K').replace('A#', 'L')
        l = len(melody)
        tmp1, tmp2 = s.split(':'), e.split(':')

        time = (int(tmp2[0]) * 60 + int(tmp2[1])) - (int(tmp1[0]) * 60 + int(tmp1[1])) # 해당곡이 재생된 시간
        full_melody = (time // l) * melody + melody[:(time % l) + 1] # 재생된 시간만큼 멜로디 연결

        if (m in full_melody) and (t < time): # 재생된 멜로디 안에 m 포함됨 (더 오래 재생된게 answer)
            answer = title
            t = time

    return answer