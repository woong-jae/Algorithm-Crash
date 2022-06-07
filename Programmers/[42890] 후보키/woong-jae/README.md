# [42890] 후보키
## Algorithm
- Bitmask
## Logic
길이가 1인부터 최대 크기까지인 조합까지 하나하나 최소성과 유일성을 만족하는지 확인한다.
조합은 비트마스크로 표현했다.

최소성은 이전에 찾은 후보키들과 비교해서 찾은 후보키가 현재 조합에 포함되어 있는지 확인한다.
`(후보키) & (현재 조합)`이 `(후보키)`와 같다면 포함되어 있다는 것을 알 수 있다.

유일성은 비트마스크에 해당하는 컬럼들끼리 하나의 문자열로 뭉친 후 `Set`에 넣은 후, `Set`의 사이즈와 전체 열의 수를 비교해서 얻을 수 있다.


```js
const isCandidate = picked => {
    const check = new Set();
    // 최소성 확인
    let notMinimal = false;
    answer.forEach(pick => {
        if((pick & picked) === pick) notMinimal = true;
    })
    if(notMinimal) return false;
    
    // 유일성 확인
    relation.forEach(row => {
        check.add(row.reduce((prev, cur, idx) => picked & (1 << idx) ? prev + cur : prev, ""));
    });
    return check.size === relation.length;
}
```

## Review
최소성을 확인하는 부분에서 엄청 해맸다... 역시 구현 문제에 약한걸 또 느낀다...