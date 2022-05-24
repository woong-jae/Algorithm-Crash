# [64064] 불량 사용자
## Algorithm
- Bit Mask
- Set
## Logic
각 불량 사용자 아이디마다 응모자 아이디를 선택해 조합을 만든다.

이때 비트마스크로 이미 제재된 아이디인지 확인해서, 불량 사용자 아이디와 일치하고 선택하지 않았다면 선택한다.

```js
const getCombs = (banned_idx, banned) => {
    if(banned_idx >= banned_id.length) {
        combs.add(banned);
        return;
    };
    const bid = banned_id[banned_idx];
    user_id.forEach((uid, uidIdx) => {
        if(uid.length !== bid.length || banned & (1 << uidIdx)) return;
        for(let index = 0; index < uid.length; index++) {
            if(bid[index] === "*") continue;
            if(uid[index] !== bid[index]) return;
        }
        getCombs(banned_idx + 1, banned ^ (1 << uidIdx));
    });
}
```

## Review
비트마스크를 사용하면 쉬운 문제.