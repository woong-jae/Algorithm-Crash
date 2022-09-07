# [68646] 풍선 터트리기

## Algorithm

- Segment tree

## Logic

1. 구간에 대한 최소값을 가지는 인덱스를 저장하는 세그먼트 트리를 만든다.

```js
const segmentTree = Array(4 * a.length);

const init = (start, end, node) => {
  if (start === end) return (segmentTree[node] = start);

  const mid = Math.floor((start + end) / 2);

  const left = init(start, mid, node * 2);
  const right = init(mid + 1, end, node * 2 + 1);
  return (segmentTree[node] = a[left] < a[right] ? left : right);
};
```

2. 전체에서 가장 작은 값을 찾는다.

3. 그 값을 기준으로 왼쪽 끝에 도달할 때까지 작은값을 찾고, 오른쪽도 끝에 도달할 때까지 작은값을 찾는다.

```js
const mid = query(0, a.length - 1);
let leftEnd = mid,
  rightStart = mid;

let result = 1;
while (leftEnd > 0) {
  leftEnd = query(0, leftEnd - 1);
  result++;
}
while (rightStart < a.length - 1) {
  rightStart = query(rightStart + 1, a.length - 1);
  result++;
}
```


## Review
처음에 최소값을 선형적으로 찾아서 시간초과가 났다. 세그먼트 트리를 활용하는 문제라고 생각이 들어서 바로 맞출 수 있었다.
근데 아직은 알고리즘을 안보고 구현을 못하겠다. 어렵구만.