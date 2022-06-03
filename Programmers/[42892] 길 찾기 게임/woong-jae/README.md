# [42892] 길 찾기 게임
## Algorithm
- Sort
- Binary Tree
## Logic
주어진 노드 정보를 레벨에 대해 내림차순으로 정렬한 다음 이진 트리를 구성한다.

만든 이진 트리를 전위, 후위 순회하면 된다.
```js
function solution(nodeinfo) {
    const bt = new BinaryTree();
    nodeinfo
        .map((info, index) => [...info, index + 1])
        .sort((a, b) => b[1] - a[1])
        .forEach(([x, y, idx]) => bt.insert(new Node(x, y, idx)));
    
    return [preorder(bt.root), postorder(bt.root)];
}
```

## Review
쉬운 문제.