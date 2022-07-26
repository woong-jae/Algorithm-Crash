# [17687] n진수 게임 - Python

## 🔍 Algorithm

## 💻 Logic

```Python
def convert(num, base):
    temp = "0123456789ABCDEF"
    q, r = divmod(num, base)
    if q == 0:
        return temp[r]
    else:
        return convert(q, base) + temp[r]
```
- **n진수 변환 함수**  

```Python
    # 진수 변환 (변환 시 길이가 t*m 될 때까지)
    while len(total) <= t * m:
        total += str(convert(num, n))
        num += 1
    # t개까지 말해야 하는 숫자 저장
    while len(answer) < t:
        answer += total[cur]
        cur += m
```
- **진수 변환 (변환 시 길이가 t*m 될 때까지)**  
- **t개까지 말해야 하는 숫자 저장**  


## 📝 Review

파이썬에서는 n진수로 변환하는 함수가 따로 존재하지 않아서 변환 부분은 다른 코드를 보고 참조했다...  
처음으로 파이썬이 마음에 안들었던 부분,,,
