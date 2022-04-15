def solution(s):
    answer = []
    tuples = s[2:-2].split('},{') # 중괄호 기준으로 쪼갬

    tuples.sort(key = lambda x:len(x)) # 길이로 오름차순 정렬
    
    result = set()
    for i in range(len(tuples)):
        tuple_set = tuples[i].split(',')

        for j in tuple_set:
            # if j not in result:
            result.add(j)

    # answer = list(map(int, answer))
    return result

print(solution("{{2,22},{2}}"))