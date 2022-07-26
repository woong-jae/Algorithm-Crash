from itertools import combinations
def binary_search(arr, value): # lower bound
    n = len(arr)
    lo, hi = -1, n

    while (lo + 1 < hi):
        mid = (lo + hi) // 2
        if not (arr[mid] >= value): lo = mid
        else: hi = mid
    return hi

def solution(info, query):
    answer = []
    tables = dict()
    for i in info: # info마다 가능한 조합 다 구하고 tables에 넣기 (value: 그 info 점수)
        rows = i.split()
        for c in range(5):
            key = list(combinations(rows[:-1], c))
            for k in key:
                tmp = ''.join(k)
                tables.setdefault(tmp, [])
                tables[tmp].append(int(rows[-1]))

    for t in tables: # 점수 정렬
        tables[t].sort()
    
    for q in query: # 쿼리에 해당하는거 조합 tables에서 꺼냄
        tmp = q.split()
        key = ''
        for t in tmp[:-1]:
            if t == '-' or t == 'and': continue
            key += t
        if not tables.get(key): # 해당 조합(키) 없는 경우 처리
            answer.append(0)
            continue
        index = binary_search(tables[key], int(tmp[-1])) # 꺼낸 점수들에서 x 이상인 값들 이진탐색으로 count
        answer.append(len(tables[key]) - index)
    return answer

print(solution(["java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"], ["java and frontend and junior and pizza 100"]))