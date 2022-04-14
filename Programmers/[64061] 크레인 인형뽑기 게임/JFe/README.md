# [64061] 크레인 인형뽑기 게임 - Python

## 🔍 Algorithm
**Stack**

## 💻 Logic

```Python
for i in moves:     # moves 배열 반복
    for j in range(len(board[0])):
        if board[j][i-1] == 0:      # 0이 아닌 값 나올 때까지 반복, continue 이용해서 깊이 깊어지지 않도록
            continue
        if bucket and bucket[-1] == board[j][i-1]:  # stack의 top과 값이 같으면 append 하지 않고 pop한 뒤, answer 값 +2 증가
            board[j][i-1] = 0
            bucket.pop()
            answer += 2
            break
        # top과 값이 다르면 append
        bucket.append(board[j][i-1])
        board[j][i-1] = 0
        break
```
- `if board[j][i-1] == 0:`  
    0이 아닌 값 나올 때까지 반복  
    0이 아닐 때로 하게 되면 코드 깊이가 깊어져서 continue를 이용해서 깔끔하게 만들었다.  
- `if bucket and bucket[-1] == board[j][i-1]:`  
    stack의 top과 값이 같으면 append 하지 않고, top 값을 pop  
    stack의 top은 `bucket[-1]`처럼 접근 가능  
    `bucket.pop()` 부분은 `bucket = bucket[:-1]`처럼 슬라이싱으로도 구현 가능하다.  

## 📝 Review

문제 해결 방법 자체는 빨리 찾아내서 어떻게 하면 코드를 더 예쁘게, 빠르게 돌아가도록 할 수 있을지에 대해 고민했다.
확실히 파이썬이 다른 언어에 비해 실행 시간은 많이 걸리는 듯,,
