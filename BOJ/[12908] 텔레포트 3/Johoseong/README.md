# [12908] 텔레포트 3
## Algorithm
- DFS, Brute Force
## Logic
- 일단 범위가 매우 커서 BFS는 아니고, 시간을 기준으로 한 이진탐색도 고려는 해봤는데 불가능할 듯
- 다행히 텔레포트의 개수가 3개로 제한돼있어서 ```텔레포트 순서 순열 & 각 텔레포트 내에서 이용 순서``` 경우의 수 구해보면 100개도 안됨
1. ```순열```로 사용할 텔레포트의 순서를 구함. (텔레포트를 사용안할 수도 있으므로, 0개/1개/2개/3개 사용 경우를 나눠서 순열 구해야함)
```python
for i in range(1, 4): # 텔레포트 이용하는 경우의 수
    permuts.append(list(permutations([0, 1, 2], i)))
```
2. 각 경우에 대해 걸리는 시간을 DFS로 구함. DFS를 하는 이유는, 한 텔레포트 안에서 또 들어오고->나오고 이용 순서를 2개로 나눌 수 있어서, 이를 편하게 계산하려고 DFS를 사용함
- 텔레포트를 이용하는 경우엔 거리와 상관없이 10초임에 유의
```python
def calculate(ports, permut, index, num, dist):
    global answer, rs, cs, re, ce
    if index == len(permut) -1: # 마지막 텔레포트면
        answer = min(answer, dist + distance(ports[permut[index]][num], [re, ce]))
        return

    if index == -1: # start -> port면
        d1 = distance([rs, cs], ports[permut[0]][0])
        d2 = distance([rs, cs], ports[permut[0]][1])
        calculate(ports, permut, 0, 1, dist + d1 + 10)
        calculate(ports, permut, 0, 0, dist + d2 + 10)
    else: # port -> port
        d1 = distance(ports[permut[index]][num], ports[permut[index + 1]][0])
        d2 = distance(ports[permut[index]][num], ports[permut[index + 1]][1])
        calculate(ports, permut, index + 1, 1, dist + d1 + 10)
        calculate(ports, permut, index + 1, 0, dist + d2 + 10)
```
3. 걸리는 시간이 가장 작은 경우가 답임

## Review
이진탐색이려나 했다가, 텔레포트 개수가 3개 제한이길래 빠르게 풀 수 있었다.
