# [2618] 경찰차 - Python

## 🔍 Algorithm
**DP**

## 💻 Logic

```Python
def search(dist1, dist2):
	if dist1 > W or dist2 > W: return 0
	if dp[dist1][dist2] != -1: return dp[dist1][dist2]
	next_loc = max(dist1, dist2) + 1

	next_d1 = search(next_loc, dist2) + abs(loc[next_loc][0] - loc[dist1][0]) + abs(loc[next_loc][1] - loc[dist1][1])
	next_d2 = search(dist1, next_loc) + abs(loc[next_loc][0] - loc[dist2][0]) + abs(loc[next_loc][1] - loc[dist2][1])

	dp[dist1][dist2] = min(next_d1, next_d2)
	return dp[dist1][dist2]
```

---

```Python
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
```


## 📝 Review

DP 문제라는 사실을 알고도 어떻게 접근해야 할지 감이 안잡혀서 다른 풀이 참고했다..  
살려주세요  