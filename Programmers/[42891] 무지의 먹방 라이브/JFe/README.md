# [42891] 무지의 먹방 라이브 - Python

## 🔍 Algorithm
**Priority Queue**

## 💻 Logic

```Python
    # 우선순위 큐 생성
    for i, time in enumerate(food_times):
        heapq.heappush(h, [time, i])
    # 가장 작은 양의 음식부터 먹기 (k 시간을 넘지 않을 때까지만 반복)
    while total_time + (h[0][0] - pre_time) * len(h) <= k:
        cur_time = heapq.heappop(h)[0]
        total_time += (cur_time - pre_time) * (len(h)+1)
        pre_time = cur_time
```
- **우선순위 큐 생성**  
    - `food_time`이 작은 것부터 먹기 위해 **우선순위 큐**를 만들어준다.  
    - 가장 작은 양의 음식을 먹고 (**heappop**해서 `cur_time`에 저장)  
    - 이전 `pre_time`을 뺀 시간에 현재 **heap**의 크기(**heappop** 했는 것도 포함)를 곱해서 `total_time`에 더해준다.  
    - 이전에 걸린 시간을 저장하기 위해 `cur_time`을 `pre_time`으로 변경  
    - **k** 시간을 넘지 않을 때까지만 반복  

```Python
# 음식 번호 순으로 정렬
    sorted_list = sorted(h, key = lambda x : x[1])
    answer = sorted_list[(k-total_time)%len(h)][1] + 1
```
- **음식 번호 순으로 정렬**  


## 📝 Review

처음에는 쉬운 문제라고 생각했는데 효율성을 통과할 방법을 찾지 못해서 다른 코드를 참고했다,,, 
