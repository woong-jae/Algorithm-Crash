# [12973] 짝지어 제거하기
## Algorithm
- stack, queue
## Logic
1. ```stack```에 문자열을 담아 pop 해나가면서 탐색
2. 빠진 알파벳은 ```queue```에 넣으면서, ```스택에서 빠진 알파벳 == 큐의 첫번째 알파벳```이면 두개 다 제거
3. 이를 반복하면, 스택 문자열을 한번 탐색하는 것만으로도 연속 중복이 발생하는 경우를 또 다른 반복문 없이 해결 가능
```python
stack = deque(stack)
que = deque()

while stack:
    last = stack.pop()
    if que and que[0] == last:
        que.popleft()
        continue
    que.insert(0, last)
```

## Review
백준-문자열폭발과 비슷해서 빨리 풀긴했는데 효율성 통과 못해서 당황함; 삽질하다가 혹시나해서 list->deque로 바꾸니까 바로 통과.. 하 디큐쓰니까 시간이 1/20정도 줄어들었다.. 디큐쓰기 생활하하자 진짜;