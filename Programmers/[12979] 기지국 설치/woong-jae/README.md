# [12979] 기지국 설치

## Algorithm

- Two pointer

## Logic

빈 공간의 넓이만 안다면 기지국을 몇 개 설치해야하는지 알 수 있다.

따라서 시작점을 1부터 시작해서 빈 공간의 넓이를 찾는다.

만약 시작점이 다음 station의 왼쪽 도달거리보다 크거나 같다면, 시작점이 이미 전파가 도달하고 있다는 뜻이다.
그러므로 시작점을 (station의 오른쪽 도달거리 + 1)로 해주면 된다.

`stations`의 순회가 끝났을 때, 시작점이 n보다 같거나 작다면, 끝에 남은 공간이 있으므로 처리해준다.

```js
stations.forEach((station) => {
  if (start >= station - w) {
    start = station + w + 1;
    return;
  }

  const length = station - w - start;
  result += Math.ceil(length / coverWidth);

  start = station + w + 1;
});

if (start <= n) {
  result += Math.ceil((n + 1 - start) / coverWidth);
}
```

## Review

마지막 처리에 숫자하나 빼먹어서 1번과 15번을 틀렸다. Edge 케이스 빼면 쉽게 아이디어를 생각할 수 있는 문제라고 생각한다.
