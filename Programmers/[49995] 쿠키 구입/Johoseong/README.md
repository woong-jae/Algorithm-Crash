# [49995] 쿠키 구입
## Algorithm
- Two pointer
## Logic
- 인덱스를 기준으로, 포인터를 이용해서 왼쪽은 형/오른쪽은 동생의 몫으로 잡음
- 합을 구해나가다가 둘의 합이 같은 것 중 최대값을 구하면 됨
1. 인덱스를 기준으로 형/동생의 포인터를 정함
2. 만약 형의 합이 더 작으면, 형의 몫을 왼쪽으로 하나 더 늘려서 합함
3. 반대로 동생의 합이 더 작으면, 동생의 몫을 오른쪽으로 하나 더 늘려서 합함
4. 같으면 answer가 최대값이 되도록 함
```python
for i in range(N - 1):
    old, young = i, i + 1
    old_sum, young_sum = cookie[i], cookie[i + 1]

    while True:
        if old_sum == young_sum:
            answer = max(answer, old_sum)

        if old_sum <= young_sum:
            if old <= 0: break
            old -= 1
            old_sum += cookie[old]
        else:
            if young >= N - 1: break
            young += 1
            young_sum += cookie[young]
```

## Review
투포인터 전형적인 문제인듯함. 근데 인덱스 범위때매 좀 삽질했다ㅎㅎ ㅠ