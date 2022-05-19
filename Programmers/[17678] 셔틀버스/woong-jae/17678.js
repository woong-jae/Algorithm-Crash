function solution(n, t, m, timetable) {
    timetable = timetable.map(time => {
        const [h, m] = time.split(":").map(e => +e);
        return h * 60 + m;
    }).sort((a, b) => a - b);
    
    const lines = Array.from(Array(n), () => []);
    let index = 0;
    lines.forEach((line, nth) => {
        const shuttleTime = 9 * 60 + nth * t;
        while(line.length < m && index < timetable.length && timetable[index] <= shuttleTime) {
            line.push(timetable[index++]);
        }
    });
    
    let cand = -Infinity;
    for(let nth = lines.length - 1; nth >= 0; nth--) {
        const shuttleTime = 9 * 60 + nth * t;
        if(lines[nth].length === 0) return parseShuttleTime(Math.max(cand, shuttleTime));
        if(lines[nth].length < m) {
            return parseShuttleTime(Math.max(cand, shuttleTime, ...lines[nth]));
        }
        if(lines[nth].length === m) {
            cand = Math.max(cand, Math.min(shuttleTime, Math.max(...lines[nth]) - 1));
        }
    }
    
    return parseShuttleTime(cand);
}

function parseShuttleTime(min) {
    let hour = String(Math.floor(min / 60));
    if(hour.length === 1) hour = "0" + hour;
    let miniute = String(min % 60);
    if(miniute.length === 1) miniute = "0" + miniute;
    return hour + ":" + miniute;
}