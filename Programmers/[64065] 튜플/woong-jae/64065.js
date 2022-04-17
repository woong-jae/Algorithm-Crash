function solution(s) {
    let answer = [];
    let setRepresentation = [];
    
    // s를 배열로 파싱
    s = s.slice(1, s.length - 1);
    let sIndex = 0;
    while(sIndex < s.length) {
        const char = s[sIndex++];
        if(char === "{") {
            const begin = sIndex;
            while(s[++sIndex] !== "}");
            setRepresentation.push(s.slice(begin, sIndex).split(",").map(elem => +elem));
        }
    }
    // 원소의 길이를 기준으로 오름차순 정렬, 각 원소를 순회하면서 s가 표현하는 튜플 생성
    let visited = new Set();
    setRepresentation.sort((a, b) => a.length - b.length).forEach(set => {
        for(let i = 0; i < set.length; i++) {
            const element = set[i];
            if(!visited.has(element)) {
                visited.add(element);
                answer.push(element);
                break;
            }
        }
    });
    
    return answer;
}