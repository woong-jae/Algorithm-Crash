# [60057] 문자열 압축
## Algorithm
- 구현, 문자열

## Logic
- 문자열 길이가 최대 1000까지로 별로 안길어서, 자르는 단위를 1부터 시작해서 다 구해보면 됨. 핵심은 문자열 반복을 체크하는거니까 ```문자열 길이 / 2``` 길이까지만 자르면 됨.
1. 체크할 길이로 문자열을 slice 함
```python
for n in range(1, (N // 2) + 1):
    word = s[:n]
```
2. 자르는 단위에 맞춰서 문자열 반복되는지 체크함
- 단어 반복되면 반복되는 만큼 count
- 새로운 단어면 반복된 횟수 + 단어로 문자열 압축
```python
...
    while (start <= (N + n)):
        if word == s[start:start + n]:
            cnt += 1
        else: # 다른 단어면?
            if cnt != 1:
                str_zip += (str(cnt) + word)
            else:
                str_zip += word
            word = s[start:start + n]
            cnt = 1

        start += n
```
3. 가장 짧게 압축된 문자열 길이로 갱신

## Review
무난한 문제였는데, count 시작을 0으로 했다가 테스트케이스 2개를 계속 통과 못해서 애먹었다; 1로 시작하는 거로 바꾸니까 됨