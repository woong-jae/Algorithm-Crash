from collections import defaultdict
from itertools import product
import bisect

def solution(info, query):
    answer = []
    info_dict = defaultdict(list)
    # 중복 순열로 key 조합 생성 후, 딕셔너리에 값 추가
    for i in info:
        l = i.split()
        prod_list = list(product([l[0], '-'], [l[1], '-'], [l[2], '-'], [l[3], '-']))
        for p in prod_list:
            info_dict[''.join(p)].append(int(l[-1]))
    # 딕셔너리 정렬
    for key in info_dict.keys():
        info_dict[key].sort()
    # query를 딕셔너리 key 형태로 맞춰주고, 점수로 이진 탐색
    for q in query:
        q_list = q.split()
        key = ''.join([q_list[0], q_list[2], q_list[4], q_list[6]])
        index = bisect.bisect_left(info_dict[key], int(q_list[-1]))
        count = len(info_dict[key]) - index
        answer.append(count)
    return answer