# [81301] 숫자 문자열과 영단어
## Algorithm
- 없음
## Logic
문제에서 원하는대로 문자열을 읽어가며 숫자를 만들어주면 된다.

숫자가 아닌 문자가 나왔을 때는 문자에 따라 숫자로 변환해준다.

앞 글자가 무엇이냐에 따라 대응되는 영단어와 영단어의 길이를 알 수 있기 때문에 이를 토대로 숫자와 다음 인덱스를 계산한다.
```js
const getNumber = (index) => {
    const startingChar = s[index];
    if(startingChar === "z") return ["0", 4];
    if(startingChar === "o") return ["1", 3];
    if(startingChar === "t") {
        if(s[index + 1] === "w") return ["2", 3];
        return ["3", 5];
    }
    if(startingChar === "f") {
        if(s[index + 1] === "o") return ["4", 4];
        return ["5", 4];
    }
    if(startingChar === "s") {
        if(s[index + 1] === "i") return ["6", 3];
        return ["7", 5];
    }
    if(startingChar === "e") return ["8", 5];
    return ["9", 4];
};
```

## Review
쉬운 문제.