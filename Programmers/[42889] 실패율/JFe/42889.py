from collections import defaultdict
def solution(N, stages):
    last = len(stages)
    cur_stage = defaultdict(int)    # 사용자별 현재 스테이지
    failure = defaultdict(float)    # 스테이지별 실패율
    # 사용자별 현재 스테이지 계산
    for s in stages:
        cur_stage[s] += 1
    # 스테이지별 실패율 계산
    for i in range(1, N+1):
        # 도달한 사람이 없는 경우 (division by zero 피하기 위해)
        if last == 0:
            failure[i] = 0
            continue
        failure[i] = cur_stage[i] / last
        last -= cur_stage[i]
    # value 내림차순, key 오름차순 정렬
    answer = [key for key, value in sorted(failure.items(), key = lambda x : (-x[1], x[0]))]
    return answer