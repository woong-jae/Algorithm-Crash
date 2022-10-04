const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

class Heap {
  constructor(max = true) {
    this.heap = [];
    this.compare = (v1, v2) => {
      return max ? v1 < v2 : v1 > v2;
    }
  }
  push(newValue) {
    const heap = this.heap;
    heap.push(newValue);
    let index = heap.length - 1, parent = Math.floor((index - 1) / 2);
    while (index > 0 && this.compare(heap[parent], heap[index])) {
      [heap[parent], heap[index]] = [heap[index], heap[parent]];
      index = parent;
      parent = Math.floor((index - 1) / 2);
    }
  }
  pop() {
    const heap = this.heap;
    if (heap.length <= 1) return heap.pop();
    const ret = heap[0];
    heap[0] = heap.pop();
    let here = 0;
    while (1) {
      let left = here * 2 + 1, right = here * 2 + 2;
      // 리프에 도달
      if (left >= heap.length) break;
      // heap[here]가 내려갈 위치를 찾는다.
      let next = here;
      if (this.compare(heap[next], heap[left])) next = left;
      if (right < heap.length && this.compare(heap[next], heap[right])) next = right;
      if (next === here) break;
      [heap[here], heap[next]] = [heap[next], heap[here]];
      here = next;
    }
    return ret;
  }
}

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
      }
      else {
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

const dpq = new DoublePriorityQueue();
const result = [];
let T = null, k = null, count = 0;
rl.on('line', function (line) {
  if (!T) {
    T = +line;
    return;
  }
  if (!k) {
    k = +line;
    return;
  }

  const [command, value] = line.split(" ");
  if (command === "D") {
    dpq.pop(value);
  }
  else {
    dpq.push(+value);
  }


  if (k === ++count) {
    k = null;
    const max = dpq.pop("1"), min = dpq.pop("-1") || max;
    result.push(max ? `${max} ${min}` : "EMPTY");
    dpq.reset();
    count = 0;
  }
}).on("close", function () {
  console.log(result.join("\n"));
  process.exit();
});