# [12899] 124 나라의 숫자
## Algorithm
- 구현
## Logic
- 1 2 4 세 개 숫자를 쓰기 때문에 3진법으로 이해한다.
-  3으로 나눠 주고 나누어떨어지면 몫에서 1을 빼고 4를 넣어 준다.
```python
def solution(n):
    answer = ""
    res = n
    while res > 0:
        if res % 3 != 0:
            answer += str(res % 3)
        if res % 3 == 0:
            res = res//3 -1
            answer+="4"
        else:
            res = res // 3
            
    return answer[::-1]
```

## Review
생각보다 시간이 오래 걸렸다. 1을 빼 줄 타이밍을 머릿속에서 계산하려고 하니까 힘들어서 써서 하나씩 했다.
