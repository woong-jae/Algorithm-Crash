def solution(s):
    answer = 0
    for i in range(len(s)):
        # 팰린드롬 문자열이 홀수인 경우
        left, right, count = i - 1, i + 1, 1
        while 0 <= left and right < len(s):
            if s[left] == s[right]:
                count += 2
                left, right = left - 1, right + 1
            else: break
        answer = max(answer, count)
        # 팰린드롬 문자열이 짝수인 경우
        pivot, right, count = i, i + 1, 0
        while 0 <= pivot and right < len(s):
            if s[pivot] == s[right]:
                count += 2
                pivot, right = pivot - 1, right + 1
            else: break
        answer = max(answer, count)
    return answer