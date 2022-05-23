# [17680] 캐시 - Python

## 🔍 Algorithm
**Queue**

## 💻 Logic

```Python
    for city in cities:
        city = city.upper() # 대소문자 통일
        # 캐시에 값이 있는 경우
        if city in cache:
            answer += 1
            # Recently Used 업데이트
            cache.remove(city)
            cache.append(city)
            continue
        # 캐시에 값이 없는 경우
        answer += 5
        # 캐시가 가득 찬 경우
        if len(cache) == cacheSize:
            key = cache.popleft()
        cache.append(city)
```
- **캐시에 값이 있는 경우**  
    hit 카운트인 **+1** 만큼 카운트해주고,  
    **Recently Used** 업데이트를 위해 해당 값을 **remove** 하고, 다시 **append** 해준다.  
- **캐시에 값이 없는 경우**  
    miss 카운트인 **+5** 만큼 카운트해주고, 해당 값을 **append** 해준다.  
    이 때, **캐시가 가득 찬 경우**에는 `cache`에서 **popleft** 해준다.  


## 📝 Review

**LRU**를 구현하기 위해 처음에는 **딕셔너리**를 생각했지만 비효율적인 것 같아서 `deque`을 사용해 **큐**를 이용하는 방법으로 구현했다.  
두 가지 정도 테스트케이스가 통과 못했었는데 `cacheSize`가 **0**인 경우 예외 처리 하는 과정을 통해 처리했다.  
