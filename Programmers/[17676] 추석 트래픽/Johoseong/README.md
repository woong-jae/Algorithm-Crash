# [17679] 프렌즈4블록
## Algorithm
- 완전탐색?
## Logic
- 각 로그의 end 기준으로 1초 이내에 시작된 로그 개수를 구하면 쉽게 구할 수 있음!
1. 로그의 start 시간을 구하고, ```time``` 배열에 로그들의 [start, end] 시간 저장. (기준이 1초라서 초단위로 바꿔줌)
```python
for log in lines:
    l = log.split(' ')
    S = l[1].split(':')
    T = l[2][0:-1]
    end = Decimal(int(S[0]) * 3600 + int(S[1]) * 60) + Decimal(S[2])

    start = Decimal(end) - Decimal(T) + Decimal('0.001')
    time.append([start, end])
```
2. 각 로그들의 end 기준으로 1초동안 start된 로그 개수를 구하고 저장.
- 로그 시간이 겹침 or 겹치진 않지만 1초내에 시작된 로그 -> count+1
- 1초 이내에 시작 안됐으면 넘어감
```python
for i in range(N - 1):
    count = 1
    cur_e = time[i][1]
    range_e = cur_e + Decimal('0.999') # 현재 로그의 끝난 시간
    for j in range(i + 1, N): # 그 다음 로그들 중에서 겹치는거 확인함
        next_s = time[j][0]

        if cur_e >= next_s or range_e >= next_s: # 아예 겹침 or 1초 이내
            count += 1
    answer.append(count)
```
3. 그중 최대값이 answer.
- 시간복잡도 O(N^2)

## Review
문제 이해하는게 좀 오래 걸림. 구현은 금방 생각해낼 수 있었다! 