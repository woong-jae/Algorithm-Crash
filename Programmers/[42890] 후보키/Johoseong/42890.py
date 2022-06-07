from itertools import combinations
def solution(relation):
    col_num = len(relation[0])

    cols = [i for i in range(col_num)]
    combis = []
    for i in range(1, col_num + 1):
        combis.append(list(combinations(cols, i)))
    
    answer = set()
    for i in range(col_num):
        for combi in combis[i]:
            is_candidate = True

            for sets in answer: # 최소성 검사
                cnt = 0
                for s in sets:
                    if s in combi:
                        cnt += 1
                if len(sets) == cnt:
                    is_candidate = False
            if is_candidate == False:
                continue

            sets = []
            for r in relation: # 유일성 검사
                tmp = []
                for c in combi:
                    tmp.append(r[c])
                if tmp in sets:
                    is_candidate = False
                    break
                sets.append(tmp)

            if is_candidate == True:
                answer.add(combi)

    return len(answer)