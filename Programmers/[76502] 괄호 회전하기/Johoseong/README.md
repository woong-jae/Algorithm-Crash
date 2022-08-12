# [76502] 괄호 회전하기
## Algorithm
- Stack
## Logic
1. ```s```를 왼쪽으로 한칸씩 이동한 경우 모두에 대해서 괄호가 올바른지 체크해야함
2. 괄호가 올바른지 체크하기 위해선 stack을 사용해야함
- 회전시킨 문자열에서 차례대로 한 괄호 씩 뽑아내서 stack에 넣음
- stack에 들어온 괄호로 인해 stack 상단의 괄호 조합이 (), {}, []가 된다면 해당 괄호 조합 pop -> 반복
- 마지막에 stack이 비었으면 모두 올바른 괄호라는 뜻임
```python
for _ in range(N):
    stack = ''
    cur = copy.deepcopy(new)
    while cur:
        stack += cur[0]
        cur = cur[1:]
        if stack[-2:] == '[]' or stack[-2:] == '()' or stack[-2:] == '{}':
            stack = stack[:-2]
            continue
    if not stack:
        answer += 1

    new = new + new[0]
    new = new[1:]
```

## Review
쉬운 stack 문제였다.