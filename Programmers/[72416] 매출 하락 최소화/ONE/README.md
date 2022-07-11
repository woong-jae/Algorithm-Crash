# [72416] 매출 하락 최소화
## Algorithm
- **DP**

## Logic
- 주요 아이디어는 부모를 넣고 안넣고를 구분하고
- 부모를 넣었다면 자식을 넣지않고
- 부모를 안넣었다면 자식들 중에 최솟값을 찾는다
- 위를 재귀적으로 구현하여 그룹의 최소 매출액을 구한다

```java
private void traversal(int node) {

    // 초기에 선택안하는 값은 0, 선택한값은 자기 자신의 cost로 설정
    cost[node][0] = 0;
    cost[node][1] = sales[node - 1];

    if(children[node].isEmpty())
        return;

    // extra 는 부모가 참석 안했을때 자식이 참석해서 생기는 비용
    int extra = Integer.MAX_VALUE;
    for (int child : children[node]) {
        traversal(child);
        if (cost[child][0] < cost[child][1]) {
            cost[node][0] += cost[child][0];
            cost[node][1] += cost[child][0];
            // 자식 비용들중에서 최솟값 찾기
            extra = Math.min(extra, cost[child][1] - cost[child][0]);
        } else {
            cost[node][0] += cost[child][1];
            cost[node][1] += cost[child][1];
            extra = 0;
        }
    }
    cost[node][0] += extra;
}
```

## Review
처음에는 Map<Integer, List<Integer>>를 이용하여 자식중에 최솟값과 부모를 비교하여 최솟값을 찾아서 구하는 방식으로 구현했는데,  
완전히 잘못된 방향으로 하고 있다는 것을 깨닫고 풀이를 참고하여 구현했다.  
막상 생각해보면 어려운 아이디어는 아니었던것 같지만 생각을 좀 다른 방향으로 했어야 했던 것 같다.  
좋은 문제.
