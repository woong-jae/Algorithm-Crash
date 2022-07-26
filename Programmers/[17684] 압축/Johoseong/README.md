# [17684] 압축
## Algorithm
- two pointer
## Logic
- 주어진 문장을 s, e 두 포인터를 사용해서 슬라이싱 하며 사전에 있는지 확인했음. 일치하는 가장 긴 문자열의 인덱스를 출력해야하므로, e를 이동하면서 슬라이싱하면 해결할 수 있음.
1. 알파벳 26개 포함하는 사전 만듦
2. s, e two pointer를 이용해서 문자열을 탐색함
```python
while True:
    cur = msg[s:e]

    if e > N: # 종료조건
        answer.append(dic[prev])
        break

    if cur in dic: # 사전에 있음
        prev = cur
        e += 1
        continue
    else: #사전에 없음
        answer.append(dic[prev])
        index += 1
        dic[cur] = index
        s = e - 1
```
- s, e로 문자열 슬라이싱해서 cur 변수에 담음
- cur이 사전에 있으면 e를 늘리고 재탐색 (사전 내 가장 긴 문자열 찾아야되므로)
- cur이 사전에 없으면 prev까지만 사전에 있다는 뜻이므로, prev의 인덱스를 answer에 넣음. cur는 사전에 추가

## Review
처음에 그냥 구현인 줄 알았는데 풀다보니 투포인터 느낌이라 중간에 노선 바꿨다. 무난했는데 처음에 길을 잘못 잡아서 시간 좀 걸렸음..