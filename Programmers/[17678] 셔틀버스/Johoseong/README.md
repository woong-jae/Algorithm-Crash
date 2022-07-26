# [17678] 셔틀버스
## Algorithm
- 구현?
## Logic
- 버스 시간표 따라 크루원들을 줄 세운 다음, 맨 마지막 버스 줄에 대해서만 고려하면 됨!
1. 버스 출발 시간표를 만듬.
```python
for _ in range(n): # 버스 시간표 만듦
    bus_time[start] = []
    start += t
```
2. 크루원들의 도착시간을 오름차순 정렬한 후, 버스 시간표에 맞게 배분을 함. (= 줄 세움)
```python
crew_time.sort() # 크루 도착시간 정렬
for crew in crew_time: # 버스 시간표에 크루 배분 (줄세움)
    for bus in bus_time:
        if len(bus_time[bus]) == m:
            continue
        if crew <= bus:
            bus_time[bus].append(crew)
            break
```
3. 제일 마지막에 운행하는 버스 줄만 고려하면 됨.
```python
last_line = bus_time[last]
if len(last_line) < m: # 맨 마지막 버스의 줄이 m 안넘음 -> 버스 출발 시간에 도착
    hour = str(last // 60)
    minute = str(last % 60)
else: # 맨 마지막 버스의 줄이 m 넘음 -> 마지막에 선 사람보다 1분 일찍 도착
    end = last_line.pop()
    hour = str((end - 1) // 60)
    minute = str((end - 1) % 60)
```
- 줄이 m보다 작음 -> 마지막 운행 버스의 출발 시간에 딱 맞춰서 도착하면 됨.
- 줄이 m임 -> 맨 마지막 크루원의 도착시간보다 딱 1분 빠르게 도착하면 됨.

## Review
lv.3치고 쉬운 문제였다고 생각한다.