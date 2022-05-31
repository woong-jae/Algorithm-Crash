function solution(relation) {
    let answer = new Set();
    
    const isCandidate = picked => {
        const check = new Set();
        // 최소성 확인
        let notMinimal = false;
        answer.forEach(pick => {
            if((pick & picked) === pick) notMinimal = true;
        })
        if(notMinimal) return false;
        
        // 유일성 확인
        relation.forEach(row => {
            check.add(row.reduce((prev, cur, idx) => picked & (1 << idx) ? prev + cur : prev, ""));
        });
        return check.size === relation.length;
    }
    
    const getCombination = size => {
        if(size === 1) {
            return relation[0].map((_, idx) => {
                if(isCandidate(1 << idx)) answer.add(1 << idx)
                return 1 << idx;
            });
        }
        const combination = new Set();
        const smallCombinations = getCombination(size - 1);
        
        smallCombinations.forEach(picked => {
            for(let i = 0; i < relation[0].length; i++) {
                if(picked & 1 << i) continue;
                combination.add(picked | 1 << i);
            } 
        });
        
        combination.forEach(picked => {
            if(isCandidate(picked)) answer.add(picked);
        })
        
        return Array.from(combination);
    }
    
    getCombination(relation[0].length);
    
    return answer.size;
}