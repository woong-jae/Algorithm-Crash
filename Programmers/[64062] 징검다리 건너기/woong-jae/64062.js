function solution(stones, k) {
    const canCross = (friends) => {
        let hop = 0;
        for(let stone of stones) {
            if(stone < friends) {
                hop++;
                if(hop === k) return false;
            }
            else hop = 0;
        }
        return true;
    }
    
    let left = 0, right = 200000000;
    while(left + 1 < right) {
        const mid = Math.floor((left + right) / 2);
        if(canCross(mid)) left = mid;
        else right = mid;
    }
    return left;
}