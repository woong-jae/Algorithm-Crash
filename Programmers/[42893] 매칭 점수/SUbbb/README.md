# [42893] 매칭 점수

## Algorithm
- 문자열
- 정규표현식

## Logic

```java
for (String page : pages) {
    Page p = new Page(word, page);
    // url을 키로 하여 맵 추가
    pageInfo.put(p.url, p);
}
```

- `Page` 클래스의 `setScore()`, `setExtPages()` , `setLinkScore()` 로 각 `page` 에 대해 기본 점수와 외부 링크 수, 링크 점수를 계산한다.

```java
for (Page p : pageInfo.values()) {
    for (String extPage : p.extPages) {
        if (pageInfo.containsKey(extPage))
            pageInfo.get(extPage).addScore(p.getLinkScore());
    }
}
```

- 해당 웹 페이지로 링크가 걸린 다른 웹 페이지의 링크 점수(기본 점수 / 외부 링크 수)의 총합을 구해 해당 웹 페이지의 매칭 점수를 계산한다. 

## Review
- 문제 자체는 매우 간단했지만 구현이 진짜 더러웠던 문제 ... 정규 표현식을 제대로 이해하지 않으면 절대 그냥 못 풀 듯 하다..
- 문제 이해를 잘못해서, **해당 웹 페이지로 링크가 걸린 다른 웹페이지의 기본 점수 / 외부 링크의 수의 총합**을 해당 웹 페이지로 링크가 걸린 다른 웹 페이지의 기본 점수와 현재 페이지의 링크의 수에 대한 계산으로 착각해서 시간을 더 날렸다.