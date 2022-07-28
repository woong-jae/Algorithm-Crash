function solution(enroll, referral, seller, amount) {
  class Node {
      constructor(name) {
          this.name = name;
          this.profit = 0;
          this.parent = null;
      }
  }
  // 트리 생성
  const name_to_index = new Map(enroll.map((name, index) => [name, index]));
  const nodes = enroll.map((name) => new Node(name));
  const root = new Node("-");
  
  referral.forEach((parent, index) => {
      const node = nodes[index];
      if(parent === "-") {
          node.parent = root;
          return;
      }
      const parentNode = nodes[name_to_index.get(parent)];
      node.parent = parentNode;
  });
  
  amount.forEach((sold, index) => {
      let earned = sold * 100;
      let cur = nodes[name_to_index.get(seller[index])];
      while(cur) {
          if(earned === 0) return;
          const will_earn = Math.ceil(earned * 0.9);
          earned -= will_earn;
          
          cur.profit += will_earn;
          cur = cur.parent;
      }
  });
  
  return nodes.map(({ profit }) => profit);
}