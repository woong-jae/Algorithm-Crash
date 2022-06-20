# [60059] 자물쇠와 열쇠
## Algorithm
- 구현?
## Logic
- 회전이 가능하므로, 자물쇠 배열을 * 3으로 늘리고 전체를 탐색하면 됨.
1. 자물쇠를 중간에 두고 전체 배열을 3배 늘림
2. 4회전을 하면서 되는지 체크
- 회전/이동한 경우마다 자물쇠 영역이 전부 1 되는지 확임함. 1되면 열림
```python
def check(key_arr, lock_arr, x, y):
    key_size = len(key_arr)
    lock_size = len(lock_arr)
    
    boardSize = lock_size * 3 
    board = [[0]*(boardSize) for _ in range(boardSize)]
    
    start = lock_size -1
    end = start + lock_size
    
    for i in range(lock_size):
        for j in range(lock_size):
            board[start+i][start+j] += lock_arr[i][j]        
    
    for i in range(key_size):
        for j in range(key_size):
            board[i+x][j+y] += key_arr[i][j] # 값 복사
    
    for i in range(start, end):
        for j in range(start, end):
            if board[i][j] != 1 :
                return False
    return True
```

## Review
생각보다 복잡하게 풀었다.. 결국 다른 코드 좀 참고함 