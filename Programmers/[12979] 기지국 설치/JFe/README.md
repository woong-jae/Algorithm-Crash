# [12979] 기지국 설치 - Python

## 🔍 Algorithm
**Two Pointers**

## 💻 Logic

```Python
    # 1. station 전파가 도달하는 제일 앞 부분을 end로 설정
    # 2. (end - start) 거리를 block으로 나눈 값을 올림한 만큼 기지국 추가 설치 가능
    # 3. 다음 start 위치를 현재 station 전파가 도달하는 제일 끝 부분 다음으로 설정
    for v in stations:
        end = v - w
        answer += math.ceil((end - start) / block)
        start = v + w + 1
    # start가 끝에 도달하지 않은 경우, 추가로 진행
    if start <= n:
        answer += math.ceil((n + 1 - start) / block)
```
- **투 포인터 사용해서 설치 가능한 최소 기지국 개수 확인**  
    1. **station 전파가 도달하는 제일 앞 부분**을 `end`로 설정  
    2. `(end - start)` 거리를 `block` `(w * 2 + 1)` 으로 나눈 값을 **올림**한 만큼 기지국 추가 설치 가능  
    3. 다음 `start` 위치를 현재 **station 전파가 도달하는 제일 끝 부분 다음**으로 설정  
    - start가 끝에 도달하지 않은 경우, 추가로 진행  


## 📝 Review

처음에는 기지국을 설치했을 때 전파가 도달할 수 있는 거리가 정해져 있으니까 Sliding Window를 사용해서 확인하면 되겠다고 생각했는데 n 범위가 너무 커서 효율성에서 실패했다.  
그래서 생각을 살짝 틀어서 Two Pointers를 사용해 n 말고 stations를 기준으로 반복문을 돌면서 확인하도록 바꿔서 해결했다.  