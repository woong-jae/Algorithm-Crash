# [1083] 소트
## Algorithm
- Bubble Sort
## Logic
버블 소트와 비슷하지만, `S`범위 내에서 가장 큰 값을 가져오는 것이 다르다.
```js
for(let left = 0; left < elements.length; left++) {
    let target = left;
    for(let right = left + 1; right <= left + S && right < elements.length; right++) {
        if(elements[target] < elements[right]) {
            target = right;
        }
    }
    if(target === left) continue;
    for(let i = target; i > left; i--) {
        [elements[i], elements[i - 1]] = [elements[i - 1], elements[i]];
    }
    S -= target - left;
    if(S === 0) break;
}
```
## Review
문제 자체는 진짜 쉬운데, '소트한 결과가 사전순으로 가장 뒷서는 것' 때문에 해맸다.
이게 문자열 기준인지, 숫자 기준인지, 아니면 다 합쳤을 때 기준인지 말을 안해줘서 그렇다.

쓰레기같은 문제ㅋㅋ