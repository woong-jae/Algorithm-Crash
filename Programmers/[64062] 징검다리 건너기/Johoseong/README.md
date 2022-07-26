# [64062] 징검다리 건너기
## Algorithm
- binary search
## Logic
- 스톤을 n명이 밟으면 스톤 숫자는 ```(스톤 숫자) - n```가 됨. 이 때 ```0 이하 스톤의 연속된 개수```가 k 이상이면 건널 수 없는 경우 됨.
- 스톤에 적힌 숫자를 이분탐색으로 탐색하면서 해당 숫자 다 밟았을 때 ```0 이하 스톤의 연속된 개수```를 구함.
```python
while start <= end:
    mid = (start + end) // 2

    count = 0
    for s in stones: # 연속된 0인 돌 개수 셈
        if s - mid <= 0:
            count += 1
        else:
            count = 0

        if k <= count:
            break

    if k <= count: # 못 밟는 돌 개수가 k보다 큼 (징검다리 못 건너는 경우)
        end = mid - 1
        answer = min(answer, mid)
    else: # 징검다리 건널 수 있는 경우
        start = mid + 1
```
1. ```0 이하 스톤의 연속된 개수```가 k 이상이면 못 건넘. 이미 더 적은 인원이 밟았을 때 못 건너는 경우될 수 있으니까, 스톤 숫자 지금보다 작게하고 탐색.
2. ```0 이하 스톤의 연속된 개수```가 k 이하면 더 건널 수 있음. 스톤 숫자 더 키우고 탐색.
- 시간복잡도 O(N*logN)

## Review
stones 원소 값이 2000,000,000까지로 엄청 커서 이분탐색 쓰니까 쉽게 풀 수 있었다!
