# [92341] 주차 요금 계산
## Algorithm
- 구현
## Logic
- 분 단위로 입/출차 시간을 모두 기록한 후, 문제에서 주어진 식대로 계산하면 풀린다.
1. 각 차 번호의 입/출차 시간을 모두 기록함
- 출차 없을 수도 있어서 입차할 땐 디폴트 값(23시 59분)으로 출차 기록
```python
for r in records:
    tmp, num, state = r.split()
    h, m = tmp.split(":")
    time = int(h) * 60 + int(m)
    num = int(num)
    if state == "IN":
        cars.setdefault(num, [])
        cars[num].append([time, last])
    else:
        cars[num][-1][1] = time
```
1. 각 차마다 기록된 입/출차 시간에 따른 총 주차 시간을 구하고, 문제에서 주어진 식대로 비용을 계산함
```python
for c in cars:
    time = 0
    for i, o in cars[c]:
        time += (o - i)
    if time > fees[0]:
        tmp = Decimal((time - fees[0]) / fees[2])
        answer[c] = fees[1] + math.ceil(tmp) * fees[3]
    else:
        answer[c] = fees[1]
```
3. 차 번호따라 오름차순 정렬

## Review
쉬운 레벨2 문제였다. 