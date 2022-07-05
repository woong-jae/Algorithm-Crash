function logToSec(log) {
    return log.split(":").reduce((prev, cur, index) => prev + (+cur) * (60 ** (2 - index)), 0);
}

function secToLog(sec) {
    const log = [];
    for(let i = 0; i < 3; i++) {
        const time = Math.floor(sec / (60 ** (2 - i)));
        log.push(String(time).padStart(2, "0"));
        sec %= 60 ** (2 - i);
    }
    return log.join(":");
}

function calcPlay(start, end, logs) {
    return logs.reduce((prev, cur) => {
        const left = start < cur.start ? cur.start : start;
        const right = end < cur.end ? end : cur.end;
        return prev += right - left;
    }, 0);
}

function solution(play_time, adv_time, logs) {
    const parsed_logs = logs.map(log => log.split("-").map(time => logToSec(time)));
    play_time = logToSec(play_time);
    adv_time = logToSec(adv_time);
    
    const user = Array(play_time + 1).fill(0);
    
    parsed_logs.forEach(([start, end]) => {
        user[start]++;
        user[end]--;
    });
    
    for(let i = 1; i <= play_time; i++) user[i] += user[i - 1];
    
    for(let i = 1; i <= play_time; i++) user[i] += user[i - 1];
    
    let max_sum = user[adv_time - 1], best_start_time = 0;
    for(let i = adv_time; i <= play_time; i++) {
        const sum = user[i] - user[i - adv_time];
        if(sum > max_sum) {
            max_sum = sum;
            best_start_time = i - adv_time + 1;
        }
    }
    
    return secToLog(best_start_time);
}