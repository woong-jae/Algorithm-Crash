# [72416] 매출 하락 최소화

## Algorithm

- DFS(tree postorder)

- Dynamic Programming

## Logic

- DP의 관점으로 보았을때, 한 팀에서 고려해야 하는 경우는 두가지다.

  1. 팀장이 참석하는 경우

  - 팀장의 매출액 + 팀원들이 팀장으로 속한 각 팀의 매출액

  2. 팀장이 참석하지 않는 경우

  - 최소 매출액을 가진 팀원을 뽑는다.

  - 해당 팀원의 매출액 + 참석하지 않는 팀원들이 팀장으로 속한 각 팀의 매출액

- 트리를 postorder 순회하며 재귀적으로 위 사항에 대한 연산을 수행한다.

  ```js
  const cost = Array.from(Array(N), () => [0, 0]);
  ```

  `cost` 배열을 생성하여 각 직원들(원소)에 대해

  - 인덱스 0 : 해당 직원이 참석하지 않음

  - 인덱스 1 : 해당 직원이 참석함

  아래 연산을 수행한다.

  ```js
  let extraCost = Infinity;

  team[leader].forEach((member) => {
    postorder(member);
    const minCost = Math.min(...cost[member]);
    cost[leader][0] += minCost;
    cost[leader][1] += minCost;
    extraCost =
      cost[member][0] > cost[member][1]
        ? 0 // 참석된 팀원에 대한 비용이 이미 포함되어 있기 때문
        : Math.min(extraCost, cost[member][1] - cost[member][0]);
  });

  cost[leader][0] += extraCost;
  ```

### 시간 복잡도 : O(N)

## Review

처음에 바보 같이 위상 정렬로 접근했다. ㅋㅋ

역시 개같이 멸망.

결국 해결하지 못하여 다른 사람의 접근법을 참고했다.

오랜만의 DP라 어려웠다..