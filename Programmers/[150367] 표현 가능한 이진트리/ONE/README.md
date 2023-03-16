# [150367] 표현 가능한 이진트리
## Algorithm
- **Divide & Conquer**

## Logic
- 크게 두 가지의 기능으로 구현할 수 있다
  1. **입력 받은 수를 포화 상태의 이진 트리(이진 수)로 만들기**
  2. **이진 수를 분할 하면서 Root 를 검사한다**  
  

- 포화 상태의 이진 트리의 개수는 _**2ⁿ - 1**_ 라는 규칙을 따른다
  - 입력 받은 수를 이진수로 변경 후 _**2ⁿ - 1**_ 수열 중 자신보다 크거나 같은 수를 찾는다
  - 문제의 제한 조건에서 최대 값이 10^15 로 정해져 있어 _**2ⁿ - 1**_ 가 될 수 있는 최댓 값은 50(n 은 6)이다
  - 이진수의 길이와 _**2ⁿ - 1**_ 간의 길이 차 만큼 앞을 0 으로 채워준다
```java
private String getBinaryString(long number) {
    String binaryString = Long.toBinaryString(number);
    StringBuilder sb = new StringBuilder();
    int length = binaryString.length();
    for (int i = 0; i <= 5; i++) {
        if (length >= Math.pow(2, i) - 1 && length <= Math.pow(2, i + 1) - 1) {
            sb.append("0".repeat((int) (Math.pow(2, i + 1) - 1 - length)));
            break;
        }
    }
    return sb.append(binaryString).toString();
}
```

- 이진 트리를 만들 수 없는 조건은 _"현재 Root 가 0 이면서 좌 우 자식 중 1 이 존재할 때"_ 이다
  - 이를 재귀함수로 구현해 좌 우 자식 루트의 재귀의 결과를 & 연산을 통해 구한다

```java
private int divideConquer(String number, int start, int end) {
    if (start >= end) {
        return 1;
    }
    int mid = (start + end) / 2;
    if (number.charAt(mid) == '0'
            && ((number.charAt((start + mid - 1) / 2) == '1' || number.charAt((mid + 1 + end) / 2) == '1'))) {
        return 0;

    }
    return divideConquer(number, start, mid - 1) & divideConquer(number, mid + 1, end);
}
```

## Review
처음에 문제를 제대로 읽지 않고 포화 이진 트리를 만들어야 하는 규칙을 찾지 못해 엄청 헤맸다...  
문제 좀 똑바로 읽으면서 하자!