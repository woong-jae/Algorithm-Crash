# [81304] 미로 탈출
## Algorithm
- Dijkstra
- Bitmask
## Logic
이 문제는 trap만 빼고보면 일반적인 다익스트라 문제다. 

Trap을 추가하면 지나온 간선에 따라 '상태'가 생기게 된다.
Trap은 최대 10개이기 때문에 상태를 비트로 저장해서 이동할 간선이 유효한지 확인하고 다익스트라 알고리즘을 실행하면 된다.

```js
const pq = new MinHeap();
pq.push(new Element(start, 0, 0));
dist[start][0] = 0;
while(pq.heap.length) {
    const { cur, time, pressed } = pq.pop();
    if(dist[cur][pressed] < time) continue;
    for(let [next, nextTime, state] of adjList[cur]) {
        if(!isValid(cur, next, pressed, state)) continue;
        const nextPressed = calcPressed(pressed, next);
        if(dist[next][nextPressed] > time + nextTime) {
            dist[next][nextPressed] = time + nextTime;
            pq.push(new Element(next, time + nextTime, nextPressed));
        }
    }
}
```
## Review
다익스트라 문제인 것과 비트마스크를 활용한다는 아이디어는 빨리 잡았지만 구현하는데 하루종일 걸렸다...

카카오 4번, 5번 문제는 그래프나 트리에 상태를 두어서 꼬아내는 것 같다.
실전에서 이걸 시간안에 풀 수 있을까... 특히 자바스크립트는 우선순위 큐를 직접 구현해야하기 때문에 더 골치 아프다. 
화이팅하자.