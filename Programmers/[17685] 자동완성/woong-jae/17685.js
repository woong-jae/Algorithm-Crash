class Node {
    constructor() {
        this.count = 0;
        this.childs = new Map();
    }
}

function solution(words) {
    let answer = 0;
    const head = new Node();
    
    // 트라이 구성
    words.forEach(word => {
        let cur = head, depth = 1;
        while(depth <= word.length) {
            const next = word.slice(0, depth);
            if(!cur.childs.has(next)) {
                cur.childs.set(next, new Node());
            }
            cur = cur.childs.get(next);
            cur.count++;
            depth++;
        }
    });
    
    // 입력해야 할 문자수 구하기
    words.forEach(word => {
        let cur = head, depth = 1;
        while(depth < word.length) {
            const next = word.slice(0, depth);
            cur = cur.childs.get(next);
            if(cur.count === 1) break;
            depth++;
        }
        answer += depth;
    });
    
    return answer;
}
