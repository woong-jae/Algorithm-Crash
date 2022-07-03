# [72414] 광고 삽입
## Algorithm
- **Prefix Sum**, **Sliding Window**

## Logic
- 시간이 표현된 문자열을 int로 바꿔서 사용 -> 계산할 때 더 편하다
- int 형 배열 하나를 play길이 만큼 선언하고 Log의 시작시간과 끝시간을 포함한 배열의 인덱스에 ++ 해준다
- max 의 초기값을 0 부터 adv 길이만큼의 총합으로 하고
- 슬라이딩 윈도우 알고리즘을 이용하여 adv길이만큼 유지하며 이동하여 최대 값을 찾는다

```java
private int findMax(int play, int adv, int[] window) {
    int index = 0;
    long max = 0, current = 0;

    for(int i = 0; i < adv; i++)
        max += window[i];

    current = max;

    for (int i = 0, j = adv; j < play; i++, j++) {
        current = current - window[i] + window[j];
        if (current > max) {
            index = i + 1;
            max = current;
        }
    }
    return index;
}
```

## Review
처음에는 log들을 시작시간 기준으로 정렬하여 각 시작시간별로 광고를 검사하려고 했으나 맞지 않는 방법이었고,  
그래서 더 고민해보니 정렬 필요없이 누적하여 개수를 구해서 최댓값을 구하는 아이디어를 떠올려 해결 할 수 있었다  
방법은 맞았는데 내코드는 자꾸 시간초과가 떠서 다른 사람의 코드를 참고해 깔끔하게 구현한듯!