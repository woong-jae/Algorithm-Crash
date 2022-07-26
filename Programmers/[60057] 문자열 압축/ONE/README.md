# [60057] 문자열 압축
## Algorithm
- **Brute Force**

## Logic
- 길이 1부터 절반의 길이에 해당하는 부분문자열을 각각구하여 뒤의 문자열들중 같은것의 개수를 세서 문자열을 생성

```java
for (int i = 1; i <= (len + 1) / 2; i++) {
    StringBuilder sb = new StringBuilder();
    for (int j = 0; j <= len - i; j += i) {
        String sub = s.substring(j, j + i);
        int count = 1;
        for (int k = j + i; k <= len - i; k += i) {
            if (sub.equals(s.substring(k, k + i))) {
                count++;
                j = k;
            }
            else
                break;
        }
        if(count == 1)
            sb.append(sub);
        else
            sb.append(count).append(sub);
    }
    sb.append(s.substring(len - (len % i)));
    answer = Math.min(answer, sb.length());
}
```

## Review
문자열의 길이가 1개 짜리인걸 처리 못해서 시간이 오래걸렸다...  
굳