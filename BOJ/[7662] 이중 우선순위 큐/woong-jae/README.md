# [7662] 이중 우선순위 큐

## Algorithm

- Heap
- Map

## Logic

최대 힙과 최소 힙 그리고 맵을 사용한다.

삽입할 때는 두 힙에 모두 넣는다. 이때, 맵에 넣은 숫자의 개수를 기억한다.

삭제할 때는 해당하는 힙에서 맵에 기록된 숫자가 나올 때까지 빼준다.

```js
class DoublePriorityQueue {
  constructor() {
    this.maxHeap = new Heap();
    this.minHeap = new Heap(false);
    this.numCount = new Map();
  }
  push(newValue) {
    this.maxHeap.push(newValue);
    this.minHeap.push(newValue);
    if (!this.numCount.has(newValue)) this.numCount.set(newValue, 0);
    this.numCount.set(newValue, this.numCount.get(newValue) + 1);
  }
  pop(flag) {
    const heap = flag === "1" ? this.maxHeap : this.minHeap;
    while (heap.heap.length) {
      const value = heap.pop();
      if (!this.numCount.has(value)) continue;

      if (this.numCount.get(value) === 1) {
        this.numCount.delete(value);
      } else {
        this.numCount.set(value, this.numCount.get(value) - 1);
      }
      return value;
    }
  }
  reset() {
    this.maxHeap.heap = [];
    this.minHeap.heap = [];
    this.numCount.clear();
  }
}
```
## Review
미친 억까 문제. 자꾸 메모리 초과가 나서 포기하려 했다. 근데 문제를 보니 인풋의 길이가 최대 백만이라고 해서, 혹시 인풋 값 때문에 메모리가 터지는 거 아닐까라는 생각을 했다. 그리고 내 예상이 맞았다. 인풋을 한 번에 다 받고 처리하지 않고 하나씩 처리하니 통과됐다. 하...