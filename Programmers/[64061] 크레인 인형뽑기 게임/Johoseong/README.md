# [64061] 크레인 인형뽑기 게임
## Algorithm
- stack
## Logic
- 바구니는 stack으로 구현
- 주어진 열을 탐색해서 0이 아닌 최상단 값이 있으면 스택 최상단 값과 비교함
```python
if board[row][col - 1] != 0:
    if stack[-1] == board[row][col - 1]:
        answer += 2
        stack.pop()
    else:
        stack.append(board[row][col - 1])
    board[row][col - 1] = 0
    break
```
- 스택 최상단 값이 board 값과 동일하면 answer += 2하고 stack pop
- moves에 따른 열과, 해당 열에서 0이 아닌 최상단 값 찾을 때 이중 for문 사용됨. 시간복잡도 O(N^2)

## Review
바로 stack 떠오르는 쉬운 문제였다. 시간 더 줄일 수 있는 방법이 있는지는 생각해 봐야할 거 같음.
