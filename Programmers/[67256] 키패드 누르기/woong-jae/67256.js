function solution(numbers, hand) {
    let answer = '';
    
    let leftHand = 10, rightHand = 12;
    numbers.forEach(num => {
        if(num === 0) num = 11;
        
        if(num % 3 === 1) { // 1, 4, 7
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
    
    return answer;
}