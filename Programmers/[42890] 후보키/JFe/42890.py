from itertools import combinations
from collections import defaultdict

def check_uniqueness(relation, comb, answer_idx):
    tmp_set = set()
    for i in range(len(relation)):
        tmp = ''
        for c in comb: tmp += relation[i][c]
        tmp_set.add(tmp)
    if len(tmp_set) == len(relation): return True
    return False

def check_minimality(answer_idx, comb):
    for i in answer_idx:
        if i.issubset(set(comb)): 
            return False
    return True

def solution(relation):
    answer, answer_idx = 0, []
    # 조합 생성 후, 후보키 확인
    for i in range(len(relation[0])):
        comb_list = combinations([int(x) for x in range(len(relation[0]))], i+1)
        for comb in comb_list:
            comb = list(comb)
            # 최소성 체크
            if not check_minimality(answer_idx, comb): continue
            # 유일성 체크
            if check_uniqueness(relation, comb, answer_idx):
                answer += 1
                answer_idx.append(set(comb))
    return answer