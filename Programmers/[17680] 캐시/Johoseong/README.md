# [17680] 캐시
## Algorithm
- 구현
## Logic
- 캐시를 배열로 두고, 중요한 점은 ```LRU``` 이므로 인덱스가 클수록 최근에 조회한 데이터가 오도록 함. 나머지는 문제 조건대로 구현.
1. cities의 city들을 탐색. (대소문자 구별 안하므로 upcase시킴)
2. city가 ```cache hit```임
```python
if city in cache: # 캐시에 있음 -> 캐시 젤 뒤로 이동 (제일 최근에 조회했다는 뜻)
    i = cache.index(city)
    cache = cache[:i] + cache[i + 1:] + [city]
    answer += 1
```
- 실행시간 + 1
- 해당 city가 제일 최근에 조회됐으므로, 배열 맨 뒤로 이동시켜줌
3. ```cache miss```고, 캐시에 빈공간 존재
```python
elif len(cache) < cacheSize: # 캐시에 없고 캐시 빈공간 있음
    cache.append(city)
    answer += 5
```
- 캐시 제일 뒤에 city 추가. 실행시간 + 5
4. ```cache miss```고, 캐시에 빈공간 없음
```python
else: # 캐시에 없고 캐시 꽉찼음 -> 조회된 지 제일 오래된 데이터 뺌
    cache.pop(0)
    cache.append(city)
    answer += 5
```
- 조회한지 제일 오래된 city 빼고(0번째 data), 현재 city 추가. 실행시간 + 5

## Review
쉬웠는데 LRU인거 생각 없이 hit 때 데이터 위치 이동 안했다가 몇개 틀려서 어리둥절했다ㅎㅎ;; 문제 꼼꼼히 읽자