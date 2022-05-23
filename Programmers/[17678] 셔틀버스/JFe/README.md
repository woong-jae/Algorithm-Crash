# [17678] 셔틀버스 - Python

## 🔍 Algorithm
**Sort**

## 💻 Logic

```Python
# 시간 분 단위로 변환 후, 내림차순 정렬
    for i, time in enumerate(timetable):
        timetable[i] = int(time[:2]) * 60 + int(time[3:])
    timetable.sort(reverse=True)
```
- **시간 분 단위로 변환 후, 내림차순 정렬**  

```Python
# 시간대별 셔틀 확인
    for i in range(n):
        full = True
        for j in range(m):
            # 셔틀에 탈 수 없으면, full을 False로 바꾸고 break
            if len(timetable) == 0 or timetable[-1] > cur_time:
                full = False
                break
            last_time = timetable.pop()
        cur_time += t
    if full: last_time -= 1 # 마지막 셔틀이 가득 차있으면, 제일 뒷 사람 시간 - 1
    else: last_time = cur_time - t  # 가득 차있지 않으면, 현재 셔틀 시간
```
- **셔틀에 탈 수 없는 경우**  
    `timetable` 리스트가 비어있거나 `timetable`의 마지막 값이 `cur_time`보다 큰 경우에는  
    `full`을 **False**로 바꾸고 **break**  
- **정답 시간 계산**  
    마지막 셔틀이 가득 차 있으면, **제일 뒷 사람 시간 - 1**  
    가득 차 있지 않으면, **현재 셔틀 시간**  


## 📝 Review

처음에 문제를 바로 이해하지 못해서 문제 이해하는데 시간을 보낸 것 같다...  
문제 이해하고 나서 구현하기까지는 그렇게 오래 걸리지는 않았다. 조금 더 깔끔하게 코드를 바꿀 수 있을까  
