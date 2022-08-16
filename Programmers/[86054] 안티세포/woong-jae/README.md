# [86054] 안티세포

## Algorithm

- Prefix Sum
- Disjoint Set

## Logic

1. `i`에서 시작하는 세포 X의 시작점을 찾는다. Union-Find를 이용해서 합쳐진 세포를 표현한다.

2. X의 시작점이 0보다 크면 Y를 찾고, prefix sum을 이용해 X와 Y의 합을 비교한다. 같다면 X와 Y를 합치고 다시 시뮬레이션한다.

```js
const simulate = (i, root) => {
  if (i >= b.length) {
    states++;
    return;
  }

  const x = find(i, root);
  if (x > 0) {
    const y = find(x - 1, root);

    if (sum(x, i) === sum(y, x - 1)) {
      const next_root = root.slice();
      merge(y, x, next_root);

      simulate(i, next_root);
    }
  }

  simulate(i + 1, root);
};
```

시간, 메모리 초과...

## Review
도저히 못풀겠어서 답을 보니까 prefix sum을 사용하는 것까지는 맞췄는데, DP는 상상도 못했다. 여기서 어떻게 DP를 적용하지...

푼 사람이 있으면 그 사람 설명을 들어야겠다.