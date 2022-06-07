# [42893] 매칭 점수
## Algorithm
- Regex
## Logic
각 페이지마다 기본 점수, 외부 링크, 나를 참조하는 링크를 구한다.

```js
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
```
그리고 매칭 점수가 가장 높은 페이지를 구한다.
## Review
문제 자체는 간단하지만 링크 파싱하는데 오래 걸렸다. 아직 정규표현식이 익숙하지도 않고 자꾸 한 두개씩 틀려서 다른 사람의 풀이를 참고했다.

극혐이였던 문제.
