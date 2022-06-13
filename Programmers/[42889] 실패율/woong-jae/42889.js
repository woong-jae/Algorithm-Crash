function solution(N, stages) {
    const player = Array(N).fill(0);
    const cleared = Array(N).fill(0);
    
    stages.forEach(stage => {
        player[stage - 1]++;
        for(let i = 0; i < stage; i++) {
            cleared[i]++;
        }
    });

    const get_failure_rate = stage => {
        return cleared[stage] ? player[stage] / cleared[stage] : 0;
    }
    const failure_rates = Array.from(Array(N), (_, i) => get_failure_rate(i));
    
    return Array(N)
        .fill(0)
        .map((_, i) => i + 1)
        .sort((a, b) => failure_rates[b - 1] - failure_rates[a - 1]);
}