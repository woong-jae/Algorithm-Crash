function solution(sales, links) {
    const adj_list = new Map(Array(sales.length).fill(0).map((_, i) => [i, []]));
    links.forEach(([a, b]) => adj_list.get(a - 1).push(b - 1));
    const cache = Array.from(Array(sales.length), () => [null, null]);
    
    const select = (current, picked) => {
        const children = adj_list.get(current);
        if(children.length === 0) return picked ? sales[current] : 0;
        if(cache[current][picked]) return cache[current][picked];
        
        // root를 고른 경우
        let min = children.reduce((prev, next) => prev + select(next, 0), sales[current]);
        // root를 안 고른 경우
        if(picked) return cache[current][picked] = min;
        
        children.forEach((next, index) => {
            let count = select(next, 1);
            for(let i = 0; i < children.length; i++) {
                if(i === index) continue; 
                count += select(children[i], 0);
            }
            min = Math.min(min, count);
        });
        
        return cache[current][picked] = min;
    }
    
    return select(0, 0);
}