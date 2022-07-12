function solution(lottos, win_nums) {
    const win_picks = Array(46).fill(0);
    win_nums.forEach(num => win_picks[num]++);
    
    const unknown = lottos.reduce((prev, cur) => prev + (cur === 0 ? 1 : 0), 0);
    const match = lottos.reduce((prev, cur) => {
        if(cur > 0 && win_picks[cur] > 0) {
            win_picks[cur]--;
            prev += 1;
        }
        return prev;
    }, 0);
    
    const calcRank = match => {
        const rank = 7 - match;
        return rank > 6 ? 6 : rank;
    }
    
    return [match + unknown, match].map(e => calcRank(e));
}