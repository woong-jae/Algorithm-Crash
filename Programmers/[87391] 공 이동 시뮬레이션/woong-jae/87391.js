function solution(n, m, x, y, queries) {
    let [top, bottom, left, right] = [x, x, y, y];
    queries = queries.reverse();

    for(let [command, dx] of queries) {
        switch(command) {
            case 0:
                if(left !== 0) left += dx;
                
                right += dx;
                if(right > m - 1) right = m - 1;
                
                break;
            case 1:
                left -= dx;
                if(left < 0) left = 0;
                
                if(right !== m - 1) right -= dx;
                
                break;
            case 2:
                if(top !== 0) top += dx;
                
                bottom += dx;
                if(bottom > n - 1) bottom = n - 1;
                
                break;
            case 3:
                top -= dx;
                if(top < 0) top = 0;
                
                if(bottom !== n - 1) bottom -= dx;
                
                break;
        }
        
        if(top >= n || bottom < 0 || left < 0 || right >= m) return 0;
    }
    
    return BigInt(bottom - top + 1) * BigInt(right - left + 1);
}