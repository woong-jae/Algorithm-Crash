# [67259] 경주로 건설
## Algorithm
- DP
- BFS
## Logic
처음에는 한 정점에서 다른 정점까지 최소 거리를 구하는 문제여서 **다익스트라**로 접근했다.

하지만 각 격자에 어느 방향으로 들어왔냐에 따라 비용이 달라지는 문제점이 발생했다.

이를 처리하기 위해서는 방항마다 달리 상태(수직, 가로)를 저장해줘야 한다. 이를 처리하기 위해 상태마다 배열을 따로뒀다.

이제 상태별로 배열을 선택하고, 현재 값이 배열의 값보다 작으면 갱신해주면 된다.

```js
const pq = [{ r: 0, c: 0, v: 0, d: null }];
while(pq.length) {
    const {r, c, v, d} = pq.shift();
    
    for(let distanceIndex = 0; distanceIndex < 4; distanceIndex++) {
        if(distanceIndex === d) continue;
        const nr = r + dr[distanceIndex], nc = c + dc[distanceIndex];
        if(isValid(nr, nc, N) && board[nr][nc] !== 1) {
            let nextVal = v + (d === null ? 100 : Math.abs(distanceIndex - d) % 2 ? 600 : 100);
            let type = (distanceIndex + 2) % 2;
            
            if(cache[type][nr][nc] > nextVal) {
                cache[type][nr][nc] = nextVal;
                pq.push({ r: nr, c: nc, v: nextVal, d: (distanceIndex + 2) % 4 });
            }
        }
    }
}
```

## Review
처음에 DP를 생각했지만 재귀로는 풀 수 없을 것 같아 다른 알고리즘으로 생각을 돌렸다.
그래서 한 점에서 다른 점까지 최소 거리를 찾는 문제니 다익스트라를 쓰면 되겠다고 생각했다.
하지만 계속 두 케이스가 틀려서 해메다 결국 다른 사람의 생각을 참고했다.

상태에 따라 배열을 분리하지 않아서 계속 오답이 나왔던 것이다... 결국 배열을 분리하니 DP + BFS 문제가 됐다.
카카오 고레벨 문제는 약간 수능 21번 느낌인 것 같다. 알고리즘 하나가 아니라 여러개를 응용할 수 있는 능력이 필요하다.
