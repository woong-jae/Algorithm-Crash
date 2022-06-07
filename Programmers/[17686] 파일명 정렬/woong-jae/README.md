# [17686] 파일명 정렬
## Algorithm
- Sort
- Regx
## Logic
파일명을 HEAD와 NUMBER로 분리한 다음 기준에 따라 정렬한다.
```js
const splitFileName = fileName => {
    let matched = fileName.match(/\d+/);
    return [fileName.slice(0, matched.index).toLowerCase() , +matched];
}
```
## Review
쉬운 문제. 이번주 문제는 전반적으로 다 쉬운 것 같다.
