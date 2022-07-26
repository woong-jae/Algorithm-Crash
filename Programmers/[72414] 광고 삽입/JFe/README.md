# [72414] 광고 삽입 - Python

## 🔍 Algorithm
**Prefix Sum**

## 💻 Logic

```Python
# log 시간 변환
    for log in logs:
        l = log.split('-')
        for i, v in enumerate(l):
            tmp = v.split(':')
            l[i] = int(tmp[0]) * 3600 + int(tmp[1]) * 60 + int(tmp[2])
        time_list[l[0]] += 1
        time_list[l[1]] -= 1
```
- **log 시간 변환**  

```Python
    # 구간별 시청자 수 저장
    for i in range(1, len(time_list)):
        time_list[i] += time_list[i-1]
    # 누적 시청자 수 저장
    for i in range(1, len(time_list)):
        time_list[i] += time_list[i-1]
```
- **구간별 시청자 수 저장**  
- **누적 구간 합 구한 후 저장**  


```Python
    # 시청자 수 가장 많은 구간 체크
    most_view = time_list[adv_time-1]
    result = 0
    for i in range(adv_time, play_time):
        if most_view < time_list[i] - time_list[i - adv_time]:
            most_view = time_list[i] - time_list[i - adv_time]
            result = i - adv_time + 1
    # 결과 값 문자열 변환
    hour = '0' + str(result // 3600)
    min = '0' + str(result % 3600 // 60)
    sec = '0' + str(result % 60)
    return hour[-2:] + ':' + min[-2:] + ':' + sec[-2:]
```
- **시청자 수 가장 많은 구간 체크**  
- **결과 값은 문자열로 변환하여 반환**  


## 📝 Review

처음에는 Sliding Window가 생각나서 각 구간의 처음 시작 시간을 기준으로 해결을 하려고 했는데 누적 시간을 계산하는 부분을 어떻게 해야할지 몰라서 다른 코드를 참고했다..  
