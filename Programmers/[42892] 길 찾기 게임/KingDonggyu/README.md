# [42892] 길 찾기 게임

## Algorithm

- binary tree

- recursion

## Logic

- 이진 트리를 생성한다.

  ```js
  const nodes = nodeinfo
    .map(([x, y], i) => ({ x, y, number: i + 1 }))
    .sort((a, b) => b.y - a.y);
  ```

  - 주어진 `nodeinfo`를 y를 기준으로 내림차순 정렬한다.

  ```js
  ...

  Node.prototype.insert = function (x, number) {
    this.x > x
      ? this.addLeft(x, number)
      : this.addRight(x, number);
  }

  Node.prototype.addLeft = function (x, number) {
    this.left
      ? this.left.insert(x, number)
      : (this.left = new Node(x, number));
  }

  Node.prototype.addRight = function (x, number) {
    this.right
      ? this.right.insert(x, number)
      : (this.right = new Node(x, number));
  }

  ...

  for (let i = 1; i < nodes.length; i++) {
    root.insert(nodes[i].x, nodes[i].number);
  }

  ...
  ```

  - 정렬한 노드들을 순회하며, 매 순회마다 루트노드부터 x 값을 비교하여(x가 작으면 왼쪽, 크면 오른쪽) 노드가 없는 적절한 위치에 해당 노드를 추가한다.

- 생성한 트리를 재귀를 통한 전위 순회, 후위 순회한다.

### 시간 복잡도: O(NlogN)

## Review

이진 트리를 만드는 것이 관건이었다. 이전 노드의 x 값을 저장해두었다가 이를 통해 현재 노드의 트리 상 위치를 파악하는 형식으로 구현하려다 실패했다.

그래서 매 노드를 트리에 추가할 때마다 그냥 루트 노드부터 확인하는 형식으로 하니 해결했다.

이 방법은 시간초과 나올거 같아 하지 않았던거였는데..

그리고 처음으로 프로토타입을 이용해보았다. 모든 노드마다 중복 메서드를 가지는 것이 메모리적으로 비효율적이라 생각했다.
