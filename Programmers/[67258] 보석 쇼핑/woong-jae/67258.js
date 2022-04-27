function solution(gems) {
    let answer = [0, gems.length - 1];
    const kind = new Set(gems).size;

    let start = 0, cur = 0, picked = new Set();
    while(start < gems.length) {
        while(cur < gems.length && picked.size < kind) {
            picked.add(gems[cur++]);
        }
        if(picked.size === kind) {
            let end = --cur;
            picked.clear();
            while(picked.size < kind) picked.add(gems[cur--]);
            start = cur + 1;
            if(answer[1] - answer[0] > end - start) {
                answer = [start, end];
                if(end - start + 1 === kind) break;
            }
            picked.delete(gems[start++]);
            cur = end + 1;
        }
        else break;
    }

    return answer.map(i => i + 1);
}