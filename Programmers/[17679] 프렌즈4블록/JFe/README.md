# [17679] 프렌즈4블록 - Python

## 🔍 Algorithm
**구현**

## 💻 Logic

```Python
    # 블록 확인
        for i in range(m-1):
            for j in range(n-1):
                # 2X2 형태면 temp에 append
                if board[i][j] != 0 and board[i][j] == board[i+1][j] == board[i][j+1] == board[i+1][j+1]:
                    temp.append((i, j))
                    play = True
```
- **블록 확인**  
    2X2 형태면 temp에 append  

```Python
    # 블록 지우기
        for i, j in temp:
            board[i][j] = 0
            board[i+1][j] = 0
            board[i][j+1] = 0
            board[i+1][j+1] = 0
```
- **블록 지우기**  


```Python
    # 빈 공간 채우기
        for i in range(m-1):
            for j in range(n):
                # 밑에 빈 공간 있으면 내리기
                if board[i][j] != 0 and board[i+1][j] == 0:
                    board[i+1][j], board[i][j] = board[i][j], board[i+1][j]
                    # 해당 블록 위에 블록들도 내려주기
                    for k in range(i-1, 0, -1):
                        if board[k][j] != 0:
                            board[k][j], board[k+1][j] = board[k+1][j], board[k][j]
```
- **빈 공간 채우기**  
    밑에 빈 공간이 있는지 확인한 다음 블록을 내려주고,  
    해당 블록 위에 블록들도 확인해서 내려준다.  


## 📝 Review

블록 확인해주고, 지우고, 빈 공간 채우는 과정을 순서대로 구현하면 되는 문제다.  
블록 내려주는 과정에서 한 번 내리고, 그 다음에 해당 블록 위에 블록들도 내려줘야 하는 점을 잊어버려서 이 부분에서 살짝 당황했었다,,  
