# [92334] 신고 결과 받기
## Algorithm
- Map
## Logic
한 유저를 여러 번 신고했을 때 신고 횟수가 중복되지 않기 때문에 `Set`으로 처리한다.

```js
report.forEach(string => {
    const [did, get] = string.split(" ");
    reported.get(did).add(get);
});
```

신고 당한 횟수를 취합한 후, 각 신고자들이 신고한 유저 중 정지당한 유저를 센다.

```js
reported.forEach(reports => {
     reports.forEach((id, get) => {
         count.set(get, count.get(get) + 1);
     });
});

return Array.from(reported).map(([_, reports]) => {
    let message = Array.from(reports).reduce((prev, cur) => {
        if(count.get(cur) >= k) prev += 1;
        return prev;
    }, 0);
    return message;
});
```