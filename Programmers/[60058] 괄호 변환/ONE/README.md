# [60058] 괄호 변환
## Algorithm
- **Recursion**

## Logic
- 문제의 조건에 따라 시키는 대로 구현하는 문제

```java
private String recursion(String p) {
    if (p.equals(""))
        return p;

    String u = "";
    String v = "";

    int countL = 0;
    int countR = 0;

    for (int i = 0; i < p.length(); i++) {
        char c = p.charAt(i);

        if(c == '(')
            countL++;
        else
            countR++;

        if (countL > 0 && countL == countR) {
            u = p.substring(0, i + 1);
            if (i != p.length() - 1)
                v = p.substring(i + 1);
            break;
        }
    }

    if (!checkCorrect(u)) {
        String tmp = "(" + recursion(v);

        tmp += ")";

        u = u.substring(1, u.length() - 1);
        u = u.replace("(", "-");
        u = u.replace(")", "(");
        u = u.replace("-", ")");

        tmp += u;

        return tmp;
    }
    else
        return u + recursion(v);
}
```

## Review
문제 자체는 어렵지 않았지만 문제를 이해하기 어렵게 써놓은 것 같다
