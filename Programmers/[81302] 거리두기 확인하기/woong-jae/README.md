# [81301] 거리두기 확인하기
## Algorithm
- DFS
## Logic
각 대기실마다 응시자들이 거리두기를 지키고 있는지 확인한다.

응시자의 위치에서 DFS를 깊이 2까지 했을 때, 다른 응시자가 있다면 거리두기가 지켜지지 않는 경우다.
```js
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
```

## Review
별로 어렵지 않은 문제. 쉽게 풀 수 있었다.