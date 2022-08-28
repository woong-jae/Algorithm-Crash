# [12929] 올바른 괄호의 갯수
## Algorithm
- 재귀(DFS?)
## Logic
- 재귀로 왼쪽, 오른쪽 괄호 개수를 각각 n까지 더해가면서 가능한 경우를 구했음
1. 왼쪽 괄호가 n보다 작으면 왼쪽 괄호는 무조건 추가할 수 있음
- 오른쪽 괄호가 현재 왼쪽 괄호수보다 적으면 오른쪽 괄호도 추가 가능
2. 왼쪽 괄호가 n개 만족하면 오른쪽 괄호만 추가가능
3. 이렇게 재귀 돌다가 둘 다 n개를 만족하면 answer에 추가
```python
def count(l, r, n):
    global answer
    if l == n and r == n:
        answer += 1
        return

    if l < n:
        count(l + 1, r, n)
        if l > r:
            count(l, r + 1, n)
    else:
        count(l, r + 1, n)
```

## Review
쉬웠다. 근데 다른 풀이보니까 카탈란 수로 푸는거 보고 놀람 ㅎ..