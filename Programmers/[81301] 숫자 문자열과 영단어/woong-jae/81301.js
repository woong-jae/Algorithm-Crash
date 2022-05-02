function solution(s) {
    const answer = [];
    const isNum = /\d/;
    
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
    
    let index = 0;
    while(index < s.length) {
        const char = s[index];
        if(isNum.test(char)) {
            answer.push(char);
            index++;
        }
        else {
            const [number, step] = getNumber(index);
            answer.push(number);
            index += step;
        }
    }
    
    return +answer.join("");
}