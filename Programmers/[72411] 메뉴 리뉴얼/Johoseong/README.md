# [72411] 메뉴 리뉴얼
## Algorithm
- 조합
## Logic
- 범위가 크지 않기 때문에, 각 order마다 course 만큼의 조합을 구한 후 가장 많이 발생한 조합을 결과로 하면 풀 수 있다.
1. 각 order마다 course개 조합을 구함
- 발생한 조합은 정렬 후 넣어야함
- 조합을 key로 하는 딕셔너리를 사용해서, 해당 조합이 나오는 횟수 count
```python
for c in course:
    for o in orders:
        coms = list(combinations(o, c)) # 각 order마다 course개 조합 구함
        for com in coms:
            tmp = ''.join(sorted(com))
            combis[c].setdefault(tmp, 0)
            combis[c][tmp] += 1 # 해당 코스 조합이 나온 횟수 count
```
1. 1에서 구한 조합들을 탐색하면서, 각 course 개수마다 제일 많이 count된 조합 구함
- 2 이상 count된 경우만 보면 됨
```python
for i in combis: # 제일 많이 주문된 코스조합 구하기
    MAX = 2 # 2명 이상 손님이 주문한 경우만 됨
    tmp = []
    for j in combis[i]:
        if combis[i][j] > MAX:
            MAX = combis[i][j]
            tmp = [j]
        elif combis[i][j] == MAX:
            tmp.append(j)
    for t in tmp:
        answer.append(t)
```
3. 정렬 후 출력

## Review
솔직히 푸는 건 쉬웠는데 문제 자체를 이해하기가 힘들었다;; 테케 이해도 힘듬..