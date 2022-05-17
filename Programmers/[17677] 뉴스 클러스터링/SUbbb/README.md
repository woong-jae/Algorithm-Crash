# [17677] 뉴스 클러스터링

## Algorithm
- 문자열
- Map

## Logic

```java
// 단어와 빈도 수 초기화
for (int i = 0; i < str1.length() - 1; i++) {
    String str = parsing(str1, i);
    if (str.length() != 2) continue;

    size1++;
    if (map1.containsKey(str)) map1.put(str, map1.get(str) + 1);
    else map1.put(str, 1);
}

for (int i = 0; i < str2.length() - 1; i++) {
    String str = parsing(str2, i);
    if (str.length() != 2) continue;

    size2++;
    if (map2.containsKey(str)) map2.put(str, map2.get(str) + 1);
    else map2.put(str, 1);
}
```
- `Map` 으로 단어와 그 빈도 수를 저장한다.

```java
// 교집합 구하기, 두 빈도 수 중 작은 값을 교집합의 크기로 지정
int inter = 0;
for (String key : map1.keySet())
    if (map2.containsKey(key))
        inter += map1.get(key) > map2.get(key) ? map2.get(key) : map1.get(key);
```
- 두 맵에 모두 존재하는 원소라면, 두 맵 중 **더 작은 빈도 수**를 교집합의 크기에 더한다.

## :black_nib: **Review**

- 자카드 유사도를 이해하는데 너무 오래 걸렸다. 
  - "원소의 중복을 허용하는 다중집합" 이라는 조건에서 시간이 많이 뺏겼다.
- 항상 테케를 잘 확인해야겠다.