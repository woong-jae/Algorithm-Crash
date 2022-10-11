import sys
from collections import defaultdict

def bellman_ford(N, node):
    distance= [int(1e9) for _ in range(N + 1)]
    distance[node] = 0
    # 전체 n번 반복
    for i in range(N):
        # 모든 간선 확인
        for key, value in graph.items():
            for next_node, cost in value:
                # 다음 노드로 이동하는 거리가 더 짧은 경우 업데이트
                if distance[next_node] > distance[key] + cost:
                    distance[next_node] = distance[key] + cost
                    # N번째에도 값이 업데이트 되면, 음의 사이클이 존재
                    if i == N - 1: return True
    return False

T = int(sys.stdin.readline())
for _ in range(T):
    N, M, W = map(int, sys.stdin.readline().split())
    road = [[int(x) for x in sys.stdin.readline().split()] for _ in range(M)]
    wormhole = [[int(x) for x in sys.stdin.readline().split()] for _ in range(W)]
    graph = defaultdict(list)
    # road 정보 graph에 입력
    for S, E, T in road:
        graph[S].append([E, T])
        graph[E].append([S, T])
    # wormhole 정보 graph에 입력
    for S, E, T in wormhole:
        graph[S].append([E, -T])
    result = bellman_ford(N, 1)
    if result: print("YES")
    else: print("NO")