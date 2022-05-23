from collections import deque
def solution(cacheSize, cities):
    answer = 0
    cache = deque()
    # cacheSize가 0이면 전부 다 miss
    if cacheSize == 0: return len(cities) * 5
    
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
    return answer