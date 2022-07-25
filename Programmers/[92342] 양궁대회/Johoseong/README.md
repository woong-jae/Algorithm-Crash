# [92342] 양궁대회
## Algorithm
- 구현, Brute-Force
## Logic
- ```combination```으로 라이언이 이길 과녁 조합을 모두 만든 후, 해당 조합마다 화살 분배+어피치 점수와 비교를 통해서 제일 점수차가 큰 경우를 찾으면 된다!
1. 10C1 부터 10C10까지 라이언이 이길 과녁 조합을 모두 만듦
```python
for i in range(10, 0, -1):
    wins[i] = list(combinations(scores, i))
```
2. 만들어진 과녁 조합마다, (1) 라이언이 이길 수 있게 화살 분배 (2) 라이언/어피치 점수 계산을 함
- (1) 라이언이 이겨야하는 과녁이면, ```어피치가 해당 과녁에 쏜 화살 수 + 1``` 만큼만 맞추면 됨
```python
for case in wins[i]:
    remain = n # 라이언 남은 화살 수
    ryan_info = [0 for _ in range(10, -1, -1)]
    ryan_score = 0
    apeach_score = 0
    for j, a_num in enumerate(info):
        if j == 10:
            if remain > 0:
                ryan_info[j] = remain
            break
        cur = scores[j]
        if cur in case: # 라이언이 더 맞춤
            remain -= (a_num + 1)
            ryan_score += cur
            ryan_info[j] = a_num + 1
        elif a_num != 0: # 어피치가 더 맞춤
            apeach_score += cur
```
3. 이렇게해서 남은 화살이 음수면 라이언이 해당 조합을 쏠 수 없다는 뜻 OR 어피치 총 점수가 더 높으면 그냥 [-1]임
4. 둘의 점수차가 더 크거나, 점수차 같으면 낮은 점수 맞춘 case가 답이 되도록 함
```python
    if remain < 0 or apeach_score > ryan_score: # 3.
        continue
    
    sub = ryan_score - apeach_score
    if MAX < sub: # 4.
        answer = ryan_info
        MAX = sub
    elif MAX == sub: # 4.
        for k in range(10, -1, -1):
            if answer[k] == ryan_info[k]: continue
            elif answer[k] > ryan_info[k]: break
            else:
                answer = ryan_info
                MAX = sub
                break
```

## Review
레벨2인데 조건이 상세해서 그런지 생각보다 시간 좀 걸렸음. 그리고 처음에 23번 테케만 통과 못했었는데 라이언-어피치 비긴 경우면 [-1]였다. 문제 잘 읽기!