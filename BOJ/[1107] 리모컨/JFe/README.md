# [1107] 리모컨 - Python

## 🔍 Algorithm
**Brute Force**

## 💻 Logic

```Python
# 이동 가능한 번호 탐색
for i in range(1000000):
    temp = list(str(i))
    flag = True
    # 고장난 번호가 포함되어 있으면 flag를 False로 바꾸고 break
    for t in temp:
        if int(t) in button:
            flag = False
            break
    # flag가 True면 이동하는데 누르는 횟수(len(temp)) + 이동한 위치에서 목표 위치까지 차이(abs(N - i))와 result 중 최솟값 업데이트
    if flag: result = min(result, abs(N - i) + len(temp))
```
- **이동 가능한 가까운 번호 탐색**  
    - 고장난 번호가 포함되어 있으면 `flag`를 **False**로 바꾸고 **break**  
    - `flag`가 **True**면 이동하는데 누르는 횟수(`len(temp)`) + 이동한 위치에서 목표 위치까지 차이(`abs(N - i)`)와 `result` 중 **최솟값** 업데이트  


## 📝 Review

처음에는 채널이 500000까지 있어서 이만큼만 반복문 돌렸는데 500000번 위의 번호에서 내려오는 경우를 생각 못하고 있었다.  
시간 초과 안나고 이게 되네  