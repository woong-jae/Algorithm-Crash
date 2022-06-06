# [42892] 길 찾기 게임
## Algorithm
- **Sorting**, **Tree**

## Logic
- 주어진 x,y, 인덱스 번호, 왼쪽자식노드, 오른쪽자식노드의 값을 가진 Node로 리스트를 만든다
- 루트노드의 값의 y값이 가장 크고 만약 같다면 x값이 작은게 앞으로 와야하기 때문에  
- 리스트를 y값 기준으로 내림차순 정렬을 하고 만약 y가 같다면 x값을 기준으로 오름차순 정렬을 한다
- 리스트의 0번째 노드를 root 노드로 설정하고 insert를 수행
  - 만약 x값이 부모 노드보다 작고 부모노드의 왼쪽 자식노드가 비어있다면 왼쪽 자식노드에 넣고  
  - 만약 왼쪽자식 노드가 비어있지않다면 왼쪽자식을 부모노드로 하여 함수를 재귀호출한다
  - x값이 부모노드 보다 큰경우는 오른쪽 자식노드로 설정하여 위와 같은 방식으로 진행
- 만들어진 트리로 전위순회와 후위순회를 수행

```java
public int[][] solution(int[][] nodeinfo) {
    answer = new int[2][nodeinfo.length];

    List<Node> tree = new ArrayList<>();
    for(int i = 0; i < nodeinfo.length; i++)
        tree.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1], null, null));

    tree.sort((o1, o2) -> {
        if (o1.y == o2.y)
            return o1.x - o2.x;
        return o2.y - o1.y;
    });

    Node root = tree.get(0);

    for(int i = 1; i < tree.size(); i++)
        root.insert(root, tree.get(i));

    postorder(root);
    index = 0;
    preorder(root);

    return answer;
}
```

## Review
트리를 생성할때에 정렬을 하는 방식이 금방생각나서 쉽게 풀었던 문제
