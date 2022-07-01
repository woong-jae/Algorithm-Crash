function solution(info, query) {
    // info로 트리 구성
    const root = new Map();
    const leaf = new Set();

    info.forEach(information => {
        let cur = root;
        information = information.split(" ");
        for(let i = 0; i < 4; i++) {
            const elem = information[i];
            if(!cur.has(elem)) cur.set(elem, i < 3 ? new Map() : []);
            cur = cur.get(elem);
        }
        leaf.add(cur);
        cur.push(+information[4]);
    });
    leaf.forEach(arr => arr.sort((a, b) => a - b));
    
    
    const countCandiate = (queries) => {
        
        const search = (cur, query_index) => {
            let query = queries[query_index];
            if(query_index === queries.length - 1) {
                let left = 0, right = cur.length - 1;
                
                while(left <= right) {
                    const mid = Math.floor((left + right) / 2);
                    if(cur[mid] < query) left = mid + 1;
                    else right = mid - 1;
                }
                
                return cur.length - left;
            }
            
            if(query === "-") {
                let count = 0;
                cur.forEach(next => count += search(next, query_index + 1));
                return count;
            }
            if(!cur.has(query)) return 0;
            
            return search(cur.get(query), query_index + 1);
        }
        
        return search(root, 0);
    }
    
    return query.map(q => {
        q = q.split(" ").filter(word => word !== "and");
        return countCandiate(q);
    });
}