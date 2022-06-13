function solution(s) {
    let answer = s.length;
    
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
    
    return answer;
}