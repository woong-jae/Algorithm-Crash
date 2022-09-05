function solution(N, stages) {
    const notCleared = Array(N).fill(0);
    const reached = Array(N).fill(0);
    stages.forEach(stage => {
        notCleared[stage - 1] += 1;
        
        reached[0] += 1;
        if(stage > N - 1) return;
        reached[stage] -= 1;
    });
    
    for(let i = 1; i < N; i++) {
        reached[i] += reached[i - 1];
    }
    
    const fail_rate = reached.map((players, index) => notCleared[index] / players);
    
    return Array.from(Array(N), (_, i) => i + 1).sort((a, b) => {
        if(fail_rate[a - 1] === fail_rate[b - 1]) return a - b;
        return fail_rate[b - 1] - fail_rate[a - 1];
    });
}
