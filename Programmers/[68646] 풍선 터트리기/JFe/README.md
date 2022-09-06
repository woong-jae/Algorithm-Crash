# [68646] 풍선 터트리기 - Python

## 🔍 Algorithm
**구현**

## 💻 Logic

```Python
def solution(a):
    answer, left, right = 2, [], []
    heapq.heappush(left, a[0])
    for i in reversed(range(2, len(a))):
        heapq.heappush(right, (a[i], i))
    # 풍선 하나씩 터트릴 수 있는지 탐색
    for i in range(1, len(a)-1):
        # right 값 중에 이미 지나간 인덱스면 pop
        while i > right[0][1]:
            heapq.heappop(right)
        left_temp, right_temp = left[0], right[0]
        heapq.heappush(left, a[i])  # left에 값 추가
        # left 중 최솟값과 right 중 최솟값 둘 다 현재 기준 값(a[i])보다 작으면 continue
        if a[i] > left_temp and a[i] > right_temp[0]: continue
        answer += 1
    return answer
```
- **풍선 하나씩 터트릴 수 있는지 탐색**  
    right 값 중에 이미 지나간 인덱스면 pop  
    left 중 최솟값과 right 중 최솟값 둘 다 현재 기준 값(a[i])보다 작으면 continue  

## 📝 Review

처음에는 이중 for문으로 구현할 수 있겠다 생각하고 지금이랑 같은 방식이지만 이중 for문으로 구현했는데 시간 초과가 나서 heap을 사용하는 방식으로 바꿨다.  
right에 있는 값을 삭제하는 과정에서 헤매다가 지금과 같은 방식으로 삭제하도록 구현했다.  
