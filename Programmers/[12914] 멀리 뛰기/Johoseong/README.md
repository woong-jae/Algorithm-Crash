# [12914] 멀리 뛰기
## Algorithm
- DP
## Logic
### 처음 풀이
```python
from itertools import permutations
def solution(n):
    answer = 0
    jumps = [1 for _ in range(n)]
    L = n

    while True:
        permut = list(set(permutations(jumps)))
        answer += len(permut)

        if L <= 1:
            break

        a = jumps.pop(0)
        b = jumps.pop(0)
        if a >= 2 or b >= 2:
            break
        jumps.append(2)
        L -= 1
    return answer % 1234567
print(solution(2))
```
- permutation을 썼는데 두개만 통과하고 다 시간초과 났음..

### 바뀐 풀이
```python
for i in range(2, n + 1):
    jumps[i] = jumps[i - 1] + jumps[i - 2]
```
- 차근차근 나눠서 생각해보면, 1 또는 2칸을 뛸 수 있으므로 칸이 1개면 경우의 수 하나, 칸이 2개면 1 + 1 혹은 2칸을 뛰어서 경우의 수가 두개임.
- 그럼 3칸의 경우엔 "1칸까지의 상태에서 2칸을 더 뛰는 경우" + "2칸까지의 상태에서 1칸을 더 뛰는 경우"를 합한 경우의 수가 생김.
- 즉, DP를 사용해서 풀 수 있음. (n칸의 경우, n-1칸의 경우에서 한칸 뛰기 + n-2칸의 경우에서 두칸 뛰기)

## Review
처음 문제를 봤을 때, 그 전에 사용한 값을 이용할 수 있겠다는 생각을 하긴 했다. 그런데 n의 범위가 별로 안 큰 것 같아서 permutation 썼는데 어림도 없었다 ㅎㅎ 그래도 레벨3치곤 쉬운 것 같다