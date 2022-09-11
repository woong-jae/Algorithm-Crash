# [12984] 지형 편집 - Python

## 🔍 Algorithm
**?**

## 💻 Logic

```Python
    # land 이차원 리스트 -> height 일차원 리스트로 + 오름차순 정렬
    height = list(chain.from_iterable(land))
    height.sort()
    # answer : 이전 cost 값 / cost : 해당 층을 기준으로 높이를 맞췄을 때 비용
    answer = sum(height) * Q                            # 전부 다 0층으로 만들 때의 비용
    cost = (sum(height) - height[0] * len(height)) * Q  # 제일 낮은 층을 기준으로 높이를 맞출 때 드는 비용 : (전체 블록 수 - 제일 낮은 층까지 있는 블록 수) * Q
    answer = min(answer, cost)
```
- **`land` 이차원 리스트 -> `height` 일차원 리스트로**  
    - `chain()`을 이용해 이차원 리스트 `land`를 일차원 리스트 `height`로 변경  
    - `sort()`로 오름차순 정렬  
- **answer : 이전 cost 값 / cost : 해당 층을 기준으로 높이를 맞췄을 때 비용**  
    - `answer` 초기값 : 전부 다 0층으로 만들 때의 비용  
    - `cost` 초기값 : 제일 낮은 층을 기준으로 높이를 맞출 때 드는 비용 `(전체 블록 수 - 제일 낮은 층까지 있는 블록 수) * Q`  


```Python
# height에 있는 제일 낮은 층부터 제일 높은 층까지 기준으로 지형 수정
    for i in range(1, len(height)):
        # 이전과 같은 높이의 층이면 계산 X
        if height[i - 1] == height[i]: continue
        w = height[i] - height[i - 1]
        # 추가해야 하는 블록들만큼 비용 추가 : P * i * w / 이전에 제거했던 블록 중 제거할 필요 없는 블록들만큼 비용 빼기 : Q * (len(height) - i) * w
        cost += (P * i * w) - (Q * (len(height) - i) * w)
        # answer보다 cost가 커지면 break
        if answer < cost: break
        answer = cost
```
- **`height`에 있는 제일 낮은 층부터 제일 높은 층까지 기준으로 지형 수정**  
    - 이전과 같은 높이의 층이면 계산 X  
    - 추가해야 하는 블록들만큼 비용 추가 : `P * i * w` / 이전에 제거했던 블록 중 제거할 필요 없는 블록들만큼 비용 빼기 : `Q * (len(height) - i) * w`  
    - `answer`보다 `cost`가 커지면 **break**  


## 📝 Review

끝내 다른 사람 풀이를 참고했는데 문제 이해부터 차이가 있었던 것 같다. 이런 생각 어떻게 하지 싶으면서도 쉬운 풀이 같기도 하고...  
chain 함수는 새로 배워갑니다.  