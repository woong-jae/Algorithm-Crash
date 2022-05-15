import sys
from collections import defaultdict
N = int(sys.stdin.readline())
s = sys.stdin.readline().strip()
start, result = 0, 0
alpha = defaultdict(int)
# start, end 투 포인터 사용
for end in range(len(s)):
    alpha[s[end]] += 1
    # N개 이하의 알파벳을 가지는 경우
    if len(alpha) <= N:
        result = max(result, end - start + 1)   # result와 최댓값 비교 후 저장
    # N개 보다 많이 알파벳을 가지는 경우
    while len(alpha) > N:
        alpha[s[start]] -= 1
        if alpha[s[start]] == 0: del alpha[s[start]]    # 해당 키 값이 0이 되면 dictionary에서 삭제
        start += 1
print(result)