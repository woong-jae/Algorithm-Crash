# [17677] 뉴스 클러스터링
## Algorithm
- Map
## Logic
다중집합을 다뤄야하기 때문에 원소의 수를 세야한다.

교집합의 크기는 한 집합을 순회하면서 다른 집합에 똑같은 원소가 있을 때 더 작은 값을 더해주면서 구할 수 있다.
합집합의 크기는 한 집합을 순회하면서 다른 집합에 똑같은 원소가 없으면 원소의 수를 더하고, 있다면 둘 중 더 큰 값을 더해주면서 구할 수 있다.

```js
let intersect = 0, union = 0;
str1Map.forEach((value, key) => {
    if(!str2Map.has(key)) {
        str2Map.set(key, value);
        return;
    };
    intersect += Math.min(value, str2Map.get(key));
    str2Map.set(key, Math.max(value, str2Map.get(key)));
});
str2Map.forEach(value => union+=value);
```

## Review
문제를 자세히 안읽어서 좀 해맸다. 좀 읽자...