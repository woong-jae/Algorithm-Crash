function solution(m, musicinfos) {
    let answer = [];
    
    const getRuntime = (start, end) => {
        const [sHour, sMin] = start.split(":").map(e => +e);
        const [eHour, eMin] = end.split(":").map(e => +e);
        return (eHour - sHour) * 60 + eMin - sMin;
    }
    
    const getTotalScore = (score, runTime) => {
        const numberOfUpscale = score.split("").reduce((prev, cur) => cur === "#" ? prev + 1 : prev, 0);
        const scoreLength = score.length - numberOfUpscale;
        let totalScore = score.repeat(runTime / scoreLength);
        let count = runTime % scoreLength;
        for(let char of score) {
            if(count === 0 && char !== "#") break;
            if(char !== "#") count--;
            totalScore += char;
        }
        return totalScore;
    }
    
    musicinfos.forEach((musicInfo, index) => {
        const [start, end, title, score] = musicInfo.split(",");
        const runTime = getRuntime(start, end);
        const totalScore = getTotalScore(score, runTime);
        
        let target = totalScore.indexOf(m);
        while(target !== -1) {
            const checkIndex = target + m.length;
            if(checkIndex >= totalScore.length || totalScore[checkIndex] !== "#") {
                answer.push([runTime, index, title]);
                break;
            }
            target = totalScore.indexOf(m, target + 1);
        }
    });
    
    answer.sort((a, b) => {
        if(a[0] === b[0]) return a[1] - b[1];
        return b[0] - a[0];
    });
    
    return answer.length ? answer[0][2] : "\(None\)";
}