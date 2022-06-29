function solution(orders, course) {
    
    const getCombination = arr => {
        const comb = (arr, pick) => {
            if(pick === 1) return arr.map(elem => [elem]);
            const combs = [];
            arr.forEach((elem, index) => {
                const smallerCombs = comb(arr.slice(index + 1), pick - 1);
                smallerCombs.forEach(smallComb => combs.push([elem].concat(smallComb)));
            });
            return combs;
        }
        
        const ret = [];
        for(let len = 2; len <= arr.length; len++) {
            ret.push(...comb(arr, len).map(menu => menu.join("")));
        }
        return ret;
    }
    
    let menu_count = new Map();
    
    orders.forEach(order => {
        const allMenu = getCombination(order.split("").sort());
        allMenu.forEach(menu => {
            if(!menu_count.has(menu)) menu_count.set(menu, 0);
            menu_count.set(menu, menu_count.get(menu) + 1);
        });
    });
    
    menu_count = Array.from(menu_count);
    
    return course.map(courseLen => {
        const cand = menu_count.filter(([menu, count]) => menu.length === courseLen && count >= 2)
        const max_count = cand.reduce((prev, cur) => Math.max(prev, cur[1]), 0);
        return cand
            .filter(([_, count]) => count === max_count)
            .map(([menu]) => menu);
    }).flat().sort();
}