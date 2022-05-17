# [17676] 추석 트래픽
## Algorithm
- Greedy
## Logic
`i`번째 로그부터 시작했을 때, `i` 의 응답완료시간보다 `i + n`의 로그의 시작시간이 빠르면 카운트 해준다.

이때 주의해야할 점은 조건에 만족하지 않는다고 끝까지 순회하지 않으면 안된다. 왜냐하면 응답완료시간은 늦어도 시작시간은 더 빠를 수도 있기 때문이다.
```js
for(let i = 0; i < lines.length; i++) {
    let count = 1;
    const end = lines[i][1];
    end.setSeconds(end.getSeconds() + 1);
    for(let j = i + 1; j < lines.length; j++) {
        if(lines[j][0] < end) count++;
    }
    answer = Math.max(count, answer);
}
```

## Review
`Date`를 사용하는 문제는 처음 풀어봐서 검색하느라 좀 시간을 소모했다.
그래도 덕분에 다음에 비슷한 문제가 나오면 빨리 풀 수 있을 듯.