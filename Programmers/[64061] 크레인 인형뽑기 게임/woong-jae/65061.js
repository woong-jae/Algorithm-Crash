function solution(board, moves) {
    let answer = 0;
    
    // 각 열의 최상단 index를 구함
    const tops = [];
    for(let col = 0; col < board[0].length; col++) {
        let start = 0, end = board.length - 1;
        while(start <= end) {
            const mid = Math.floor((start + end) / 2);
            if(board[mid][col]) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        tops[col] = start;
    }
    
    const stack = [];
    moves.forEach(move => {
        const top = tops[move - 1];
        if(top < board.length) {
            const item = board[top][move - 1];
            if(stack.length && stack[stack.length - 1] === item) {
                stack.pop();
                answer += 2;
            }
            else {
                stack.push(item);
            }
            tops[move - 1]++;
        }
    });
    
    return answer;
}