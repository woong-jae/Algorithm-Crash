# [81303] 표 편집
## Algorithm
- 연결리스트, stack
## Logic
1. 표의 행을 연결리스트로 표현 (각 노드는 [이전 노드, 다음 노드]). 가리키는 노드는 ```pointer```로 기록.
2. cmd 탐색하면서 연결리스트 연산함. 
3. ```U```, ```D``` 연산이면 for문 통해서 ```pointer```를 이동함
```python
if c[0] == 'U':
    for _ in range(int(c[2:])):
        pointer = linked_list[pointer][0]
elif c[0] == 'D':
    for _ in range(int(c[2:])):
        pointer = linked_list[pointer][1]
```
4. ```C``` 연산이면 ```pointer```가 가리키는 노드 삭제
- 복구 대비해서 ```stack```에 [해당 노드, 이전 노드, 다음 노드] 넣음
- ```pointer```가 마지막 행 가리키는 경우 예외 처리
```python
if next == n + 1:
    pointer = linked_list[pointer][0]
else:
    pointer = linked_list[pointer][1]
```
- 연결리스트 삭제 연산. 경우에 따라서 이전/이후 노드들이 가리키는 값에서 현재 노드 제외시키게 함
```python
if prev == 0: # 첫 노드 삭제 경우
    linked_list[next][0] = prev
elif next == n + 1: # 마지막 노드 삭제 경우
    linked_list[prev][1] = next
else: # 중간 노드 삭제 경우
    linked_list[prev][1] = next
    linked_list[next][0] = prev
```
5. ```Z```연산이면 ```stack```에서 pop()한 노드 다시 제자리에 넣음
- ```stack```에 [해당 노드, 이전 노드, 다음 노드] 넣어놨어서 바로 노드 넣어버릴 수 있음. O(1)
```python
if prev == 0:
    linked_list[next][0] = now
elif next == n + 1:
    linked_list[prev][1] = now
else:
    linked_list[prev][1] = now
    linked_list[next][0] = now
```
- 시간복잡도 O(N)

## Review
너무 쉬운거 아닌가 하면서 배열로 풀었다가 효율성 통과 못했다.. 질문하기 보고 풀었는데 솔직히 연결리스트로 풀 생각조차 못했다. </br>
배열 insert랑 pop 연산 반복되면 연결리스트 쓰는게 훨씬 빠르다는 거 배웠다. **연결리스트는 삽입/삭제 연산 O(1)**</br>
알고 나서 보니까 ```U, D 이동 합이 1,000,000 안 넘는다``` 등으로 연결리스트 캐치할 수 있는 것 같다.
