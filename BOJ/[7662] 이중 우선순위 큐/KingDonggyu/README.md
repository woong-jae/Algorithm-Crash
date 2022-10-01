# [7662] 이중 우선순위 큐

## Algorithm
- Heap

## Logic
- MinHeap과 MaxHeap을 각각 만든다.
- 두 heap이 공유할 `heapSize`를 정의한다.
- insert시 두 heap에 모두 값을 enqueue한 후, `heapSize`를 + 1 한다.
- delete시 두 heap 중 하나의 값을 dequeue한 후, `heapSize`를 - 1 한다.

### 시간복잡도 : O(KlogN) K: 연산의 개수

## Review
메모리 초과..

이거 Node.js로 풀 수는 있는 건가..?

검색해보니 전부 메모리 초과 나온다던데, 갓재웅의 코드를 참고해봐야겠다.