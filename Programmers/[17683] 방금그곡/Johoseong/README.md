# [17683] 방금그곡
## Algorithm
- 문자열 
## Logic
- 재생된 시간만큼 멜로디를 이어붙여서, 그 멜로디 내에 m 포함되는 것 찾으면 됨
1. ```#``` 음은 구별 위해서 다른 알파벳으로 변경
2. musicinfos의 노래 정보 탐색
```python
time = (int(tmp2[0]) * 60 + int(tmp2[1])) - (int(tmp1[0]) * 60 + int(tmp1[1]))
full_melody = (time // l) * melody + melody[:(time % l) + 1] # 재생된 시간만큼 멜로디 연결

if (m in full_melody) and (t < time):
    answer = title
    t = time
```
- 끝난시간에 시작시간을 빼서 총 재생된 시간을 구함
- 그 시간만큼 멜로디를 이어붙임
- 이어붙인 멜로디에 m이 포함되면 answer에 저장 (찾은 곡이 또 있다면, 재생된 시간이 더 긴 곡으로 갱신)

## Review
무난했다. 처음에 #붙은 음 구별 안해줬다가 테스트케이스 3번 통과못했다. replace 반복하기 싫었는데 다른 대안 못찾아서 그냥 이렇게 풀었음..