# [42892] 길 찾기 게임

## Algorithm
- 그래프

## Logic

```java
public void insert(Node input) {
    // root가 없다면 root 지정, root가 있다면, 자식 노드 탐색하여 저장
    if (root == null) root = input;
    else insert(input, root);
}

private void insert(Node input, Node root) {
    // 부모보다 x값이 작은 경우에는 left를 탐색, 그렇지 않으면 right 탐색
    if (input.data.x < root.data.x) {
        if (root.left == null) root.left = input;
        else insert(input, root.left);
    } else {
        if (root.right == null) root.right = input;
        else insert(input, root.right);
    }
}
```

- 트리를 생성하는 함수
- 노드의 `x` 좌표를 기준으로 왼쪽 또는 오른쪽 자식 여부가 결정된다.

## Review
- 고문해시간에 BFS 형식으로 입력받아 이를 preorder, postorder로 출력했던 과제가 생각나 아이디어는 쉽게 잡을 수 있었다.
- x값 좌표에 따른 왼쪽, 오른쪽 자식 여부에 대한 고려를 통해 트리를 생성해야 하는 생각이 키 포인트였지 않을까 ..
  - `insert()` 를 오버로딩하는 방식을 사용해보았다!