# [17684] 압축
## Algorithm
- Map
## Logic
Map과 사전에 등록한 단어의 최대 길이를 저장해 현재 입력과 일치하는 문자열을 효율적으로 찾는다.

```js
while(msg.length) {
    for(let length = maxLength; length > 0; length--) {
        const candidate = msg.slice(0, length);
        if(!dic.has(candidate)) continue;
        // w 찾음
        answer.push(dic.get(candidate));
        msg = msg.slice(length);
        if(msg.length) {
            const newWord = candidate + msg[0];
            dic.set(newWord, dic.size + 1);
            maxLength = Math.max(maxLength, newWord.length);
        }
        break;
    }
}
```
## Review
그냥 시키는대로 하면 풀 수 있는 문제.