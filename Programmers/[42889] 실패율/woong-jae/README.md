# [42889] 실패율
## Algorithm
- Sort
## Logic
각 스테이지마다 아직 클리어하지 못한 플레이어와 클이어한 플레이어의 수를 센 후, 각 스테이지별 실패율을 구한다.

```js
const player = Array(N).fill(0);
const cleared = Array(N).fill(0);

stages.forEach(stage => {
    player[stage - 1]++;
    for(let i = 0; i < stage; i++) {
        cleared[i]++;
    }
});
const get_failure_rate = stage => {
    return cleared[stage] ? player[stage] / cleared[stage] : 0;
}
const failure_rates = Array.from(Array(N), (_, i) => get_failure_rate(i));
```

실패울에 따라 스테이지를 내림차순 정렬한다.
## Review
시키는 대로 구현하면 되는 쉬운 문제.