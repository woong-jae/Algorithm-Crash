function solution(msg) {
    const answer = [];
    
    const initial = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("").map((char, index) => [char, index + 1]);
    const dic = new Map(initial)
    let maxLength = 1;
    
    while(msg.length) {
        for(let length = maxLength; length > 0; length--) {
            const candidate = msg.slice(0, length);
            if(!dic.has(candidate)) continue;
            // w 찾음
            answer.push(dic.get(candidate));
            msg = msg.slice(length);
            if(msg.length) {
                const newWord = candidate + msg[0];
                dic.set(newWord, dic.size + 1);
                maxLength = Math.max(maxLength, newWord.length);
            }
            break;
        }
    }
    
    return answer;
}