def solution(id_list, report, k):
    answer = { id: 0 for id in id_list}
    result = []
    report_cnt = { id:[0, set()] for id in id_list}

    for r in report: # 유저마다 신고당한 횟수/신고한유저id 기록
        id1, id2 = r.split()
        if id1 not in report_cnt[id2][1]:
            report_cnt[id2][0] += 1
            report_cnt[id2][1].add(id1)
    for tmp in report_cnt:
        cnt, ids = report_cnt[tmp]
        if cnt >= k:
            for id in ids:
                answer[id] += 1

    for id in id_list:
        result.append(answer[id])
    return result