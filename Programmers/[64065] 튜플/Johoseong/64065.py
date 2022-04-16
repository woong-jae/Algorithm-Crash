def solution(s):
    answer = []
    tuples = s[2:-2].split('},{') # 중괄호 기준으로 쪼갬

    tuples.sort(key = lambda x:len(x)) # 길이로 오름차순 정렬
    
    for i in range(len(tuples)):
        tuple_set = tuples[i].split(',')
        for j in tuple_set: # answer에 없는 원소 나옴 -> answer 맨 뒤에 추가
            if j not in answer:
                answer.append(j)

    answer = list(map(int, answer))
    return answer
