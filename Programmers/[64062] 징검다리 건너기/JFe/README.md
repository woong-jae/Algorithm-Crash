# [64062] 징검다리 건너기 - Python

## 🔍 Algorithm
**Binary Search**

## 💻 Logic

```Python
# Binary Search
    while start <= end:
        mid = (start + end) // 2
        count = 0
        # 디딤돌 확인
        for s in stones:
            # 밟을 수 있는 경우
            if s >= mid:
                count = 0
                continue
            # 밟을 수 없는 경우
            count += 1
            # 건너뛰어야 하는 디딤돌이 k인 경우 (징검다리를 건널 수 없는 경우)
            if count == k:
                end = mid - 1
                break
        # 징검다리를 건널 수 있는 경우
        if count < k:
            answer = mid
            start = mid + 1
    return answer
```
- **Parametric Search**  
    `징검다리를 건널 수 있는 최대 인원수`를 찾는 **최적화 문제**를 `x명의 인원이 건널 수 있는지` 찾는 **결정 문제**로 바꿔서 해결  
- **Binary Search**  
    `x영의 인원이 건널 수 있는지` 찾기 위해서 `x`를 **Binary Search**  
    `count`를 이용해서 한번에 건너뛰어야 하는 디딤돌 수 체크  
    **밟을 수 있는 경우**에는 `count`를 **0**으로 초기화  
    **밟을 수 없는 경우**에는 `count`를 하나씩 늘려주고, `count == k`가 되면 징검다리를 건널 수 없으므로 `end`를 `mid` 앞으로 옮겨주고 다시 **Binary Search**  
    징검다리를 끝까지 건널 수 있는 경우에는 현재 `mid` 값을 정답으로 우선 저장해두고, `start`를 `mid` 뒤로 옮겨주고 다시 **Binary Search**  


## 📝 Review

처음에는 **Binary Search**로 풀어야겠다는 생각을 못하고 **Union-Find**나 다른 알고리즘을 생각하고 있었는데 입력도 터무니없이 크고, 저번에 풀었던 **Parametric Search** 문제가 생각나서 **Parametric Search** 기법을 사용해서 풀었다. 그러고 다시 보니까 전형적인 **Binary Search** 문제.  
다음부터 입력이 200,000,000처럼 터무니없이 크면 **Binary Search** 생각부터 하자!  
