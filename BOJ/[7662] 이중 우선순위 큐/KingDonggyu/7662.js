const HEAP_TYPE = { MIN: 0, MAX: 1 };

class Heap {
  constructor(type) {
    this.heap = [];
    this.type = type;
  }

  getParent(child) {
    return Math.floor((child - 1) / 2);
  }

  getLeftChild(parent) {
    return parent * 2 + 1;
  }

  getRightChild(parent) {
    return parent * 2 + 2;
  }

  insert(value) {
    this.heap.push(value);
    this.heapifyUp();
  }

  remove() {
    const root = this.heap[0];
    const count = this.heap.length;

    if (!count) {
      return;
    }

    if (count === 1) {
      this.heap = [];
    } else {
      this.heap[0] = this.heap.pop();
      this.heapifyDown();
    }

    return root;
  }

  heapifyUp() {
    let index = this.heap.length - 1;
    const inserted = this.heap[index];

    while (index > 0) {
      const parent = this.getParent(index);
      const condition = (this.type === HEAP_TYPE.MIN)
        ? this.heap[parent] > inserted
        : this.heap[parent] < inserted;

      if (!condition) {
        break;
      }

      this.heap[index] = this.heap[parent];
      index = parent;
    }

    this.heap[index] = inserted;
  }

  heapifyDown() {
    let index = 0;
    const root = this.heap[index];
    const count = this.heap.length;

    while (this.getLeftChild(index) < count) {
      const leftChild = this.getLeftChild(index);
      const rightChild = this.getRightChild(index);

      let condition = (this.type === HEAP_TYPE.MIN)
        ? this.heap[rightChild] <= this.heap[leftChild]
        : this.heap[rightChild] >= this.heap[leftChild];

      const selectd = (rightChild < count && condition )
        ? rightChild
        : leftChild;

      condition = (this.type === HEAP_TYPE.MIN)
        ? this.heap[selectd] < root
        : this.heap[selectd] > root;

      if (!condition) {
        break;
      }

      this.heap[index] = this.heap[selectd];
      index = selectd;
    }

    this.heap[index] = root;
  }
}

function solution(T, testData) {
  if (!T) {
    return;
  }

  const minHeap = new Heap(HEAP_TYPE.MIN);
  const maxHeap = new Heap(HEAP_TYPE.MAX);
  let heapSize = 0;
  let endIndex = null;

  testData = testData.slice(1);

  for (let i = 0; i < testData.length; i++) {
    if (testData[i].length === 1) {
      endIndex = i;
      break;
    }
    const [cmd, value] = testData[i].split(' ');
    if (cmd === 'I') {
      heapSize++;
      minHeap.insert(+value);
      maxHeap.insert(+value);
      continue;
    }
    if (heapSize) {
      heapSize--;
      value === '1' ? maxHeap.remove() : minHeap.remove();
    }
  }

  heapSize
    ? console.log(`${maxHeap.remove()} ${minHeap.remove()}`)
    : console.log('EMPTY');

  solution(T - 1, testData.slice(endIndex));
}

const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const input = [];

rl.on('line', (line) => {
  input.push(line);
}).on('close', () => {
  solution(+input[0], input.slice(1));
  process.exit();
});
