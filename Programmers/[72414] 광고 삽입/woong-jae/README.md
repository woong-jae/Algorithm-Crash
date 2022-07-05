# [72414] 광고 삽입
## Algorithm
- Prefix sum
## Logic
1. 로그를 초 단위로 파싱한다.
2. play_time 이내의 유저 수의 누적합을 구한다.

```js
const user = Array(play_time + 1).fill(0);
    
parsed_logs.forEach(([start, end]) => {
    user[start]++;
    user[end]--;
});

for(let i = 1; i <= play_time; i++) user[i] += user[i - 1];

for(let i = 1; i <= play_time; i++) user[i] += user[i - 1];
```

3. adv_time만큼 윈도우를 유지하면서 최대 누적합을 가지는 구간의 시작시간을 구한다.

```js
let max_sum = user[adv_time - 1], best_start_time = 0;
for(let i = adv_time; i <= play_time; i++) {
    const sum = user[i] - user[i - adv_time];
    if(sum > max_sum) {
        max_sum = sum;
        best_start_time = i - adv_time + 1;
    }
}
```

## Review
이번주 문제중 젤 어려웠던 문제인듯... 시간을 초 단위로 바꿔주는 것과 슬라이딩 윈도우를 사용하는 것 까지는 했는데, 누적합을 구하는 것을 생각해내지 못해서 결국 다른 사람의 코드를 참고했다.

카카오 문제중에 시간 + 문자열 + 구간 콤보가 나오면 어지러운 것 같다.