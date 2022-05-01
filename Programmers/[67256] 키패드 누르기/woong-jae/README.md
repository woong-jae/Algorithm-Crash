# [67256] 키패드 누르기
## Algorithm
- 딱히 없음
## Logic
"*", "0", "#"을 각각 10, 11, 12로 바꿔서 생각하면 풀기 쉽다.

왼쪽 배열의 경우 3으로 나눴을 때 나머지가 1이 된다.
오른쪽 배열은 3으로 나눴을 때 나머지가 0이 된다.

중앙 배열을 눌러야 할 경우는 거리를 계산해야 한다.
왼쪽 손이나 오른쪽 손이 중앙에 있으면 현재 `num`과 거리를 계산해주고, 아니라면 손을 중앙으로 옮겨주고 거리를 계산한다.

```js
numbers.forEach(num => {
    if(num === 0) num = 11;
    
    if(num % 3 === 1) {
        answer += "L"
        leftHand = num;
    }
    else if(num % 3 === 0) { 
        answer += "R"
        rightHand = num;
    }
    else {
        let leftDistance = Math.abs(num - (leftHand % 3 === 1 ? leftHand + 1 : leftHand)) / 3;
        if(leftHand % 3 === 1) leftDistance++;
        let rightDistance = Math.abs(num - (rightHand % 3 === 0 ? rightHand - 1 : rightHand)) / 3;
        if(rightHand % 3 === 0) rightDistance++;
        if(leftDistance < rightDistance) {
            answer += "L";
            leftHand = num;
        }
        else if(leftDistance > rightDistance) {
            answer += "R"
            rightHand = num;
        }
        else {
            if(hand === "right") {
                answer += "R";
                rightHand = num;
            }
            else {
                answer += "L";
                leftHand = num;
            }
        }
    }
});
```

## Review
빨리 풀 수는 있었는데 코드가 좀 지저분한 것 같다...