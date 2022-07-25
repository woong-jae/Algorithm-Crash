# [92342] 양궁대회
## Algorithm
- Combination
## Logic
10점부터 시작해서 고를지 안고를지 선택한다.

고를때는 아직 안쏜 화살의 개수가 `info[index]`의 개수보다 커야 고를 수 있다.

```js
// 고르는 경우
if(info[index] < n) {
    picked.add(index);
    getMaxScore(index + 1, n - (info[index] + 1), picked);
    picked.delete(index);
}
// 안고르는 경우
getMaxScore(index + 1, n, picked);
```

고르다가 `index`가 10을 넘어가거나, 화살을 다쐈다면 '점수 차이'를 계산해보고 정답을 갱신해준다.

```js
if(index === info.length || n === 0) {
    const peach_score = info.reduce((prev, cur, i) => {
        if(cur > 0 && !picked.has(i)) prev += (10 - i);
        return prev;
    }, 0);
    const ryan_score = Array.from(picked).reduce((prev, cur) => prev + (10 - cur), 0);
    
    const diff = ryan_score - peach_score
    if(diff <= 0) return;
    
    const ryan_info = info.map((elem, i) => picked.has(i) ? elem + 1 : 0);
    if(n > 0) ryan_info[10] += n;
    
    if(diff > max_score_diff) {
        max_score_diff = diff;
        max_info = ryan_info;
    }
    else if(diff === max_score_diff) {
        for(let i = 10; i >= 0; i--) {
            if(ryan_info[i] === max_info[i]) continue;
            
            if(ryan_info[i] > max_info[i]) max_info = ryan_info;
            break;
        }
    }
        
    return;
}
```

## Review
다 풀어놓고 문제를 제대로 안읽어서 한참 걸린 문제. 나는 내가 구현을 잘못한 줄 알았다.
처음에는 라이언의 점수가 최대가 되는 정답을 구했는데, 문제를 제대로 읽어보니 '점수 차이'가 최대가 되는 것을 구하는 문제였다...
무친놈...