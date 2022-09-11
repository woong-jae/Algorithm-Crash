# [70129] 이진 변환 반복하기
## Algorithm
- 구현, 진수 변환
## Logic
1. 주어진 2진수를 탐색해서 0 제거
2. 남은 1의 길이를 구함
3. 길이를 2진수로 변환 (format 함수 사용)
- 위 과정을, 변환된 2진수가 1이 될 때까지 반복
```python
while s != '1':
    tmp = ''
    for i in s: # 1. 0 제거
        if i == '0':
            answer[1] += 1
            continue
        tmp += i

    l = len(tmp) # 2. 길이 구함
    s = format(l, 'b') # 3. 길이->2진수 변환
    answer[0] += 1
```

## Review
조건만 잘 따르면 쉽게 풀 수 있는 문제. 진수 변환 그냥 구글링했는데 format 함수 처음 알았는데!! 편함