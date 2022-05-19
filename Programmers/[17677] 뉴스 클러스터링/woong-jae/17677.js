function solution(str1, str2) {
    str1 = str1.toLowerCase();
    str2 = str2.toLowerCase();
    const regx = /[^a-z]/;
    
    const str1Map = new Map(), str2Map = new Map();
    for(let index = 0; index < str1.length - 1; index++) {
        const element = str1.slice(index, index + 2);
        if(element.length !== 2 || regx.test(element)) continue;
        if(!str1Map.has(element)) str1Map.set(element, 0);
        str1Map.set(element, str1Map.get(element) + 1);
    }
    for(let index = 0; index < str2.length - 1; index++) {
        const element = str2.slice(index, index + 2);
        if(element.length !== 2 || regx.test(element)) continue;
        if(!str2Map.has(element)) str2Map.set(element, 0);
        str2Map.set(element, str2Map.get(element) + 1);
    }
    
    if(str1Map.size === 0 && str2Map.size === 0) return 65536;
    
    let intersect = 0, union = 0;
    str1Map.forEach((value, key) => {
        if(!str2Map.has(key)) {
            str2Map.set(key, value);
            return;
        };
        intersect += Math.min(value, str2Map.get(key));
        str2Map.set(key, Math.max(value, str2Map.get(key)));
    });
    str2Map.forEach(value => union+=value);
    
    return Math.floor(intersect / union * 65536);
}