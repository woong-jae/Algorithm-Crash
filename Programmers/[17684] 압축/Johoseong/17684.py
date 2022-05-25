def solution(msg):
    answer = []
    arr = list(msg)
    N = len(arr)
    dic, index = dict(), 26
    
    for i in range(1,27): # 사전 만들기
        dic[chr(64 + i)] = i

    s, e = 0, 1
    while True:
        cur = msg[s:e]

        if e > N: # 종료조건
            answer.append(dic[prev])
            break

        if cur in dic:
            prev = cur
            e += 1
            continue
        else: #사전에 없음
            answer.append(dic[prev])
            index += 1
            dic[cur] = index
            s = e - 1

    return answer

print(solution("KAKAO"))