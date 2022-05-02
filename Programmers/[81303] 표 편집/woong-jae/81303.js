class ListNode {
    constructor(n, prev = null, next = null) {
        this.value = n;
        this.prev = prev;
        this.next = next;
    }
}

function solution(n, k, cmd) {
    let head = null, tail = null;
    for(let i = 0; i < n; i++) {
        if(!head) {
            head = new ListNode(i);
            tail = head;
        }
        else {
            tail.next = new ListNode(i, tail);
            tail = tail.next;
        }
    }
    
    const stack = [];
    let cur = head;
    while(k--) cur = cur.next;
    cmd.forEach(command => {
        let [dir, x] = command.split(" ");
        if(dir === "C") {
            stack.push(cur);
            if(cur.prev) cur.prev.next = cur.next;
            if(cur.next) {
                cur.next.prev = cur.prev;
                cur = cur.next;
            }
            else cur = cur.prev;
        }
        else if(dir === "Z") {
            const restore = stack.pop();
            if(restore.next) restore.next.prev = restore;
            if(restore.prev) restore.prev.next = restore;
        }
        else {
            if(dir === "U") while(x--) cur = cur.prev;
            else while(x--) cur = cur.next;
        }
    });
    
    let answer = Array(n).fill("O");
    stack.forEach(({value}) => {
       answer[value] = "X";
    });
    
    return answer.join("");
}