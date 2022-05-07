from collections import defaultdict
import sys
sys.setrecursionlimit(10**6)

left, right, parent = defaultdict(int), defaultdict(int), defaultdict(lambda: -1)
count = 0

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
        
def solution(k, num, links):
    global count
    root, start, end = 0, max(num), 10 ** 8
    # 트리 정보 입력
    for i in range(len(num)):
        left[i], right[i] = links[i]
        if left[i] != -1: parent[left[i]] = i
        if right[i] != -1: parent[right[i]] = i
    # root 값 찾기
    for i in range(len(num)):
        if parent[i] == -1: 
            root = i
            break
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
    return start