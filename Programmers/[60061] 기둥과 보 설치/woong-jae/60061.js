function solution(n, build_frame) {
    const colFrames = new Map(), rowFrames = new Map();
    
    const isValid = (x, y, a, b) => {
        const key = `${x},${y}`;
        if(a) { // 보
            if(b === 0 && !rowFrames.has(key)) return true;
            if(colFrames.has(`${x},${y - 1}`) || colFrames.has(`${x + 1},${y - 1}`)) return true;
            if(rowFrames.has(`${x - 1},${y}`) && rowFrames.has(`${x + 1},${y}`)) return true;
        }
        else { // 기둥
            if(b === 0 && !colFrames.has(key)) return true;
            if(y === 0) return true;
            if(colFrames.has(`${x},${y - 1}`)) return true;
            if(rowFrames.has(`${x - 1},${y}`) || rowFrames.has(`${x},${y}`)) return true;
        }
        return false;
    }
    
    const buildFrame = (x, y, a, b) => {
        const key = `${x},${y}`;
        if(!isValid(x, y, a, b)) return;
        if(a) {
            rowFrames.set(key, a);
        }
        else {
            colFrames.set(key, a);
        }
    }
    
    const deleteFrame = (x, y, a, b) => {
        const key = `${x},${y}`;
        if(a) { // 보
            rowFrames.delete(key);
            if(!isValid(x - 1, y, 1, b) 
               || !isValid(x + 1, y, 1, b)
               || !isValid(x + 1, y, 0, b)
               || !isValid(x, y, 0, b)) {
                rowFrames.set(key, a);
            }
        }
        else { // 기둥
            colFrames.delete(key);
            if(!isValid(x, y + 1, 0, b) 
               || !isValid(x - 1, y + 1, 1, b) 
               || !isValid(x, y + 1, 1, b)) {
                colFrames.set(key, a);
            }
        }
    }
    
    build_frame.forEach(([x, y, a, b]) => {
        if(b) buildFrame(x, y, a, b);
        else deleteFrame(x, y, a, b);
    });
    
    const col = Array.from(colFrames)
    .map(([position, a]) => [...position.split(",").map(e => +e), a])
    const row = Array.from(rowFrames)
    .map(([position, a]) => [...position.split(",").map(e => +e), a])
    
    return col.concat(row).sort((a, b) => {
        if(a[0] !== b[0]) return a[0] - b[0];
        if(a[1] !== b[1]) return a[1] - b[1];
        return a[2] - b[2];
    })
}