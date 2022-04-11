function solution(board, moves) {
    let answer = 0;
    
    // 각 열의 최상단 index를 구함
    const tops = new Array(board[0].length).fill(board.length);
    for(let col = 0; col < board[0].length; col++) {
        for(let row = board.length - 1; row >= 0; row--) {
            const item = board[row][col];
            if(!item) break;
            tops[col] = row;
        }
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