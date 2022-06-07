function solution(word, pages) {
    const score = new Map();
    const linked = new Map();
    
    word = word.toLowerCase();
    const parseBody = page => {
        let baseScore = 0;
        Array.from(page.toLowerCase().matchAll(/[a-z]*/g))
            .forEach(([matched]) => {
                if(matched === word) baseScore++;
            });
        
        let pageURL = page.match(/<meta property="og:url" content="https:[\S]*"/g)[0].slice(33, -1);
        let links = [...page.matchAll(/<a href="https:[\S]*"/g)];
        links = links.map(link => link[0].slice(9, -1));
        links.forEach(link => {
            if(!linked.has(link)) linked.set(link, new Set());
            linked.get(link).add(pageURL); 
        });
        
        return [baseScore, pageURL, links];
    }
    
    pages.forEach(page => {
        const [baseScore, pageURL, links] = parseBody(page);
        score.set(pageURL, { baseScore, links });
    });
    
    let maxMatching = [-Infinity, -Infinity];
    Array.from(score).forEach(([page, { baseScore, links }], index) => {
        let matchingScore = baseScore;
        if(linked.has(page)) {
            matchingScore += Array.from(linked.get(page))
                .map(link => {
                    if(!score.has(link)) return 0;
                    const outer = score.get(link);
                    return outer.baseScore / outer.links.length;
                })
                .reduce((prev, cur) => prev + cur, 0);   
        }
        if(matchingScore > maxMatching[0]) {
            maxMatching = [matchingScore, index];
        }
    });
    
    return maxMatching[1];
}