# [17676] 추석 트래픽 - Python

## 🔍 Algorithm
**Greedy**

## 💻 Logic

```Python
    for l in lines:
        l = l.split(' ')    # 로그 split
        time = l[1].split(':')  # 로그 시간 부분 split
        last_time = Decimal(time[0])*3600 + Decimal(time[1])*60 + Decimal(time[2])  # 응답 완료 시간 초 단위로 변환
        start_time = last_time - Decimal(l[2][:-1]) + Decimal('0.001')  # 시작 시간 = 응답 완료 시간 - 처리 시간
        log.append((start_time, last_time))
```
- **로그 분리**  
    로그 문자열을 분리하여 **응답완료 시간**을 초 단위로 변환하여 저장해주고, **시작 시간**은 (응답완료 시간 - 처리 시간)으로 계산하여 저장해준다.  
    이 때, 정확한 소수 계산을 위해 `Decimal` 사용  

```Python
    for i in range(len(log)):
        count = 0
        t = log[i][1]   # 응답 완료 시간
        for j in range(i, len(log)):
            # i 인덱스 뒤 로그들 시작 시간이 (i 로그의 응답 완료 시간 + 0.999s) 이하면 count 추가
            if log[j][0] <= t + Decimal('0.999'):
                count += 1
        answer = max(answer, count) # 최댓값 저장
```
- **각 로그의 응답완료 시간을 기준으로 초당 처리량 계산**  
    각 로그의 **응답완료 시간**을 기준으로 1초간 처리 가능한 로그 수를 체크하면 되기 때문에,  
    해당 로그 뒤에 오는 로그들의 시작시간이 **(로그 응답완료 시간 + 0.999s) 이하**면 `count`를 추가해주는 방식으로 계산  
    최댓값을 `answer`에 저장하면서 반복문 수행  


## 📝 Review

응답완료 시간을 기준으로 초당 처리량을 계산하면 된다는 사실을 알고 구현했지만, 소수 계산이 정확하지 않아서 틀렸었다.  
파이썬에서 **float**로 연산을 하면 정확하지 않을 수 있기 때문에 **Decimal**을 사용해야 된다는 점을 알게 되었고, 이를 활용해서 다시 풀었다.  
