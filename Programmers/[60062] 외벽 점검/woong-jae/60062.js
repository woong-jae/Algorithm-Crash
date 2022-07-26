const getPermutation = arr => {
    if(arr.length === 1) return [arr];
    
    const firstElem = arr[0];
    const smallerPerms = getPermutation(arr.slice(1));
    
    const perms = [];
    smallerPerms.forEach(smallPerm => {
        for(let i = 0; i <= smallPerm.length; i++) {
            const prefix = smallPerm.slice(0, i);
            const suffix = smallPerm.slice(i);
            perms.push(prefix.concat([firstElem], suffix));
        }
    });
    
    return perms;
}

const getUpperBound = (arr, value) => {
    let left = 0, right = arr.length - 1;
    while(left + 1 < right) {
        const mid = Math.floor((left + right) / 2);
        if(arr[mid] > value) right = mid;
        else left = mid;
    }
    return arr[left] > value ? left : right;
}

function solution(n, weak, dist) {
    const weakLength = weak.length;
    weak = weak.concat(weak.map(point => point + n));
    dist = getPermutation(dist);
    
    let answer = Infinity;
    dist.forEach(step => {
        for(let i = 0; i < weakLength; i++) {
            let start = weak[i];
            let end = weak[i + weakLength - 1];
            for(let d = 0; d < step.length; d++) {
                start += step[d];
                if(start >= end) {
                    answer = Math.min(answer, d + 1);
                    break;
                }
                let next = getUpperBound(weak, start);
                start = weak[next];
            }
        }
    });
    
    return answer === Infinity ? -1 : answer;
}