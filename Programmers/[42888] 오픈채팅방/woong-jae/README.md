# [42888] 오픈채팅방
## Algorithm
- Map
## Logic
기록이 "Enter"나 "Leave"면 해당 `uid`와 `action`을 stack에 저장하면서 해당 uid의 마지막 닉네임을 계속 기록한다.

```js
record.forEach(rec => {
    const [action, uid, nickname] = rec.split(" ");
    if(!lastNickname.has(uid)) lastNickname.set(uid, nickname);
    
    switch(action) {
        case "Enter":
            if(lastNickname.get(uid) !== nickname) lastNickname.set(uid, nickname);
        case "Leave":
            stack.push({ uid, action });
            break;
        case "Change":
            lastNickname.set(uid, nickname);
            break;
    }
});
```

마지막에 `action`과 `uid`의 닉네임에 해당하는 메시지를 출력한다.
## Review
쉬운 문제.