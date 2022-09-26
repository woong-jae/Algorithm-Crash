const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const input = [];
rl.on('line', function (line) {
    input.push(line.split(' '));
}).on("close", function () {
    console.log(solution(input[0][0]));
    process.exit();
});

function solution(input) {
    const N = input.length;

    const isPalindrome = Array.from(Array(N), () => Array(N).fill(false));
    for(let mid = 0; mid < N; mid++) {
        let left = mid, right = mid;
        while(left >= 0 && right < N && input[left] === input[right]) {
            isPalindrome[left--][right++] = true;
        }
        
        left = mid, right = mid + 1;
        while(left >= 0 && right < N && input[left] === input[right]) {
            isPalindrome[left--][right++] = true;
        }
    }

    const dp = Array(N + 1).fill(Infinity);
    dp[N - 1] = 1;
    dp[N] = 0;
    for(let start = N - 2; start >= 0; start--) {
        for(let end = start; end < N; end++) {
            if(!isPalindrome[start][end]) continue;
            dp[start] = Math.min(dp[start], dp[end + 1] + 1);
        }
    }

    return dp[0];
}