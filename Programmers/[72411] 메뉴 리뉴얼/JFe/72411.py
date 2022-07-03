from itertools import combinations
from collections import defaultdict

# 해당 조합을 포함하고 있는 order 수 반환하는 함수
def check(orders, c):
    count = 0
    for order in orders:
        flag = True
        for ch in c:
            if ch not in order:
                flag = False
                break
        if flag: count += 1
    return count

def solution(orders, course):
    answer = []
    result = defaultdict(set)   # 
    result_count = defaultdict(int)
    for order in orders:
        for num in course:
            # 갯수별 조합 생성
            comb = list(combinations(order, num))
            for c in comb:
                c = sorted(c)
                count = check(orders, c)
                if count > 1:   # 2명 이상 주문한 경우만
                    if result_count[num] <= count:  # 기존 갯수보다 크거나 같은 경우에만 추가
                        result[num].add((count, ''.join(c)))
                        result_count[num] = count
    # 각 개수별 코스요리 집합에서 조건에 맞는 값만 answer에 추가
    for num in course:
        if len(result[num]) == 0: continue
        for cnt, name in result[num]:
            if cnt == result_count[num]:
                answer.append(name)
    answer.sort()
    return answer