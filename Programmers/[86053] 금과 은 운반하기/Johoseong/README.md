# [86053] 금과 은 운반하기
## Algorithm
- Binary Search
## Logic
- 최소 시간을 구하는 최적화 문제인데, 이를 시간에 대한 결정 문제로 바꾸는게 핵심임
1. 시간을 기준으로 Binary Search를 진행
2. 현재 시간에서, i 도시의 트럭 이동 최대 횟수를 구함 (나머지 연산으로 마지막 편도 이동도 꼭 체크)
3. 2번에서 구한 트럭 이동 횟수를 통해서 i 도시들 최대 금/은/전체 운송 가능 무게를 체크
- i 도시 기준으로 ```w[i] * (트럭 이동 최대 횟수)``` = ```현재 시간에서 i 도시의 최대 운송 가능 무게```
4. 현재 시간에서 모든 도시들의 2, 3번을 체크하면 금/은/전체 운송 최대 무게를 알 수 있게됨
- if (누적 금 >= a, 누적 은 >= b, total >= a+b)면 현재 시간으로 운송이 가능함 -> 시간 줄여서 재탐색
- else -> 현재 시간으론 충분한 금/은을 운송 못함 -> 시간 늘려서 재탐색
```python
while start <= end: # 1.
    cur_time = (start + end) // 2
    gold, silver, total = 0, 0, 0

    for i in range(N):
        max_move_cnt = cur_time // (t[i] * 2) # 2.
        if cur_time % (t[i] * 2) >= t[i]:
            max_move_cnt += 1

        if w[i] * max_move_cnt > g[i]: # 3. - 금
            gold += g[i]
        else:
            gold += (w[i] * max_move_cnt)
        if w[i] * max_move_cnt > s[i]: # 은
            silver += s[i]
        else:
            silver += (w[i] * max_move_cnt)
        if w[i] * max_move_cnt > s[i] + g[i]: # total
            total += (s[i] + g[i])
        else:
            total += (w[i] * max_move_cnt)

    if total >= (a + b) and gold >= a and silver >= b: # 4.
        end = cur_time - 1
        answer = min(answer, cur_time)
    else:
        start = cur_time + 1

```

## Review
그리디로 접근했다고 못풀어서 풀이보고 겨우 풀었다. 이분탐색.. 결정 문제로 바꿔 사고하는게 너무 어렵다; 이분탐색만 집중적으로 좀 해야겠다..