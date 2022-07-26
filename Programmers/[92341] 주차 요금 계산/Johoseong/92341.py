import math
from decimal import Decimal
def solution(fees, records):
    answer = dict()
    cars = dict()
    last = 23 * 60 + 59

    for r in records: # 입/출차 기록하기
        tmp, num, state = r.split()
        h, m = tmp.split(":")
        time = int(h) * 60 + int(m)
        num = int(num)
        if state == "IN":
            cars.setdefault(num, [])
            cars[num].append([time, last])
        else:
            cars[num][-1][1] = time

    for c in cars: # 주차비용 계산
        time = 0
        for i, o in cars[c]:
            time += (o - i)
        if time > fees[0]:
            tmp = Decimal((time - fees[0]) / fees[2])
            answer[c] = fees[1] + math.ceil(tmp) * fees[3]
        else:
            answer[c] = fees[1]
    answer = [i[1] for i in sorted(answer.items())]
    return answer