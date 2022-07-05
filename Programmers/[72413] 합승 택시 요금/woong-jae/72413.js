function solution(n, s, a, b, fares) {
    const dist = Array.from(Array(n), () => Array(n).fill(Infinity));
    fares.forEach(([u, v, w]) => {
        dist[u - 1][v - 1] = w;
        dist[v - 1][u - 1] = w;
    });
    
    for(let i = 0; i < n; i++) dist[i][i] = 0;
    
    for(let k = 0; k < n; k++) {
        for(let i = 0; i < n; i++) {
            for(let j = 0; j < n; j++) {
                dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }
    }
    
    let answer = Infinity;
    for(let i = 0; i < n; i++) {
        answer = Math.min(answer, dist[s - 1][i] + dist[i][a - 1] + dist[i][b - 1]);
    }
    
    return answer;
}
