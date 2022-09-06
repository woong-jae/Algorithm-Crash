def palindrome(s, N, l, r):
    cnt = 0
    while True:
        if l < 0 or r >= N: break

        if s[l] == s[r]:
            cnt += 2
            l -= 1
            r += 1
        else: break
    return cnt

def solution(s):
    answer = 1
    N = len(s)

    for center in range(1, N - 1): # 팰린드롬 길이가 홀수라면
        cnt = palindrome(s, N, center - 1, center + 1) + 1
        answer = max(answer, cnt)

    for center in range(1, N): # 팰린드롬 길이가 짝수라면
        cnt = palindrome(s, N, center - 1, center)
        answer = max(answer, cnt)

    return answer