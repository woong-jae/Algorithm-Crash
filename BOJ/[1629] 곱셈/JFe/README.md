# [1629] 곱셈 - Python

## 🔍 Algorithm
**분할 정복**

## 💻 Logic

```Python
def calc(a, x):
    # base case
    if x == 1: 
        return a % C
    # 지수를 2씩 나눠서 분할 정복
    temp = calc(a, x // 2)
    # 짝수인 경우
    if x % 2 == 0:
        return temp * temp % C
    # 홀수인 경우
    else: 
        return temp * temp * a % C
```
- **지수를 나눠주며 분할 정복**  
    - 짝수인 경우 : `10^8 % C` -> `10^4 * 10^4 % C` 형태
    - 홀수인 경우 : `10^9 % C` -> `10^4 * 10^4 * 10 % C` 형태


## 📝 Review

어떤 방식으로 접근하길 원하는지 모르겠어서 풀이 참고했다,, 수학 문제라니