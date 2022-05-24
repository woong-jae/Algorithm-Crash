# [16472] 고냥이
## Algorithm
- Two Pointer
## Logic
앞부터 시작해서 `Map`에 알파벳을 기록하다가, `Map`에 있는 원소가 `N`보다 커지면 최대값을 갱신한다.

그 다음에는 `Map`에 있는 원소가 `N`보다 작아질 때까지 앞부터 알파벳을 하나씩 뺀다.

이 두 과정을 반복하면 된다.

```js
function solution([N, string]) {
    const map = new Map();
    let maxLength = 0;
    let left = 0, right = 0;
    while(1) {
        if(right >= string.length) {
            maxLength = Math.max(maxLength, right - left);
            break;
        }
        const rightChar = string[right++];
        if(!map.has(rightChar)) map.set(rightChar, 0);
        map.set(rightChar, map.get(rightChar) + 1);
        if(map.size > N) {
            maxLength = Math.max(maxLength, right - left - 1);
            while(left < right && map.size > N) {
                const leftChar = string[left++];
                map.set(leftChar, map.get(leftChar) - 1);
                if(map.get(leftChar) === 0) map.delete(leftChar);
            }
        }
    }

    console.log(maxLength);
}
```
## Review
투 포인터를 알고 있으면 쉽게 풀 수 있는 문제.