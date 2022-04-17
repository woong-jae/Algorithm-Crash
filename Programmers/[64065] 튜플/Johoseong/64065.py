def solution(s):
    answer = []
    tuples = s[2:-2].split('},{') # 중괄호 기준으로 쪼갬

    tuples.sort(key = lambda x:len(x)) # 길이로 오름차순 정렬
    
    sets = []
    for t in tuples:    # 문자열 -> 정수
        sets.append(list(map(int, t.split(','))))

    for s in sets:
        for i in s: # answer에 없는 원소 나옴 -> answer 맨 뒤에 추가
            if i not in answer:
                answer.append(i)

    return answer
