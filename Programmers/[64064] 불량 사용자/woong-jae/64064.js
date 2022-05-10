function solution(user_id, banned_id) {
    const combs = new Set();
    
    const getCombs = (banned_idx, banned) => {
        if(banned_idx >= banned_id.length) {
            combs.add(banned);
            return;
        };
        const bid = banned_id[banned_idx];
        user_id.forEach((uid, uidIdx) => {
            if(uid.length !== bid.length || banned & (1 << uidIdx)) return;
            for(let index = 0; index < uid.length; index++) {
                if(bid[index] === "*") continue;
                if(uid[index] !== bid[index]) return;
            }
            getCombs(banned_idx + 1, banned ^ (1 << uidIdx));
        });
    }
    
    getCombs(0, 0);
    
    return combs.size;
}