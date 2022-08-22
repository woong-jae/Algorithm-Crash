# [16172] 나는 친구가 적다 (Large) - Python

## 🔍 Algorithm
**문자열**

## 💻 Logic

```Python
temp = re.sub('[0-9]', '', S)   # 정규표현식 사용해 숫자 제거
# temp 문자열에서 K 문자열을 찾으면 1 출력, 없으면 0 출력
if temp.find(K) != -1: print(1)
else: print(0)
```
- **정규표현식 `re.sub` 이용해 숫자 제거**  
- **temp 문자열에서 K 문자열을 찾으면 1 출력, 없으면 0 출력**  


## 📝 Review

숫자 제거하고 부분 문자열이 존재하는지 확인하면 되는 문제여서 정규표현식 사용해 간단하게 해결했다.