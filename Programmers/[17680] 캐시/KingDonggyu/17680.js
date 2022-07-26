function solution(cacheSize, cities) {
  const cache = new Set();
  let answer = 0;

  cities.forEach((city) => {
    city = city.toLowerCase();

    if (cache.has(city)) {
      cache.delete(city);
      answer += 1;
    } else {
      answer += 5;
    }

    cache.add(city);

    if (cache.size > cacheSize) {
      const oldest = cache.keys().next().value;
      cache.delete(oldest);
    }
  });

  return answer;
}