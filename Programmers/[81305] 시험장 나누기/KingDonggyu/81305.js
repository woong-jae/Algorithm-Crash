function solution(k, num, links) {
  const getPostorder = () => {
    const order = [];
    const stack = [root];
    const visited = Array(N).fill(false);

    outer: while (stack.length) {
      const node = stack[stack.length - 1];
      for (const child of links[node]) {
        if (child !== -1 && !visited[child]) {
          stack.push(child);
          visited[child] = true;
          continue outer;
        }
      }
      order.push(stack.pop());
    }
    return order;
  }

  const divideRoom = (limit) => {
    postorder.forEach(node => {
      const [leftChild, rightChild] = links[node];

      // 자식 노드 0개
      if (leftChild + rightChild === -2) {
        dp[node][0] = num[node];
        return;
      }
      
      // 자식 노드 1개
      if (leftChild === -1 || rightChild === -1) {
        const child = leftChild === -1 ? rightChild : leftChild;
        // - 자식과 한 그룹에 속할 수 있는 경우
        if (limit >= num[node] + dp[child][0]) {
          dp[node][0] = num[node] + dp[child][0];
          dp[node][1] = dp[child][1];
        } 
        // - 자식과 그룹이 될 수 없는 경우
        else { 
          dp[node][0] = num[node];
          dp[node][1] = dp[child][1] + 1;
        }
        return;
      }

      // 자식 노드 2개
      // - 자식 둘과 한 그룹에 속할 수 있는 경우
      if (limit >= num[node] + dp[leftChild][0] + dp[rightChild][0]) {
        dp[node][0] = num[node] + dp[leftChild][0] + dp[rightChild][0];
        dp[node][1] = dp[leftChild][1] + dp[rightChild][1] - 1;
      }
      // - 자식 하나만 한 그룹에 속할 수 있는 경우 (둘 중 작은 자식 선택)
      else if (limit >= num[node] + Math.min(dp[leftChild][0], dp[rightChild][0])) {
        dp[node][0] = num[node] + Math.min(dp[leftChild][0], dp[rightChild][0]);
        dp[node][1] = dp[leftChild][1] + dp[rightChild][1];
      }
      // - 자식 둘과 모두 그룹이 될 수 없는 경우
      else {
        dp[node][0] = num[node];
        dp[node][1] = dp[leftChild][1] + dp[rightChild][1] + 1;
      }
    })

    return dp[root][1];
  }

  const N = num.length;
  const indegree = Array(N).fill(0);
  
  links.forEach(([leftChild, rightChild]) => {
    leftChild !== -1 && indegree[leftChild]++;
    rightChild !== -1 && indegree[rightChild]++;
  });

  const root = indegree.indexOf(0);
  const postorder = getPostorder();
  const dp = Array(N).fill(0).map((_) => [0, 1]);

  let left = Math.max(...num);
  let right = num.reduce((a, b) => a + b);

  while (left <= right) {
    const mid = Math.floor((left + right) / 2);
    if (divideRoom(mid) <= k) right = mid - 1;
    else left = mid + 1;
  }

  return left;
}