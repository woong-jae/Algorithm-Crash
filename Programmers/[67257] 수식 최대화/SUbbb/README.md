# [67257] 수식 최대화

## Algorithm
- 순열

## Logic

```java
private void compute(int n) {
    ArrayList<Long> numbers = new ArrayList<>(nums);
    StringBuilder tmpOps = new StringBuilder(operands);

    for (char p : perm) {
        int idx = tmpOps.indexOf(String.valueOf(p));
        while(idx != -1) {
            long n1 = numbers.get(idx);
            long n2 = numbers.get(idx+1);

            numbers.set(idx, calculator(n1,n2,p));
            numbers.remove(idx+1);
            tmpOps.deleteCharAt(idx);

            idx = tmpOps.indexOf(String.valueOf(p));
        }
    }

    long sum = Math.abs(numbers.get(0));
    if (answer < sum) answer = sum;
}
```

- 수식의 숫자들을 저장하고 있는 `nums` 을 복사한 `numbers` 와 연산자들을 순서대로 저장하는 `operands` 를 복사한 `tmpOps` 를 사용한다.
  - 이후 계산 시 숫자와 연산자를 배열에서 삭제하기 위해 임시 저장소를 사용한다.
- 우선 순위대로 연산자를 저장한 `perm` 을 탐색하면서, 해당 연산자의 위치를 기준으로 숫자들을 계산하고, 대치한 후, 삭제한다.

## :black_nib: **Review**

- 연산자에 대한 순열이 필요하다는 점은 쉽게 알았지만, 이후 계산을 구현하는데 시간을 너무 많이 썼다.
- 아직 자바에 대한 공부가 더 필요할 것 같다. (`String`, `StringBuilder`, `ArrayList<String>` 에서 사용하는 메소드)