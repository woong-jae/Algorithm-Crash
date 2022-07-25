function solution(n, info) {
    let max_score_diff = 0, max_info = [-1];
    
    const getMaxScore = (index, n, picked) => {
        if(index === info.length || n === 0) {
            const peach_score = info.reduce((prev, cur, i) => {
                if(cur > 0 && !picked.has(i)) prev += (10 - i);
                return prev;
            }, 0);
            const ryan_score = Array.from(picked).reduce((prev, cur) => prev + (10 - cur), 0);
            
            const diff = ryan_score - peach_score
            if(diff <= 0) return;
            
            const ryan_info = info.map((elem, i) => picked.has(i) ? elem + 1 : 0);
            if(n > 0) ryan_info[10] += n;
            
            if(diff > max_score_diff) {
                max_score_diff = diff;
                max_info = ryan_info;
            }
            else if(diff === max_score_diff) {
                for(let i = 10; i >= 0; i--) {
                    if(ryan_info[i] === max_info[i]) continue;
                    
                    if(ryan_info[i] > max_info[i]) max_info = ryan_info;
                    break;
                }
            }
                
            return;
        }
        
        // 고르는 경우
        if(info[index] < n) {
            picked.add(index);
            getMaxScore(index + 1, n - (info[index] + 1), picked);
            picked.delete(index);
        }
        // 안고르는 경우
        getMaxScore(index + 1, n, picked);
    }
    
    getMaxScore(0, n, new Set());
    
    return max_info;
}