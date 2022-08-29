# [12929] 올바른 괄호의 갯수 - Python

## 🔍 Algorithm
**Catalan number**

## 💻 Logic

```Python
def factorial(n):
    if n == 1: return 1
    return n * factorial(n-1)
```
- **기본이 되는 팩토리얼 함수**  

```Python
return factorial(2 * n) / (factorial(n) * factorial(n + 1))
```
- **카탈란 수 구현**  
    `카탈란 수` : `(2n)! / n!(n+1)!`


## 📝 Review

카탈란 수.. 처음 들어봄..!