# [92335] k진수에서 소수 개수 구하기
## Algorithm
- Prime number
## Logic
`n`을 `k` 진수 수로 바꾼 후, 0을 기준으로 `split`해서 각 숫자가 소수인지 판별하면 된다.

소수 판별은 2부터 루트 n까지 확인하는 방법으로 구한다.

```js
const isPrime = num => {
    if(num < 2) return false;
    for(let i = 2; i * i <= num; i++) {
        if(num % i === 0) return false;
    }
    return true;
}
```
## Review
소수 판별법을 몰라서 찾아본것 빼고는 너무 쉬운 문제. 소수 판별은 꽤 자주 나오는 문제니 외워둬야겠다.