# [1083] 소트 - Python

## 🔍 Algorithm
**Bubble Sort**

## 💻 Logic

```Python
# S번 교환하면서 가장 내림차순이 되게 만들기
for i in range(N):
    max_idx, max_v = i, A[i]
    # 현재 위치에서 최대로 교환 가능한 위치까지 최댓값 구하기
    for j in range(i, i+S+1):
        if j == N: break
        # 최댓값, 최댓값 인덱스 저장
        if max_v < A[j]:
            max_v = A[j]
            max_idx = j
    # 최댓값 위치까지 교환
    for j in range(max_idx, i, -1):
        A[j], A[j-1] = A[j-1], A[j]
        S -= 1
    if S == 0: break    # 더 이상 교환 못하면 break
```
- **S번 교환하면서 가장 내림차순이 되도록 만들기**  
    현재 위치(`i`)에서 최대로 교환 가능한 위치(`i+S`)까지 **최댓값** 구하고, 최댓값과 최댓값의 인덱스를 저장해둔다.  
    구한 **최댓값**이 현재 위치로 오도록 **Bubble Sort** 진행하고, `S` 값은 그만큼 줄여준다.  
    `S`가 **0**이 돼서 더 이상 교환 못하면 **break**  


## 📝 Review

일반적인 Bubble Sort와 비슷하지만 최대로 교환 가능한 횟수가 정해져 있는 문제. 이 부분만 추가로 처리해주면 된다.  
문제 자체는 어렵지 않은데 문제 설명이 너무 불친절하다,,,  
