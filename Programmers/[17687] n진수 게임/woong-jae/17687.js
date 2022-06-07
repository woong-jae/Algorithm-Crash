function solution(n, t, m, p) {
    const answer = [];
    
    let number = 0, current = "", turn = 0;
    while(answer.length < t) {
        if(!current) {
            current = number.toString(n).toUpperCase();
            number++;
        }
        if(turn === p - 1) answer.push(current[0]);
        current = current.slice(1);
        turn = (turn + 1) % m;
    }
    
    return answer.join("");
}