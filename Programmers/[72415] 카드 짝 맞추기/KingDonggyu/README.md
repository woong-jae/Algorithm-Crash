# [72415] 카드 짝 맞추기

## Algorithm

- Brute Force (Permutation)

- BFS

- Bit Masking

## Logic

- 존재하는 카드의 값에 따른 비트 마스킹을 수행한다.

  - 카드 제거시 해당 비트를 1로 변환하고, 모든 카드 값에 대한 비트가 모두 1이 되면 정답을 반환한다.

<br />

```js
const permutate = (cnt, removed, src) => {
  // 모든 카드 제거
  if (removed === allRemoved) {
    answer = Math.min(answer, cnt);
    return;
  }

  for (const [card, pos] of cardPos.entries()) {
    // 이미 제거한 카드
    if (removed & (1 << card)) {
      continue;
    }

    const one = bfs(removed, src, pos[0]) + bfs(removed, pos[0], pos[1]) + 2;
    const two = bfs(removed, src, pos[1]) + bfs(removed, pos[1], pos[0]) + 2;

    permutate(cnt + one, removed | (1 << card), pos[1]);
    permutate(cnt + two, removed | (1 << card), pos[0]);
  }
};
```

- 어떤 값의 카드를 먼저 제거할 것인지 순열에 따른 모든 순서를 탐색한다.

  - 같은 값의 두 카드 a, b가 있을때 a -> b, b -> a 순서 모두 수행해야 한다.

<br />

- 주어진 출발지와 목적지에 따른 BFS를 수행한다.

  - 큐에 키 조작 횟수를 함께 담아 업데이트하며, 목적지에 도착시 해당 조작 횟수를 반환한다.

### 시간 복잡도 : O(N!)

## Review

처음에는 모든 이동(한칸 이동, [Ctrl] 이동)에 대한 BFS를 수행하며, 매 이동시의 board 상태, 커서 위치, 뒤집은 카드 위치를 Set에 넣어 방문 여부를 제어했다.

하지만 자꾸만 틀렸다.. (아직도 틀린 원인을 모르겠다)

결국 해결하지 못하여 해당 문제에 대한 풀이 영상을 참고하여 순열과 비트 마스킹을 이용했다.

이번주 첫 문제부터 많이 힘드네..