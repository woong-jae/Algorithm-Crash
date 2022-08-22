# [2800] 괄호 제거
## Algorithm
- Stack, 조합
## Logic
1. 먼저 ```stack```을 이용해서 괄호들 짝을 짓고, 해당 짝의 인덱스를 기억해둠 (나중에 제거해야하니까)
```python
for i, a in enumerate(arr):
    if a == '(':
        stack.append(i)
    if a == ')':
        index.append([stack.pop(), i])
```
2. 이렇게 기억해둔 괄호 쌍들을 어떤 조합을 제거할지 경우의 수를 구함
```python
for i in range(1, len(index) + 1):
    combination.extend(list(combinations(index, i)))
```
3. 조합 경우의 수에 맞춰서 괄호 쌍을 제거
```python
for case in combination:
    tmp = list(arr)
    for i in case:
        for j in i:
            tmp[j] = ''
    result.add(''.join(tmp).rstrip())
```

## Review
지난주차들에서 많이 풀어본 유형의 stack 문제였다.