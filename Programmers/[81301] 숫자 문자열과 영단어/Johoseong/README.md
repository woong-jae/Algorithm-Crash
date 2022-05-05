# [81301] 숫자 문자열과 영단어
## Algorithm
- 문자열
## Logic
- 숫자 10개에 대해서 ```replace()``` 메소드 사용
```python
for i in range(10):
    arr = arr.replace(words[i], str(i))
```
- 변경할 단어가 10개 뿐이라서 ```replace()``` 메소드로 충분함
- 시간복잡도 O(N)

## Review
매우 간단한 문자열 문제였다.
