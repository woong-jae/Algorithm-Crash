def rotation(arr):
    ret_arr = [[0] * len(arr)  for _ in range(len(arr))]

    for i in range(len(arr)):
        for j in range(len(arr)):
            ret_arr[j][len(arr) - 1 - i] = arr[i][j]
    return ret_arr

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

def solution(key, lock):
    answer = False
    N = len(lock)
    for _ in range(4):
        for x in range((N * 2) - 1):
            for y in range((N * 2) - 1):
                if check(key, lock, x, y) == True :
                    answer = True
        key = rotation(key)
        
    return answer