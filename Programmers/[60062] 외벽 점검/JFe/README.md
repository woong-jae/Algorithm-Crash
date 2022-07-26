# [60062] 외벽 점검 - Python

## 🔍 Algorithm
**순열, 완전 탐색**

## 💻 Logic

```Python
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
```
- weak 시작 인덱스 바꿔가면서 반복하고, dist 기준으로 순열 생성  
- 생성된 순열 순서대로 현재 weak 인덱스에서 다음 위치까지 이동할 수 있는지 계산해서 판단하고,  
- 갈 수 없으면 dist 다음 사람으로 바꾸는 식으로 반복하면서 카운트  
- 각 순열마다 생성된 카운트의 최솟값을 answer에 저장  


## 📝 Review

이번 주 문제 너무 어렵다..  
해결 방법을 모르겠어서 다른 사람 풀이 참고했고, 순열 이용해서 푸는 방법으로 했다..
