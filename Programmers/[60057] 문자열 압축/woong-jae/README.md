# [60057] 문자열 압축
## Algorithm
- Bruteforce
## Logic
단위를 1부터 s의 절반까지해서 모든 압축 문자열을 구하면서 최소 길이를 구한다.
```js
const compress = (s, unit) => {
    const compressed = [];
    let lastChar = s.slice(0, unit), count = 1;
    
    for(let i = unit; i < s.length; i += unit) {
        const curChar = s.slice(i, i + unit);
        if(curChar === lastChar) {
            count++;
            continue;
        }
        if(count > 1) compressed.push(count);
        compressed.push(lastChar);
        lastChar = curChar;
        count = 1;
    }
    if(count > 1) compressed.push(count);
    compressed.push(lastChar);
    
    return compressed.join("");
}

for(let unit = 1; unit < s.length / 2 + 1; unit++) {
    const compressed = compress(s, unit);
    answer = Math.min(answer, compressed.length);
}
```
## Review
쉬운 문제. 지금은 전체 문자열을 구하도록 구현을 했는데, 길이만 센다면 시간을 더 단축할 수 있을 것 같다.