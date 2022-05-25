# [17684] 압축

## Algorithm
- 문자열
- Map

## Logic

```java
private int findWord(String[] msg, int i) {
    StringBuilder w = new StringBuilder(msg[i]);
    int idx = -1;

    // 사전에 포함되지 않는 단어를 찾을 때까지 문자를 계속 붙임
    while (dic.containsKey(w.toString())) {
        idx = dic.get(w.toString());
        i++;
        if (i == msg.length) break;
        w.append(msg[i]);
    }
    // 붙인 단어를 사전에 추가하고, 이미 찾은 색인을 배열에 추가
    dic.put(w.toString(), max++);
    index.add(idx);

    // i++ 된 이후로 반복문을 나오기 때문에 -1한 값을 반환
    return i - 1;
}
```
- 사전에 포함되지 않은 단어를 찾을 때까지, 색인을 계속 업데이트하면서 문자를 붙인다.
- 반복문이 종료되었을 때는,
  - 사전에 포함된 가장 긴 단어의 색인과,
  - 사전에 포함되지 않은 가장 긴 단어를 구할 수 있다.

## :black_nib: **Review**

- 사전이라는 단어를 보았을 때 바로 `Map` 을 사용해야겠다고 생각했다.
- 쉬운 문제..