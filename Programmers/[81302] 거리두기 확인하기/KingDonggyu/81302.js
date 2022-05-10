function solution(places) {
    const dx = [-1, 1, 0, 0];
    const dy = [0, 0, -1, 1];
    
    const answer = [];
    
    places.forEach((place, i) => {
        const stack = [];
        
        for (let x = 0; x < 5; x++) {
            for (let y = 0; y < 5; y++) {
                if (place[x][y] === 'P') {
                    stack.push([x, y, 1])
                }
            }
        }
            
        const visited = new Array(5).fill(null).map(
            _ => new Array(5).fill(false));
        
        let isKeep = true;
        
        outer: while (stack.length > 0) {
            let [x, y, distance] = stack.pop();
            
            if (visited[x][y]) {
                continue;
            }
            
            visited[x][y] = true;

            for (let d = 0; d < 4; d++) {
                let nextX = x + dx[d];
                let nextY = y + dy[d];

                if (nextX < 0 || nextX > 4 || nextY < 0 || nextY > 4 ||
                    visited[nextX][nextY] || place[nextX][nextY] === 'X') {
                    continue;
                }

                if (place[nextX][nextY] === 'P') {
                    isKeep = false;
                    break outer;
                }
                
                if (distance === 2) {
                    continue;
                }
                
                stack.push([nextX, nextY, distance + 1]);
            }
        }
        
        answer[i] = isKeep ? 1 : 0;
    })
    
    return answer;
}