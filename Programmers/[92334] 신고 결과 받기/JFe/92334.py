from collections import defaultdict
def solution(id_list, report, k):
    answer = []
    log_dict = defaultdict(list)
    reported = defaultdict(int)
    # 딕셔너리 추가
    for r in report:
        a, b = r.split()
        # 동일한 유저에 대한 신고 횟수는 1회로 처리
        if b not in log_dict[a]:
            log_dict[a].append(b)
            reported[b] += 1
    # 결과 메일 처리
    for id in id_list:
        cnt = 0
        for key in log_dict[id]:
            if reported[key] >= k:
                cnt += 1
        answer.append(cnt)
    return answer