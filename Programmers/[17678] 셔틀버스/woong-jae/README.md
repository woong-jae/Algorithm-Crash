# [17678] 셔틀버스
## Algorithm
- 구현
## Logic
1. 시간을 모두 분으로 처리하게 바꾸고 오름차순으로 정렬한다.
2. 각 운행하는 셔틀마다 들어 갈 수 있는 크루를 넣는다.
3. 제일 늦은 시각을 구한다.

제일 늦은 시각은 제일 늦게 운행하는 셔틀부터 시작해서 찾는다.
- 해당 셔틀이 꽉 찼다면: 셔틀이 도착한 시간과 크루 중 가장 늦게 줄을 선 시간중 작은 값을 후보로 저장한다. 이떄 후보가 이미 있다면 더 큰 값을 가지고 간다.
- 셔틀에 아무도 없다면: 후보와 셔틀이 도착한 시간 중 큰 값을 반환한다.
- 셔틀이 덜 찼다면: 후보, 셔틀이 도착한 시간, 크루가 줄 선 시간들 중 가장 큰 값을 반환한다.

```js
let cand = -Infinity;
for(let nth = lines.length - 1; nth >= 0; nth--) {
    const shuttleTime = 9 * 60 + nth * t;
    if(lines[nth].length === 0) return parseShuttleTime(Math.max(cand, shuttleTime));
    if(lines[nth].length < m) {
        return parseShuttleTime(Math.max(cand, shuttleTime, ...lines[nth]));
    }
    if(lines[nth].length === m) {
        cand = Math.max(cand, Math.min(shuttleTime, Math.max(...lines[nth]) - 1));
    }
}
```


## Review
생각보다 빨리 풀긴 했는데,
이런 구현문제가 제일 어려운 것 같다... 연습하자!