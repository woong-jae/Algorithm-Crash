# [92344] 파괴되지 않은 건물
## Algorithm
- Prefix Sum
## Logic
- Brute-Force로 생각했을 때, 스킬확인을 위한 250000 * 배열에 해당 스킬 영향주기위한 1000000의 시간복잡도가 나온다. 스킬 확인을 위한 시간복잡도는 건들 수 없으니 배열 접근의 시간복잡도를 줄여야한다.
- 여기서 누적합 연산을 이용하면, 스킬 확인 1번에 매번 1000000번의 배열 접근을 할 필요가 없어진다. 그냥 스킬마다 누적합 배열에 체크를 해주고 마지막에 합 연산 한번만 해주면 되므로 시간복잡도가 엄청나게 줄어든다!
1. ```skill```을 확인하면서, ```sum_attack``` 배열에 누적합 연산을 위한 변화량 표시를 해둠
```python
for t, r1, c1, r2, c2, degree in skill:
    amount = degree
    if t == 1: amount *= (-1) # 공격이면 음수
    
    sum_attack[r1][c1] += amount
    sum_attack[r2 + 1][c2 + 1] += amount
    sum_attack[r1][c2 + 1] += (amount * -1)
    sum_attack[r2 + 1][c1] += (amount * -1)
```
2. 체크해둔 ```sum_attack``` 배열을 가로 세로로 누적합 연산 해줌
```python
for r in range(R): # 누적합 연산 (가로)
    for c in range(C):
        sum_attack[r][c + 1] += sum_attack[r][c]
for r in range(R): # 누적합 연산 (세로)
    for c in range(C):
        sum_attack[r + 1][c] += sum_attack[r][c]
```
3. ```skill```에 대한 누적 변화량을 모두 구했으므로, 처음 배열과 합해서 0보다 크면 count + 1 하면 됨
```python
for r in range(R):
    for c in range(C):
        if board[r][c] + sum_attack[r][c] > 0:
            answer += 1
```

## Review
누적합 알고리즘은 알고있었는데 이걸 써먹을 줄은 몰랐다 ㅎㅎ.. 이진트리로 해야하나 DP로 해야하나 했는데 다 틀렸다. 카카오식 효율성 문제 접근이 너무 어렵운 것 같다. 그래도 도움은 되네요..