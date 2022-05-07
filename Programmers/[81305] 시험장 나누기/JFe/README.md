# [81305] 시험장 나누기 - Python

## 🔍 Algorithm
**DFS, Parametric Search**

## 💻 Logic

```Python
# Parametric Search
    while start < end:
        mid = (start + end) // 2
        count = 0
        dfs(root, mid, num)
        count += 1
        if count <= k:
            end = mid
        else:
            start = mid + 1
```
- `최소화된 최대 그룹의 인원`을 구하는 **최적화 문제**를 `각 그룹을 x명으로 제한할 때 k개 이하의 그룹 수가 되는지` 판단하는 **결정 문제**로 바꿔서 해결  
- 몇 명으로 제한할지는 **이분 탐색**처럼 정하고, 그 때 몇 개 이하의 그룹 수가 되는지는 **DFS** 탐색 하면서 확인  

```Python
def dfs(node, limit, num):
    global count
    left_value, right_value = 0, 0
    if left[node] != -1: 
        left_value = dfs(left[node], limit, num)    # 왼쪽 자식 노드에서 오는 값
    if right[node] != -1:
        right_value = dfs(right[node], limit, num)  # 오른쪽 자식 노드에서 오는 값
    # 나눌 필요 없는 경우
    if num[node] + left_value + right_value <= limit:
        return num[node] + left_value + right_value
    # 자식 노드 중 하나만 나누면 되는 경우
    if num[node] + min(left_value, right_value) <= limit:
        count += 1
        return num[node] + min(left_value, right_value)
    # 자식 노드 둘 다 나눠야 하는 경우
    count += 2
    return num[node]
```
- 리프에서부터 값을 `limit`를 벗어나지 않게 비교하면서 상위 노드로 올려주고, `limit`를 벗어나면 그룹을 나누어준다.  
- 1. 다 더해도 `limit`를 넘지 않아서 자식 노드와 그룹 나눌 필요 없는 경우  
- 2. 자식 노드 중 **min** 값을 더했을 때는 `limit`를 넘지 않아서 하나만 나누면 되는 경우  
- 3. 자식 노드 둘 다 더할 수 없어서 둘 다 나눠야 하는 경우  
- 이렇게 세가지로 나눠서 탐색하고 그룹 수를 카운트 해준다.


## 📝 Review

DFS로 해야 되나,,?? 라는 생각만 들고 감이 안잡혀서 다른 사람들 풀이 보고 이해했다..  
난이도가 그만큼 어려워서인지 풀이도 많지 않았고, 봐도 이해하는데 오래걸렸다.  
`Parametric Search` 라는 알고리즘은 처음 알게 되었고, 이해는 했으니 이제 많이 풀어봐야겠다ㅜ
