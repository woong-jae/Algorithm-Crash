# [49995] 쿠키 구입

## Algorithm

- Two pointer

## Logic

중간을 기준으로 좌와 우를 점점 늘려가면서 구간의 합이 같아지는 때를 확인한다.

```js
for (let mid = 0; mid < N - 1; mid++) {
  let left = mid,
    right = mid + 1;
  let leftSum = cookie[left],
    rightSum = cookie[right];

  while (1) {
    if (leftSum === rightSum) result = Math.max(result, rightSum);
    if (leftSum <= rightSum) {
      if (left === 0) break;
      leftSum += cookie[--left];
    } else {
      if (right === N - 1) break;
      rightSum += cookie[++right];
    }
  }
}
```

## Review

처음에 완전탐색으로 해서 $O(N^3)$ 시간복잡도를 가져서 효율성 통과를 못했다. 잘 생각해보니까 투포인터 문제 같아서 적용했고, 바로 풀 수 있었다.
4번이 더 어려웠음...