# [72411] 메뉴 리뉴얼

## Algorithm
- 조합
- 정렬

## Logic

```java
// map에 해당 조합이 있다면, count는 그 조합의 빈도 수, 그렇지 않다면 0
int count = 0;
if (menuComb.containsKey(str))
    count = menuComb.get(str);

// 현재 해당 조합을 추가할 것이므로 count를 증가
count++;
// 해당 조합이 이미 있었고, 해당 조합(메뉴 구성)이 속한 코스 중 최대 빈도보다 빈도가 크다면 최신화
if (count != 1 && courseMaxCount.get(str.length()) < count)
    courseMaxCount.put(str.length(), count);

menuComb.put(str, count);
```
- 메뉴 구성 길이별 최대 빈도수를 저장하는 `map` 을 최신화하는 과정

## Review
- 가능한 모든 조합을 구하는 것이 가장 관건이었다. 이후 구현은 정렬을 잘 활용하면 되는 문제였다.
- 처음에는 조합을 만들고, 각 코스 요리별 메뉴 수의 최대 빈도를 구하고, 그 빈도를 가지는 메뉴 구성을 출력하도록 했다가, 조합을 만들고 `map` 에 추가할 때, 메뉴 구성 길이별 최대 빈도를 저장하는 `map` 을 두어 이를 사용하도록 변경해보았다.