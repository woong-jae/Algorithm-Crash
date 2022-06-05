const Node = (function () {
  function Node(x, number) {
    this.x = x;
    this.number = number;
    this.left = null;
    this.right = null;
  }

  Node.prototype.insert = function (x, number) {
    this.x > x 
			? this.addLeft(x, number) 
			: this.addRight(x, number);
  };

  Node.prototype.addLeft = function (x, number) {
    this.left 
			? this.left.insert(x, number) 
			: (this.left = new Node(x, number));
  };

  Node.prototype.addRight = function (x, number) {
    this.right
      ? this.right.insert(x, number)
      : (this.right = new Node(x, number));
  };

  return Node;
})();

function solution(nodeinfo) {
  const nodes = nodeinfo
    .map(([x, y], i) => ({ x, y, number: i + 1 }))
    .sort((a, b) => b.y - a.y);

  const root = new Node(nodes[0].x, nodes[0].number);

  for (let i = 1; i < nodes.length; i++) {
    root.insert(nodes[i].x, nodes[i].number);
  }

  const answer = [[], []];

  const preorder = (node) => {
    if (node) {
      answer[0].push(node.number);
      preorder(node.left);
      preorder(node.right);
    }
  };

  const postorder = (node) => {
    if (node) {
      postorder(node.left);
      postorder(node.right);
      answer[1].push(node.number);
    }
  };

  preorder(root);
  postorder(root);

  return answer;
}
