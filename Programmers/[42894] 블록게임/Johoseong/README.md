# [42894] 블록게임
## Algorithm
- 구현
## Logic
- 주어진대로 차근차근 구현만 하면 되는 문제.
1. board 탐색하면서 블록의 모양 찾음
2. 검정색 블록을 쌓을 위치 갱신
3. 터질 수 있는 블록 위치들 저장 -> 터뜨림
4. 제거한 블록 있으면 루프 반복
```python
    while updated:
        block_info = {}
        black = set()
        check = [False for _ in range(N)]
        for i in range(N):
            for j in range(N):
                typed = board[i][j]
                if typed != 0:
                    if typed in block_info:
                        block_info[typed][0].append((i,j))
                    else:
                        block_info[typed] = [[(i,j)],list()]
                    check[j] = True
                else:
                    if not check[j]:
                        black.add((i,j))
        for typed in block_info:
            block_info[typed][1] = is_bomb(block_info[typed][0])

        updated = False
        for typed in block_info:
            if can_del(block_info[typed][1],black):
                # 삭제 가능
                answer +=1
                updated = True
                for i,j in (block_info[typed][0] + block_info[typed][1]):
                    board[i][j] = 0
```

## Review
완전구현 문제였다. 생각보다 오래 걸렸음..