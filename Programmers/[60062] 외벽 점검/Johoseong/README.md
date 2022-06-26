# [60062] 외벽 점검
## Algorithm
- 완전탐색? 구현?
## Logic
- 핵심은, 어차피 이동범위가 가장 큰 친구를 사용하게 되므로 dist를 내림차순으로 정렬한다는 점이다. 그 후 각 weak에서 dist가 미치지 못하는 범위를 저장해나가는 것.
1. ```dist```를 내림차순 정렬
```python
dist.sort(reverse=True)
```
2. current는 남아있는 취약지점이 됨. 각 취약지점마다 탐색 시작
3. 현재 취약지점에서 d만큼 조사해서 또 다시 나머지 점검할 수 없는 나머지 취약지점을 찾음
4. 이를 반복
```python
for i in range(len(dist)):
    d = dist[i]
    print(d, q)
    for _ in range(len(q)):
        current = q.popleft()
        for p in current:
            l = p
            r = (p + d) % n
            if l < r:
                temp = tuple(filter(lambda x: x < l or x > r, current))
            else:
                temp = tuple(filter(lambda x: x < l and x > r, current))

            if len(temp) == 0:
                return i + 1
            elif temp not in visited:
                visited.add(temp)
                q.append(list(temp))
```

## Review
너무 어렵다.. 레벨4보다 어려웠다. 여러 풀이를 봐서 이해하는 걸 목표로 했는데, 솔직히 제일 많은 풀이인 permutation 풀이가 더 이해도 안가고 효율도 낮아서 해당 방법을 이해하려고 노력했다. 다시 풀어봐야지..