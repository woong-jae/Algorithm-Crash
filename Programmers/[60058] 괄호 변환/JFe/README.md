# [60058] 괄호 변환 - Python

## 🔍 Algorithm
**문자열**

## 💻 Logic

```Python
# 전체 재귀 함수
    def recursion(s):
        if len(s) == 0: return ''   # 1. 빈 문자열인 경우
        index = separate(s)
        u, v = s[:index+1], s[index+1:]
        # 3. u가 올바른 괄호 문자열인 경우
        if check_correct(u):
            return u + recursion(v)
        # 4. u가 올바른 괄호 문자열이 아닌 경우
        else:
            temp = reverse(u)
            return '(' + recursion(v) + ')' + temp
```
- **재귀 함수**  
    - 빈 문자열인 경우, 빈 문자열 반환  
    - `u`가 올바른 괄호 문자열인 경우, 아닌 경우 나눠서 처리  

```Python
# 올바른 괄호 문자열인지 확인
    def check_correct(s):
        left, right = 0, 0
        for i, c in enumerate(s):
            if c == '(':
                left += 1
            else:
                right += 1
            if left < right:
                return False
        return True
```
- **올바른 괄호 문자열인지 확인하는 함수**  
    - `left`보다 `right`가 커지면 **False** 반환  

```Python
# 2. 문자열 u, v로 분리 (분리하는 기준 인덱스 반환)
    def separate(s):
        left, right = 0, 0
        for i, c in enumerate(s):
            if c == '(':
                left += 1
            else:
                right += 1
            if left > 0 and right > 0 and left == right:
                return i
```
- **문자열 u, v로 분리하는 함수**  
    - `left`, `right`가 **1 이상**이면서 **같을 때**, 그 인덱스 반환  


```Python
# 4-4. u 문자열 변환
    def reverse(s):
        temp = s[1:-1]
        temp = temp.replace('(', '-').replace(')', '(').replace('-', ')')
        return temp
```
- **u 문자열 변환하는 함수**  
    - 앞, 뒤 문자 **slicing** 하고,  
    - `'('`, `')'` 문자 **replace**  


## 📝 Review

문제에 적혀있는 그대로 구현하면 되는 문제  
문자열 변환하는 부분에서 replace를 3번 써서 swap 했는데, 이 부분을 더 깔끔하게 짤 수 있을까  
