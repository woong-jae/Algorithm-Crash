function solution(p) {
    const isCorrect = p => {
        let count = 0;
        for(const bracket of p) {
            if(bracket === "(") count++;
            else count--;
            if(count < 0) return false;
        }
        return true;
    }

    const getCorrectBracketString = p => {
        if(!p) return "";

        let index = 0, bracketCount = { left: 0, right: 0 };
        while(index < p.length) {
            const bracket = p[index];
            bracketCount[bracket === "(" ? "left" : "right"] += 1;
            index++;
            if(bracketCount.left === bracketCount.right) break;
        }
        const u = p.slice(0, index), v = p.slice(index);

        if(isCorrect(u)) return u + getCorrectBracketString(v);
        return "(" + getCorrectBracketString(v) + ")" + u
            .slice(1, index - 1)
            .split("")
            .map(char => char === ")" ? "(" : ")")
            .join("");
    }

    return getCorrectBracketString(p);
}