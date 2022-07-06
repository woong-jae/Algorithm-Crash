function solution(id_list, report, k) {
    const reported = new Map(id_list.map(id => [id, new Set]));
    const count = new Map(id_list.map(id => [id, 0]));
    
    report.forEach(string => {
        const [did, get] = string.split(" ");
        reported.get(did).add(get);
    });
    
    reported.forEach(reports => {
         reports.forEach((id, get) => {
             count.set(get, count.get(get) + 1);
         });
    });
    
    return Array.from(reported).map(([_, reports]) => {
        let message = Array.from(reports).reduce((prev, cur) => {
            if(count.get(cur) >= k) prev += 1;
            return prev;
        }, 0);
        return message;
    });
}