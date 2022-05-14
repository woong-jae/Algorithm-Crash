# [64062] 징검다리 건너기

## Algorithm
- **Binary Search**

## Logic
- `파라메트릭 서치`를 이용해 가장 큰수의 디딤돌과 가장 작은 수의 디딤돌 사이에 존재하는 가장 큰 건널수 있는 사람의 수 구하기
- 이분 탐색으로 해당 인원수가 건널 수 있다면 오른쪽에서 한번 더 탐색
- 건너지 못한다면 왼쪽에서 더 적은 인원수로 건널 수 있는지 검사
- 건널 수 있는 지 판단하는 것은 연속된 0의 개수가 k보다 작은지를 검사하면 된다

```java
private int parametricSearch(int[] stones, int k, int low, int high) {
    if (low == high)
        return low;

    while (low < high) {
        int mid = (low + high) / 2;

        if (cross(stones, k, mid))
            low = mid + 1;
        else
            high = mid;
    }

    return low - 1;
}

private boolean cross(int[] stones, int k, int mid) {
    int cnt = 0;

    for (int stone : stones) {
        if (stone - mid < 0)
            cnt++;
        else
            cnt = 0;

        if (cnt == k)
            return false;
    }
    return true;
}
```

## Review
파라메트릭 서치에 대해 여러 문제를 접했어서 다시 풀어봤을 때  
해당 개념을 적용하여 풀 수 있다는 것을 생각할 수 있었다
살짝 감이 잡히는 느낌
