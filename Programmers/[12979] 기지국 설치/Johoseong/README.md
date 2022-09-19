# [12979] 기지국 설치
## Algorithm
- 구현?
## Logic
1. 전파가 도달안되는 아파트들의 연속된 개수를 세서 ```not_5g```배열에 기억해둠.
```python
for station in stations:
    e = station - w
    not_5g.append(e - s)
    s = station + w + 1
if (stations[-1] + w) < n:
    not_5g.append(n + 1 - s)
```
2. 기지국을 세웠을 때, 영향 줄 수 있는 최대 아파트 개수는 ```w * 2 + 1```개임.
3. 1번에서 구한 연속된 아파트 개수들에서 2번 연산을 통해, 최소 기지국 수를 구하면 됨.
- 주의 : ```stations``` 기준으로 1번 값 구해서, 전파 도달안되는 아파트가 없어도 배열에 0 이하의 값이 저장됨. 예외 처리 잘 해주기.
```python
for cnt in not_5g:
    if cnt <= 0: continue # 주의
    if cnt <= (2 * w + 1):
        answer += 1
    else:
        answer += (cnt // (2 * w + 1))
        if cnt % (2 * w + 1) != 0:
            answer += 1
```

## Review
이진 탐색으로 접근했다가 바로 선회함. 그런데 예외 처리 때문에 삽질 좀 했다ㅜ