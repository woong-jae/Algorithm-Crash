function solution(lines) {
    let answer = 1;
    
    lines = lines.map(line => {
        let [D, S, T] = line.split(" ");
        const start = new Date(D + "T" + S), end = new Date(D + "T" + S);
        let [sec, milli = 0] = T.slice(0, T.length - 1).split(".").map(num => +num);
        start.setSeconds(start.getSeconds() - sec, start.getMilliseconds() - milli + 1);
        return [start, end];
    });
    
    for(let i = 0; i < lines.length; i++) {
        let count = 1;
        const end = lines[i][1];
        end.setSeconds(end.getSeconds() + 1);
        for(let j = i + 1; j < lines.length; j++) {
            if(lines[j][0] < end) count++;
        }
        answer = Math.max(count, answer);
    }
    
    return answer;
}