class Heap {
  constructor() {
    this.heap = [];
  }

  peek = () => {
    return this.heap[0];
  };

  getLeftChild = (parent) => {
    return parent * 2 + 1;
  };

  getRightChild = (parent) => {
    return parent * 2 + 2;
  };

  getParent = (child) => {
    return Math.floor((child - 1) / 2);
  };

  insert = (key, value) => {
    const node = { key, value };
    this.heap.push(node);
    this.heapifyUp();
  };

  remove = () => {
    const count = this.heap.length;
    const root = this.heap[0];

    if (count <= 0) return;

    if (count === 1) {
      this.heap = [];
    } else {
      this.heap[0] = this.heap.pop();
      this.heapifyDown();
    }

    return root;
  };

  heapifyUp = () => {
    let index = this.heap.length - 1;
    const insertedNode = this.heap[index];

    while (index > 0) {
      const parent = this.getParent(index);

      if (this.heap[parent].key > insertedNode.key) {
        this.heap[index] = this.heap[parent];
        index = parent;
      } else {
        break;
      }
    }

    this.heap[index] = insertedNode;
  };

  heapifyDown = () => {
    let index = 0;
    const count = this.heap.length;
    const root = this.heap[index];

    while (this.getLeftChild(index) < count) {
      const leftChild = this.getLeftChild(index);
      const rightChild = this.getRightChild(index);

      let minChild;

      if (
        rightChild < count &&
        this.heap[rightChild].key < this.heap[leftChild].key
      ) {
        minChild = rightChild;
      } else {
        minChild = leftChild;
      }

      if (this.heap[minChild].key <= root.key) {
        this.heap[index] = this.heap[minChild];
        index = minChild;
      } else {
        break;
      }
    }

    this.heap[index] = root;
  };
}

class PriorityQueue extends Heap {
  constructor() {
    super();
  }

  enqueue = (priority, value) => {
    this.insert(priority, value);
  };

  dequeue = () => {
    return this.remove();
  };

  isEmpty = () => {
    return this.heap.length <= 0;
  };
}

function dijkstra(n, graph, start, end, traps) {
  const pq = new PriorityQueue();
  const visited = Array(n + 1).fill(0).map((_) => []);

  // 비트 마스크 초기화
  // 각 비트는 함정의 on/off 여부를 나타냄 (1 = 함정 on, 0 = 함정 off)
  for (let i = 1; i < n + 1; i++) {
    for (let j = 0; j < 1 << traps.length; j++) {
      visited[i][j] = 0;
    }
  }

  // (누적 시간, [방 번호, 함정 상태]) 형태로 우선순위 큐에 삽입
  pq.enqueue(0, [start, 0]);

  while (!pq.isEmpty()) {
    const curr = pq.dequeue();
    const w = curr.key; // 누적 시간
    const u = curr.value[0]; // 방 번호
    let state = curr.value[1]; // 각 함정의 on/off 여부

    // 도착
    if (u === end) {
      return w;
    }

    // '방 번호'와, '함정의 상태' 두가지 요소로 방문 여부를 판단
    if (visited[u][state]) {
      continue;
    }

    visited[u][state] = true;

    let currTrapped = false;
    const trapped = new Map();

    for (let i = 0; i < traps.length; i++) {
      const bit = 1 << i;
      // 해당 비트의 함정 on일 경우
      if (state & bit) {
        if (traps[i] === u) {
          // 함정 재방문 - 해당 비트를 함정 off로 변경
          state = state & ~bit;
        } else {
          // 현재 on 되어있는 함정들 저장
          trapped.set(traps[i], true);
        }
      } else {
        if (traps[i] === u) {
          // 함정 첫방문 - 해당 비트를 함정 on으로 변경
          state = state | bit;
          // 현재 방의 함정 on
          currTrapped = true;
          // 현재 on 되어있는 함정들 저장
          trapped.set(traps[i], true);
        }
      }
    }

    for (let v = 1; v < n + 1; v++) {
      if (v === u) {
        continue;
      }

      // 다음 이동할 방이 on되어 있는 함정인지 확인
      const nextTrapped = trapped.has(v) ? true : false;

      if (currTrapped === nextTrapped) {
        // 현재 방과 다음 방 모두 함정이거나, 함정이 아닐 경우 - 정방향
        if (graph[u][v] !== Infinity) {
          pq.enqueue(w + graph[u][v], [v, state]);
        }
      } else {
        // 현재 방과 다음 방 중 하나라도 함정일 경우 - 역방향
        // 역방향은 row와 col을 반대로
        if (graph[v][u] !== Infinity) {
          pq.enqueue(w + graph[v][u], [v, state]);
        }
      }
    }
  }

  return Infinity;
}

function solution(n, start, end, roads, traps) {
  // 인접 행렬 그래프 생성
  const graph = Array(n + 1).fill(0)
    .map((_) => Array(n + 1).fill(Infinity));

  roads.forEach(([v1, v2, w]) => {
    // 두 방 사이 연결된 같은 방향 길 중 최소 시간 길 선택
    graph[v1][v2] = Math.min(graph[v1][v2], w);
  });

  return dijkstra(n, graph, start, end, traps);
}