def solution(cacheSize, cities):
    answer = 0
    cache = []

    if cacheSize == 0: # 캐시사이즈 0이면 그냥 바로 리턴
        return 5 * len(cities)

    for c in cities:
        city = c.upper()
        
        if city in cache: # 캐시에 있음 -> LRU라서 캐시 젤 뒤로 이동 (제일 최근에 조회했다는 뜻)
            i = cache.index(city)
            cache = cache[:i] + cache[i + 1:] + [city]
            answer += 1
        elif len(cache) < cacheSize: # 캐시에 없고 캐시 빈공간 있음
            cache.append(city)
            answer += 5
        else: # 캐시에 없고 캐시 꽉찼음 -> 조회된 지 제일 오래된 데이터 뺌
            cache.pop(0)
            cache.append(city)
            answer += 5

    return answer