# [72414] 광고 삽입
## Algorithm
- DP
## Logic
1. 시간들 초 단위로 변환
2. log의 시작 시간, 끝 시간을 나눠서 구간 별 시청자 수를 셈
3. 해당 구간 시청자 수 셈
```python
for l in logs:
    start, end = l.split('-')
    start = convert_time(start)
    end = convert_time(end)
    all_time[start] += 1 # 시작과 끝 나눠서 count
    all_time[end] -= 1

for i in range(1, len(all_time)): # 구간 별 count
    all_time[i] = all_time[i] + all_time[i - 1]
```
4. 전체 구간의 누적 시청자 수 셈
5. 누적 구간 대비 시청자 수 제일 많은 곳 탐색
- 2번에서 나눴던 시작/끝 구간마다 광고 재생 시간을 고려해서, 해당 구간에 몇명의 시청자가 있는지 count
```python
most_view = 0
max_time = 0                          
for i in range(adv_time - 1, play_time): # 누적 대비 시청자수 max 찾기
    if i >= adv_time:
        if most_view < all_time[i] - all_time[i - adv_time]:
            most_view = all_time[i] - all_time[i - adv_time]
            max_time = i - adv_time + 1
    else:
        if most_view < all_time[i]:
            most_view = all_time[i]
            max_time = i - adv_time + 1
```

## Review
5주차 추석 트래픽과 비슷한 것 같았는데 알고보니 DP였다.. 처음에 투포인터로 접근했다가 시간초과 났다. DP 문제는 단련을 좀 해야겠다.