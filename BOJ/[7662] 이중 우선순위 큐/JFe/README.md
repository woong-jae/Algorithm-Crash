# [7662] 이중 우선순위 큐 - Python

## 🔍 Algorithm
**Priority Queue**

## 💻 Logic

```Python
def calculate():
    k = int(sys.stdin.readline())
    min_q, max_q = [], []
    for i in range(k):
        operation, num = map(str, sys.stdin.readline().split())
        num = int(num)
        # Input : min_q, max_q 둘 다 heappush
        if operation == 'I':
            exist[i] = True
            heapq.heappush(min_q, (num, i))
            heapq.heappush(max_q, (-num, i))
        # 최솟값 삭제
        elif num == -1:
            while min_q and not exist[min_q[0][1]]:
                heapq.heappop(min_q)
            if min_q:
                exist[min_q[0][1]] = False
                heapq.heappop(min_q)
        # 최댓값 삭제
        else:
            while max_q and not exist[max_q[0][1]]:
                heapq.heappop(max_q)
            if max_q:
                exist[max_q[0][1]] = False
                heapq.heappop(max_q)
    while min_q and not exist[min_q[0][1]]:
        heapq.heappop(min_q)
    while max_q and not exist[max_q[0][1]]:
        heapq.heappop(max_q)
    if not max_q:
        print('EMPTY')
    else:
        print(-max_q[0][0], min_q[0][0])
```
- **이중 우선순위 큐 구현**  
    - `min_q`, `max_q` 두 개 **heapq** 구현  
    - **Input** : `min_q`, `max_q` 둘 다 **heappush**  
    - **최솟값 삭제 시** : `min_q` 값 삭제 후 `exist` 값 수정 / **최댓값 삭제 시** : `max_q` 값 삭제 후 `exist` 값 수정


## 📝 Review

일년 전에 풀어봤던 문제  
heapq를 통해 우선 순위 큐를 구현할 수 있고, min, max 힙 두 개를 만들고 둘의 존재 여부 인덱스를 공유하는 방법으로 구현했다.  