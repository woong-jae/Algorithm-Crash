function solution(cacheSize, cities) {
    let answer = 0;
    const cache = new Set();
    
    cities.forEach(city => {
        city = city.toLowerCase();
        if(cache.has(city)) {
            cache.delete(city);
            cache.add(city);
            answer++;
            return;
        }
        cache.add(city);
        answer += 5;
        if(cache.size > cacheSize) {
            const [target] = cache.entries().next().value;
            cache.delete(target);
        }
    });
    
    return answer;
}
