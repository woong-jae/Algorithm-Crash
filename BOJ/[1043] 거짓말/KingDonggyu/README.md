# [1043] 거짓말

## Algorithm

- Disjoint Set
- Union - Find

## Logic

- Union을 통해 Disjoint Set을 만든다.

  - 루트 노드가 진실을 알고 있는 인원이 갈 수 있도록 아래와 같이 우선순위를 적용해주어야 한다.

  ```js
  constructor(size, priority) {
    this.root = new Array(size);
    this.priority = [...priority]; // 진실을 알고 있는 인원들
    for (let i = 0; i < size; i++) {
      this.root[i + 1] = i + 1;
    }
  }

  ...

  let rootX = this.find(x);
  let rootY = this.find(y);

  if (!this.priority.includes(rootX) && this.priority.includes(rootY)) {
    [rootX, rootY] = [rootY, rootX];
  }
  ```

- 이후 각 파티에 속한 인원들에 대해 Find를 이용하여 루트 값이 진실을 알고 있는 인원이 있는지 확인한다.

### 시간 복잡도 : O(NlogN)

## Review

문제를 읽고 Disjoin Set 아이디어를 바로 떠올릴 수 있었다.
