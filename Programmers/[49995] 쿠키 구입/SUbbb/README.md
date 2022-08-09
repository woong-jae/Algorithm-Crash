# [49995] 쿠키 구입

## Algorithm
- 투 포인터

## Logic

```java
private void getCookie(int m) {
    int fIdx = m;
    int sIdx = m + 1;
    
    int first = cookie[fIdx];
    int second = cookie[sIdx];
    
    while (true) {
        // 둘의 쿠키 개수가 같고, 이전에 구한 최댓값보다 크면 갱신
        if (first == second && answer < first) answer = first;
        // 둘째의 쿠키가 더 많거나 같고, 아직 첫째에게 사줄 쿠키가 있다면 누적!
        else if (first <= second && fIdx != 0) first += cookie[--fIdx];
        // 첫째의 쿠키가 더 많거나 같고, 아직 둘째에게 사줄 쿠키가 있다면 누적!
        else if (first >= second && sIdx != len - 1) second += cookie[++sIdx];
        else break;
    }
}
```

- 가장 중요한 함수 ...
- 주어진 기준점(`m`)을 이용해 첫째와 둘째의 쿠키 수를 조건에 따라 구한다.

## Review
- 처음 아이디어는 각 인덱스를 `m` 으로 두고, 첫째 아들과 둘째 아들에게 줄 쿠키 개수를 각각 구해보는 것이었다.
  - 처음으로 두 아들에게 줄 쿠키의 개수가 같아진다면, 해당 `m` 을 기준으로 줄 수 있는 최대의 동일한 쿠키 개수가 될 것이라 판단했다.
  - 하지만, 테케 3개만 맞은 걸 보니 구현에서 뭔가 빠트린 게 있는 것 같다.
- 결국 다른 풀이를 조금 참고했고, 큰 틀에서는 맞았으나 세부 조건을 구현하는 데서 실수가 있었던 것을 발견했다 ...