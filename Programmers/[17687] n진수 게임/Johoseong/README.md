# [17687] n진수 게임
## Algorithm
- 진수 변환

## Logic
- 10진수 -> n진수로 바꾸는 메소드 작성이 핵심. 0부터 숫자들을 진수 변환해서 문자열로 쭉 이어붙인 후, 구해야하는 인덱스만 뽑아내면 됨.
1. t에 따라, (m * t) + p 등차수열을 통해서 구해야하는 수의 인덱스를 미리 구해둠
```python
while len(index) < t:
    index.append(index[-1] + m)
```
2. 10진수 -> n진수 변환 메소드 작성
```python
def convert(num, n):
    rev_base = ''
    map = {10: 'A', 11: 'B', 12: 'C', 13: 'D', 14: 'E', 15: 'F'}
    if num == 0:
        return '0'
    while num > 0:
        num, mod = divmod(num, n)
        if mod >= 10:
            mod = map[mod]
        rev_base += str(mod)
    return rev_base[::-1] 
```
- 10진수 넘어가면 mod >= 10부터는 영어 A~F에 매핑되는 것에 유의하기
3. 1을 통해서 구해야하는 인덱스 중, 가장 큰 인덱스를 넘어갈만큼 숫자 변환함
```python
last = index[-1]
num = ''
cur = 0
while len(num) <= last:
    num += convert(cur, n)
    cur += 1
```
4. 인덱스에 맞는 문자 추출
```python
for i in index:
    answer += num[i]
```

## Review
진수 변환 오랜만에 해서 좀 헷갈렸던 것만 빼면 무난한 문제였다.