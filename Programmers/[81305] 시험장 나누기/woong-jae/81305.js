function solution(k, num, links) {
    const floor = Math.floor;
    const parent = Array(num.length).fill(-1);
    links.forEach(([left, right], idx) => {
        if(left >= 0) parent[left] = idx;
        if(right >= 0) parent[right] = idx;
    });
    const root = parent.indexOf(-1);
    
    function getGroup(limit) {
        const cache = Array(num.length).fill(0);
        const visited = Array(num.length).fill(false);
        let groups = 1;
        
        let stack = [root];
        while(stack.length) {
            const cur = stack.pop();
            const [left, right] = links[cur];
            if(left >= 0 && !visited[left]) {
                stack.push(cur, left);
                continue;
            }
            if(right >= 0 && !visited[right]) {
                stack.push(cur, right);
                continue;
            }
            
            let leftValue = left >= 0 ? cache[left] : 0;
            let rightValue = right >= 0 ? cache[right] : 0;
            
            if(num[cur] + leftValue + rightValue <= limit) {
                cache[cur] = num[cur] + leftValue + rightValue;
            }
            else if(num[cur] + leftValue <= limit || num[cur] + rightValue <= limit) {
                groups++;
                cache[cur] = num[cur] + Math.min(leftValue, rightValue);
            }
            else if(num[cur] <= limit){
                groups += 2;
                cache[cur] = num[cur];
            }
            else {
                groups = Infinity;
            }
            
            visited[cur] = true;
        }
        return groups;
    }
    
    let right = num.reduce((prev, cur) => prev + cur, 0), left = floor(right / k);
    while(left <= right) {
        const mid = floor((left + right) / 2);
        if(getGroup(mid) <= k) right = mid - 1;
        else left = mid + 1;
    }
    return left;
}