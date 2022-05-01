function solution(n, path, order) {
    const adjList = Array.from(Array(n), () => Array());
    path.forEach(([a, b]) => {
        adjList[a].push(b);
        adjList[b].push(a);
    });
    const followings = new Set(order.map(([_, following]) => following));
    const priors = new Map(order);
    if(followings.has(0)) return false;
    if(priors.has(0)) {
        followings.delete(priors.get(0));
        priors.delete(0);
    }
    
    const visited = Array(n).fill(false);
    visited[0] = true;
    const stack = [0], pending = new Set();
    
    while(stack.length) {
        const current = stack.pop();
        adjList[current].forEach(next => {
            if(visited[next]) return;
            // 아직 선행되는 방을 방문하지 않음
            if(followings.has(next)) {
                pending.add(next);
                return;
            }
            // 선행되는 방이면 후행되는 방을 제거하고, 대기중인 방이 있다면 스택에 넣어줌
            if(priors.has(next)) {
                const following = priors.get(next);
                followings.delete(following);
                priors.delete(next);
                if(pending.has(following)) {
                    stack.push(following);
                    visited[following] = true;
                    pending.delete(following);
                }
            }
            
            stack.push(next);
            visited[next] = true;
        });
        // 선행되는 방이 모두 처리되었다면 모두 방문 가능함
        if(!priors.size) return true;
    }
    
    return false;
}