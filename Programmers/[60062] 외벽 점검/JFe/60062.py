import sys
from itertools import permutations

def solution(n, weak, dist):
    answer = sys.maxsize
    weak = weak + [w + n for w in weak]
    for idx in range(len(weak)//2):     # 첫 시작점
        for d in permutations(dist, len(dist)): # 순열 생성
            count = 1
            cur = idx
            for next_idx in range(1, len(weak)//2):
                next = idx + next_idx
                diff = weak[next] - weak[cur]
                # 다음 위치까지 갈 수 없으면, 다음 사람
                if diff > d[count-1]:
                    count += 1
                    cur = next
                    if count > len(dist):
                        break
            if count <= len(dist):
                answer = min(answer, count)
    if answer == sys.maxsize:
        return -1
    return answer