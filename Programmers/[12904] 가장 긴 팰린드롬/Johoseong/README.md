# [12904] 가장 긴 팰린드롬
## Algorithm
- 구현
## Logic
- 기준점을 잡고 앞뒤로 같은 게 있으면 count하는 방식으로 풀면 됨
- 팰린드롬의 길이가 홀수일 수도, 짝수일 수도 있어서 두 경우를 나눠서 구해줘야함
1. 홀수인 경우 : 기준점의 알파벳은 무시하고, 그 알파벳 기준으로 앞뒤가 같으면 count
2. 짝수인 경우 : 기준점도 포함이 돼야함. 앞뒤 문자열 중 기준점을 뒤의 문자열에 포함시키고 비교해나감
- 주의 점 : 알파벳 하나도 팰린드롬임.
```python
def palindrome(s, N, l, r):
    ...
    while True:
        ...
        if s[l] == s[r]:
            cnt += 2
            l -= 1
            r += 1
...
for center in range(1, N - 1): # 팰린드롬 길이가 홀수라면
    cnt = palindrome(s, N, center - 1, center + 1) + 1
    answer = max(answer, cnt)

for center in range(1, N): # 팰린드롬 길이가 짝수라면
    cnt = palindrome(s, N, center - 1, center)
    answer = max(answer, cnt)
```

## Review
알고리즘 고민하다가 인풋 작은거 보고 O(N^2) 방법을 생각해낼 수 있었다. 무난한 문제였다.