# [67256] 키패드 누르기

## Algorithm
- **구현**

## Logic

- 손가락의 좌표와 키패드의 좌표를 나타내는 `Key Class` 구현
- 왼속가락의 좌표, 오른손가락의 좌표, 손잡이의 정보를 가진 `Hand Class` 구현
- 키패드를 `Map`을 이용해 `<숫자, 좌표>` 로의 형식으로 구현
- 3으로 나눈 나머지로 구분하여 왼쪽, 가운데, 오른쪽을 구분해 해당 조건에 맞게 `StringBuilder`에 추가

```java
private void move(int num, Hand hand, StringBuilder builder) {
    // Right Side
    if (num % 3 == 0 && num != 0) {
        hand.rightFinger.moveFinger(keypad.get(num));
        builder.append("R");
    }

    // Left Side
    else if (num % 3 == 1) {
        hand.leftFinger.moveFinger(keypad.get(num));
        builder.append("L");
    }

    // Center
    else {
        if (hand.rightFinger.getDistance(keypad.get(num))
                < hand.leftFinger.getDistance(keypad.get(num))) {
            hand.rightFinger.moveFinger(keypad.get(num));
            builder.append("R");
        }

        else if (hand.rightFinger.getDistance(keypad.get(num))
                > hand.leftFinger.getDistance(keypad.get(num))) {
            hand.leftFinger.moveFinger(keypad.get(num));
            builder.append("L");
        }

        else {
            if (hand.isRightHanded()) {
                hand.rightFinger.moveFinger(keypad.get(num));
                builder.append("R");
            }

            else {
                hand.leftFinger.moveFinger(keypad.get(num));
                builder.append("L");
            }
        }
    }
}
```

## Review
문제 자체는 어렵지 않게 구현할 수 있었지만 `if`문이 많아 지저분 해보인다  
최대한 객체 지향적으로 구현해보려 함!