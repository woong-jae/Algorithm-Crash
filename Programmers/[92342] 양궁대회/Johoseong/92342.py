from itertools import combinations
def solution(n, info):
    answer = [-1]
    scores = [ i for i in range(10, 0, -1) ]
    wins = { i: [] for i in range(10, 0, -1) }
    for i in range(10, 0, -1):
        wins[i] = list(combinations(scores, i))
    MAX = -1

    for i in range(10, 0, -1):
        for case in wins[i]:
            remain = n # 라이언 남은 화살 수
            ryan_info = [0 for _ in range(10, -1, -1)]
            ryan_score = 0
            apeach_score = 0
            for j, a_num in enumerate(info):
                if j == 10:
                    if remain > 0:
                        ryan_info[j] = remain
                    break
                cur = scores[j]
                if cur in case: # 라이언이 더 맞춤
                    remain -= (a_num + 1)
                    ryan_score += cur
                    ryan_info[j] = a_num + 1
                elif a_num != 0: # 어피치가 더 맞춤
                    apeach_score += cur
            
            if remain < 0 or apeach_score > ryan_score: # 라이언이 승리할 수 없는 case임
                continue
            
            sub = ryan_score - apeach_score
            if MAX < sub and sub != 0: # *** 라이언 어피치 비기면 [-1]임!!!!!!
                answer = ryan_info
                MAX = sub
            elif MAX == sub:
                for k in range(10, -1, -1):
                    if answer[k] == ryan_info[k]: continue
                    elif answer[k] > ryan_info[k]: break
                    else:
                        answer = ryan_info
                        MAX = sub
                        break
    return answer

print(solution(10, [0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3]))