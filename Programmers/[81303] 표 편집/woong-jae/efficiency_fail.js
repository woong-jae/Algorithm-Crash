function solution(n, k, cmd) {
    const stack = [];
    cmd.forEach(command => {
        const [dir, x] = command.split(" ");
        if(dir === "C") {
            stack.push(k);
            if(k === n - 1) k--;
            n--;
        }
        else if(dir === "Z") {
            const restore = stack.pop();
            if(restore <= k) k++;
            n++;
        }
        else {
            k = (dir === "U") ? k - +x : k + +x;
        }
    });
    
    let answer = "O".repeat(n);
    while(stack.length) {
        const restoreIndex = stack.pop();
        answer = answer.slice(0, restoreIndex) + "X" + answer.slice(restoreIndex);
    }
    
    return answer;
}