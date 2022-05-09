function solution(places) {
    const answer = [];
    const dr = [-1, 0, 1, 0], dc = [0, 1, 0, -1];
    
    const inRange = (row, col) => {
        if(0 <= row && row < 5 && 0 <= col && col < 5) return true;
        return false;
    }
    
    const isValidDist = (row, col, place) => {
        const visited = Array.from(Array(5), () => Array(5).fill(false));
        
        function dfs(row, col, depth) {
            visited[row][col] = true;
            if(depth >= 2) return true;
            for(let direction = 0; direction < 4; direction++) {
                const nr = row + dr[direction], nc = col + dc[direction];
                if(!inRange(nr, nc) || visited[nr][nc]) continue;
                if(place[nr][nc] === "P") return false;
                if(place[nr][nc] === "O" && !dfs(nr, nc, depth + 1)) return false;
            }
            return true;
        }
        
        return dfs(row, col, 0);
    };
    
    // 거리두기 안되는 경우: P에서 DFS depth 2만큼 안에 다른 P가 존재
    places.forEach(place => {
        place = place.map(row => row.split(""));
        for(let row = 0; row < 5; row++) {
            for(let col = 0; col < 5; col++) {
                const elem = place[row][col];
                if(elem === "P" && !isValidDist(row, col, place)) {
                    answer.push(0);
                    return;
                }
            }
        }
        answer.push(1);
    });
    
    return answer;
}