# [17683] 방금그곡
## Algorithm
- Sort
## Logic
1. 주어진 악보를 재생시간만큼 늘린다
2. 악보에서 주어진 멜로디를 찾는다.
    - 있다면 정답 배열에 넣는다.
3. 모든 음악 정보들을 순회하고 난 후 배열을 주어진 양식에 따라 정렬해준다. 배열이 비었다면 "(None)"을 출력하고 아니라면 제일 앞의 원소가 가진 제목을 출력한다.

```js
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
```

## Review
무난한 문제인듯. 경계 값에서 처리를 자꾸 실수해서 더 빨리 풀 수 있는데 느려지는 것 같다.