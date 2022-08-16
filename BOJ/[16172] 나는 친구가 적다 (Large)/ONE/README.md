# [16172] 나는 친구가 적다 (Large)
## Algorithm
- **KMP**

## Logic
- 숫자가 포함된 문자열을 숫자를 제외한 문자만 필터링하여 새로운 문자열을 만든다
- 새로 만든 문자열(textbook)에서 문자열(keyword)을 찾기 위해 KMP 알고리즘을 사용
  - 접두사와 접미사가 일치하는 부분 일치 테이블을 구하기
  - 부분일치 테이블을 이용해서 문자열을 비교하며 일치하는 부분 문자열이 있는지 확

```java
private static int[] makeTable(String s) {
    int n = s.length(), idx = 0;
    int[] table = new int[n];

    for (int i = 1; i < n; i++) {
        // 일치하지 않는다면 이전 일치 인덱스 돌아가고 같은 부분이 나올떄까지 반복
        while (idx > 0 && s.charAt(i) != s.charAt(idx))
            idx = table[idx - 1];
        // 일치하면 idx +1 하고 현재위치의 테이블에 idx값을 넣는다
        if (s.charAt(i) == s.charAt(idx))
            table[i] = ++idx;
    }
    return table;
}

private static int KMP(String textbook, String keyword) {
    int[] table = makeTable(textbook);
    int n1 = textbook.length(), n2 = keyword.length();
    int idx = 0;

    for (int i = 0; i < n1; i++) {
        // 다를 때
        while (idx > 0 && textbook.charAt(i) != keyword.charAt(idx))
            idx = table[idx - 1];
        // 같을 때
        if (textbook.charAt(i) == keyword.charAt(idx)) {
            // 끝까지 일치 했을 때
            if (idx == n2 - 1)
                return 1;
            else
                idx++;
        }
    }
    return 0;
}
```

## Review
이름에 large가 있길래 일반 contains 메서드로 했더니 역시나 시간초과가 발생했고  
KMP 알고리즘을 사용했더니 통과했다  
오랜만에 푸는 백준문제