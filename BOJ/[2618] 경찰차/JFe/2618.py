import sys
sys.setrecursionlimit(10**8)

N = int(sys.stdin.readline())
W = int(sys.stdin.readline())
dp = [[-1 for _ in range(W + 2)] for _ in range(W + 2)]
loc = [(1, 1), (N, N)]

def search(dist1, dist2):
	if dist1 > W or dist2 > W: return 0
	if dp[dist1][dist2] != -1: return dp[dist1][dist2]
	next_loc = max(dist1, dist2) + 1

	next_d1 = search(next_loc, dist2) + abs(loc[next_loc][0] - loc[dist1][0]) + abs(loc[next_loc][1] - loc[dist1][1])
	next_d2 = search(dist1, next_loc) + abs(loc[next_loc][0] - loc[dist2][0]) + abs(loc[next_loc][1] - loc[dist2][1])

	dp[dist1][dist2] = min(next_d1, next_d2)
	return dp[dist1][dist2]

def route(dist1, dist2):
	if dist1 > W or dist2 > W: return
	next_loc = max(dist1, dist2)+1
	next_d1 = abs(loc[next_loc][0] - loc[dist1][0]) + abs(loc[next_loc][1] - loc[dist1][1])
	next_d2 = abs(loc[next_loc][0] - loc[dist2][0]) + abs(loc[next_loc][1] - loc[dist2][1])

	if dp[next_loc][dist2] + next_d1 < dp[dist1][next_loc] + next_d2:
		print(1)
		route(next_loc,dist2)
	else:
		print(2)
		route(dist1,next_loc)
	return

for _ in range(W):
    loc.append(tuple(map(int, sys.stdin.readline().split())))
print(search(0,1))
route(0,1)