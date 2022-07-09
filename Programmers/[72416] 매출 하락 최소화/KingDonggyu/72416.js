function solution(sales, links) {
  const postorder = (leader) => {
    cost[leader][0] = 0;
    cost[leader][1] = sales[leader];

    if (!team[leader].length) {
      return;
    }

    let extraCost = Infinity;

    team[leader].forEach((member) => {
      postorder(member);
      const minCost = Math.min(...cost[member]);
      cost[leader][0] += minCost;
      cost[leader][1] += minCost;
      extraCost = (cost[member][0] > cost[member][1]) 
        ? 0 // 참석된 팀원에 대한 비용이 이미 포함되어 있기 때문
        : Math.min(extraCost, cost[member][1] - cost[member][0]);
    });

    cost[leader][0] += extraCost;
  };

  const N = sales.length;
  const team = Array.from(Array(N), () => []);
  // index 0: 참석 x, index 1: 참석 O
  const cost = Array.from(Array(N), () => [0, 0]);

  links.forEach(([leader, member]) => {
    team[leader - 1].push(member - 1);
  });

  postorder(0);

  return Math.min(...cost[0]);
}
