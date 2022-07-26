class Node {
    constructor(x, y, index, left = null, right = null) {
        this.x = x;
        this.y = y;
        this.index = index,
        this.left = left;
        this.right = right;
    }
}

class BinaryTree {
    constructor() {
        this.root = null;
    }
    insert(node) {
        if(!this.root) {
            this.root = node;
            return;
        }
        let cur = this.root;
        while(1) {
            let next = cur.x > node.x ? "left" : "right";
            if(!cur[next]) {
                cur[next] = node;
                break;
            }
            cur = cur[next];
        }
    }
}

const preorder = root => {
    if(!root) return [];
    return [root.index].concat(preorder(root.left), preorder(root.right));
}

const postorder = root => {
    if(!root) return [];
    return postorder(root.left).concat(postorder(root.right), [root.index]);
}

function solution(nodeinfo) {
    const bt = new BinaryTree();
    nodeinfo
        .map((info, index) => [...info, index + 1])
        .sort((a, b) => b[1] - a[1])
        .forEach(([x, y, idx]) => bt.insert(new Node(x, y, idx)));
    
    return [preorder(bt.root), postorder(bt.root)];
}