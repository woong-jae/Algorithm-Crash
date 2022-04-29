function solution(n, path, order) {
    const adjList = Array.from(Array(n), () => Array());
    path.forEach(([a, b]) => {
        adjList[a].push(b);
        adjList[b].push(a);
    });
    // 각 방의 Root로부터 경로를 저장
    const visited = Array(n).fill(false);
    const pathFromRoot = Array(n);
    function dfs(cur, path) {
        pathFromRoot[cur] = new Set(path);
        visited[cur] = true;
        path.push(cur);
        adjList[cur].forEach(next => {
            if(!visited[next]) dfs(next, path);
        });
        path.pop();
    }
    dfs(0, []);
    
    const followings = new Set(order.map(([_, following]) => following));
    const priors = new Map(order);
    // prior를 다 삭제할 수 있으면 순서에 따라 방문 가능하다.
    while(priors.size) {
        let processed = false;
        // prior를 삭제하기 위해선 경로에 다른 following이 없어야 한다.
        for(let [prior, following] of priors) {
            let canDelete = true;
            for(let target of followings) {
                if(pathFromRoot[prior].has(target)) {
                    canDelete = false;
                    break;
                }
            }
            if(canDelete) {
                followings.delete(following);
                priors.delete(prior);
                processed = true;
            }
        }
        if(!processed) return false;
    }
    
    return true;
}