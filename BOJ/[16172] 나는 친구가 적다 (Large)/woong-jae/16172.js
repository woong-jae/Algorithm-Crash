const readline = require('readline');
const input = [];

readline.createInterface({
    input: process.stdin,
    output: process.stdout
}).on('line', (line) => {
    input.push(line);
}).on('close', () => {
    console.log(solution());
    process.exit();
});

function solution() {
    let [S, K] = input;

    const getPartialMatch = S => {
        const pi = Array(S.length).fill(0);
        let begin = 0, matched = 0;
        while(begin + matched < S.length) {
            if(S[begin + matched] === S[matched]) {
                matched++;
                pi[begin + matched] = matched;
            }
            else {
                if(matched === 0) begin++;
                else {
                    begin += matched - pi[matched - 1];
                    matched = pi[matched] - 1;
                }
            }
        }
        return pi;
    }

    S = S.split("").filter(char => isNaN(parseInt(char))).join("");

    const pi = getPartialMatch(K);
    let begin = 0, matched = 0;
    while(begin <= S.length - K.length) {
        if(matched < K.length && S[begin + matched] === K[matched]) {
            if(++matched === K.length) return 1;
        }
        else {
            if(matched === 0) begin++;
            else {
                begin += matched - pi[matched - 1];
                matched = pi[matched - 1];
            }
        }
    }

    return 0;
}
