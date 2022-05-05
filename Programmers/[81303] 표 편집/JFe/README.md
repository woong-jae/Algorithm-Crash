# [81303] 표 편집 - Python

## 🔍 Algorithm
**Linked List**

## 💻 Logic

```Python
# 연결리스트 형태의 dictionary 생성 (첫 행과 마지막 행 연결 부분은 None)
table = {i: [i-1, i+1] for i in range(1, n-1)}
table[0] = [None, 1]
table[n-1] = [n-2, None]
```
- **연결리스트 생성**  
    `[prev, next]` 연결리스트 형태의 **dictionary** 생성  
    첫 행과 마지막 행 연결 부분은 **None**으로 따로 저장  

```Python
if c[0] == 'D':
  for _ in range(int(c[2:])):
    current = table[current][1]
elif c[0] == 'U':
  for _ in range(int(c[2:])):
    current = table[current][0]
```
- **명령어가 'D' or 'U'인 경우**  
    `c`가 문자열이기 때문에 이동거리는 **slice**를 통해 구하고,  
    그 이동거리만큼 반복문으로 연결리스트를 이동한다.  

```Python
elif c[0] == 'C':
  answer[current] = 'X'
  prev, next = table[current]
  removed.append((table[current], current))   # 삭제된 값과 위치 removed에 append
  # 첫 행, 마지막 행 삭제하는 경우 나눠서 처리
    if prev == None:
      table[next][0] = None
      current = next
    elif next == None:
      table[prev][1] = None
      current = prev
    else:
      table[prev][1] = next
      table[next][0] = prev
      current = next
```
- **명령어가 'C'인 경우**  
    해당 위치에 해당하는 `answer` 값을 **X**로 바꾸고, 삭제할 값과 위치를 `removed` 리스트에 **append**  
    연결리스트 연결 상태는 첫 행, 마지막 행 삭제하는 경우와 나머지 경우로 나눠서 처리
    
```Python
elif c[0] == 'Z':
  (prev, next), i = removed.pop()
  answer[i] = 'O'
  # 첫 행, 마지막 행 복구하는 경우 나눠서 처리
  if prev == None:
    table[next][0] = i
  elif next == None:
    table[prev][1] = i
  else:
    table[prev][1] = i
    table[next][0] = i
```
- **명령어가 'Z'인 경우**  
    `removed` 리스트에서 `prev, next, i`를 **pop**하고, `i`에 해당하는 `answer` 값을 **O**으로 바꿔준다.  
    복구하는 경우도 삭제와 마찬가지로 첫 행, 마지막 행 복구하는 경우와 나머지 경우로 나눠서 처리  


## 📝 Review

처음에는 스택을 이용해서 간단하게 구현했지만 역시나 효율성에서 통과하지 못했다.  
도저히 방향을 못잡겠어서 질문하기를 참고했고, 연결리스트로 풀어야 한다는 팁을 얻고 연결리스트로 풀었다.  
연결리스트로 풀어야 된다는 것을 알고 나서도 이것저것 생각보다 시간이 걸렸고, 전체적으로 시간이 오래 걸렸던 문제,,,  
다양한 알고리즘 문제들을 풀어야 할 필요성을 느낌  
