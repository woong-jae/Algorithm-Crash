# [64063] 호텔 방 배정
## Algorithm
- union-find
## Logic
### 처음 풀이
- 연결리스트 사용했음. 배정된 방(노드)를 삭제하면 각 노드의 next 노드가 자동으로 오른쪽에서 가장 작은 수의 방 됨
- 방 배정 연산(= 노드 삭제 연산)이 O(1) 되니까 이렇게 했음
- k 값이 10^12 으로 너무 커서, 애초에 방 배열을 만들고 들어가니까 효율성 통과 못함..
### 바꾼 풀이
- 배열 사용하면 시간초과 되므로 빈 dict()를 사용해야함
- 배정된 방 번호가 연속되면 하나의 트리로 생각함. 트리에서 가장 큰 번호의 방의 next 방을 root로 생각하면 union-find로 방 찾는 연산의 시간복잡도를 줄일 수 있음!
1. 배정된 방을 dict() 으로 선언만 해둠
2. num에 대해 배정될 방 찾음
- 방 num이 배정 안됐으면 num 바로 배정. 그리고 dict()에 num을 value값 num + 1로 추가 (오른쪽에서 제일 작은 방 번호 가리키도록) 
- 방 num이 배정 된 상태면 num과 연결된 방 중 가장 큰 방이 가리키는 번호(root) 배정. 연결된 방들 값은 root + 1로 갱신
```python
def union_find(num):
    if num not in rooms: # 배정 안해준 방 발견
        rooms[num] = num + 1 
        answer.append(num)
        return num + 1

    rooms[num] = union_find(rooms[num]) # 루트 방번호로 갱신
    return rooms[num]
```
- 즉 빈 dict() 사용하는 union-find로 접근하면 효율성 통과 됨
- 시간복잡도 O(N*logN)

## Review
배열로 구현할 생각만 했어서 아쉽다. 그래도 다음번에 이렇게 범위 큰 문제 보면 어떻게 접근해야할지 배웠다.
