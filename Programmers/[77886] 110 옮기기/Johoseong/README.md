# [77886] 110 옮기기
## Algorithm
- Greedy, Stack
## Logic
### 처음 풀이
1. ```110``` 분리는 알고리즘 자체는 현재 풀이와 동일하게 stack을 사용하여 O(N)으로 품
2. 그러나 ```list```를 이용해서 ['1', '1', '0']을 비교해서 3배의 정확히는 O(3*N)
3. 4, 5번과 마지막 3개에서 시간초과..
### 현재 풀이
1. 반복되는 ```110```을 분리해내야함. -> stack 사용
```python
stack = ''
cnt = 0
for t in tmp: # 110 없애기
    stack += t
    if stack[-3:] == '110':
        stack = stack[:-3]
        cnt += 1
```
2. 이제 뽑아낸 ```110```을 적절한 위치에 넣어줘야함
- stack에 남아있는 ```110```을 제외한 문자열을 뒤에서부터 탐색
- 0을 만나면 break. **(이유) 0을 만나면, 110을 해당 0보다 앞에 위치시켰을 떄 어떤 경우에서도 이득이 없음. 그러므로 처음으로 만난 0의 바로 뒤에 110을 배치시켜야함**
```python
while stack: # 0 만나면 break (더 이상 앞에 110 넣어서 이득 없음)
    t = stack.pop()
    if t == '0':
        stack.append('0')
        break
    que.append(t)
```
- 110을 뽑아낸 개수만큼 배치시키고, stack에서 0을 만나기 전까지 뽑아낸 1들은 맨 뒤에 이어붙임
```python
while cnt: # 110붙이고 나머지 1들 붙임
    cnt -= 1
    stack.append('1')
    stack.append('1')
    stack.append('0')
stack += que
```

## Review
ㅋㅋ... list 비교와 문자열 비교가 이렇게 차이날 줄 몰랐다.. 2번 케이스는 6000대-> 300대까지 줄었다ㅜ 반성..