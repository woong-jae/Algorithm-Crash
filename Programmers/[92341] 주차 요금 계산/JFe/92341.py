from collections import defaultdict
import math
def solution(fees, records):
    answer = []
    cur_dict = defaultdict(int)  # 현재 입차되어 있는 차의 입차 시간
    time_dict = defaultdict(int) # 차량별 주차 시간
    # record 분석해서 주차 시간 계산
    for record in records:
        time, car, flag = record.split()
        hour, min = time.split(':')
        if flag == 'IN':
            cur_dict[car] = int(hour)*60 + int(min)
        else:
            time_dict[car] += int(hour)*60 + int(min) - cur_dict[car]
            del cur_dict[car]
    # 출차 내역 없는 경우 계산
    for key in cur_dict.keys():
        if cur_dict[key] >= 0:
            time_dict[key] += 1439 - cur_dict[key]
    car_list = sorted(time_dict.keys()) # 차량 번호 오름차순 정렬
    # 주차 시간별 요금 계산
    for key in car_list:
        if time_dict[key] <= fees[0]:
            answer.append(fees[1])
        else:
            answer.append(fees[1] + math.ceil((time_dict[key] - fees[0]) / fees[2]) * fees[3])
    return answer