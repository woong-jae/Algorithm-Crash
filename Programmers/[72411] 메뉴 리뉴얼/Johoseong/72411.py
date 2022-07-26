from itertools import combinations
def solution(orders, course):
    answer = []
    combis = dict()
    for i in range(len(course)):
        combis[course[i]] = dict()

    for c in course:
        for o in orders:
            coms = list(combinations(o, c)) # 각 order마다 course개 조합 구함
            for com in coms:
                tmp = ''.join(sorted(com))
                combis[c].setdefault(tmp, 0)
                combis[c][tmp] += 1 # 해당 코스 조합이 나온 횟수 count
    
    for i in combis: # 제일 많이 주문된 코스조합 구하기
        MAX = 2 # 2명 이상 손님이 주문한 경우만 됨
        tmp = []
        for j in combis[i]:
            if combis[i][j] > MAX:
                MAX = combis[i][j]
                tmp = [j]
            elif combis[i][j] == MAX:
                tmp.append(j)
        for t in tmp:
            answer.append(t)

    answer.sort()
    return answer