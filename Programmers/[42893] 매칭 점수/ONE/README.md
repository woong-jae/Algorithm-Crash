# [42893] 매칭 점수
## Algorithm
- **정규표현식**, **Parsing**

## Logic
- Pattern, Matcher 과 정규표현식을 이용해 문자열을 파싱하여 현재의 링크, 외부링크 배열을 구한다
- 문제에서 제시한 공식을 이용하여 매칭점수를 구한다!
  - 이때, 점수가 double 인것을 유의해야 함

```java
for (String page : pages) {
    matcher = homeUrl.matcher(page);
    matcher.find();
    Page newPage = new Page(index++, matcher.group(0).split("\"")[3]);

    matcher = externalUrl.matcher(page);
    while (matcher.find())
        newPage.addLink(matcher.group(0).split("\"")[1]);

    String body = page.toLowerCase().split("<body>")[1].split("</body>")[0].replaceAll("[^a-z]", " ");
    matcher = wordPattern.matcher(body);
    while (matcher.find())
        newPage.score++;

    newPage.setLinkScore();
    pageMap.put(newPage.url, newPage);
}

for (Page p : pageMap.values())
    for (String e : p.externalLink)
        if(pageMap.containsKey(e))
            pageMap.get(e).score += p.linkScore;

double max = Double.MIN_VALUE;
for (Page p : pageMap.values()) {
    if(p.score == max && p.index < answer)
        answer = p.index;
    else if (p.score > max) {
        answer = p.index;
        max = p.score;
    }
}
```

## Review
문제 자체는 정규표현식을 이용하면 쉽게 구할 수 있었던 것 같은데  
점수가 double이 될 수도 있다는 것을 몰라서 시간이 오래걸렸다...
