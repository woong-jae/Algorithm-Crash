from collections import defaultdict
def solution(enroll, referral, seller, amount):
    answer = []
    link = defaultdict(list)    # 연결리스트(반대 방향)
    amount_dict = defaultdict(int)  # 판매원별 이익금 합계
    # 연결리스트 생성
    for i, r in enumerate(referral):
        if r == "-": continue
        link[enroll[i]].append(r)
    # 이익금 배분
    for i, a in enumerate(amount):
        cost = a * 100
        name = seller[i]
        while cost > 0:
            amount_dict[name] += cost - int(cost * 0.1)
            cost = int(cost * 0.1)
            if len(link[name]) == 0: break
            name = link[name][0]
    # answer 리스트에 추가
    for e in enroll:
        answer.append(amount_dict[e])
    return answer