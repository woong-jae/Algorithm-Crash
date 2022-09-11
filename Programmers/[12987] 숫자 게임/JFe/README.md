# [12987] 숫자 게임 - Python

## 🔍 Algorithm
-

## 💻 Logic

```Python
    A.sort()
    B.sort()
    # 오름차순 정렬한 A 값들을 오름차순 정렬한 B와 비교하면서 얻을 수 있는 승점 체크
    for a in A:
        # pointer가 B 길이까지 가거나 a보다 큰 B 값을 만날 때까지 pointer 증가
        while pointer < len(B) and a >= B[pointer]: pointer += 1
        # pointer가 B 길이 이상이 되면 더 이상 승점을 얻을 수 없으므로 break
        if pointer >= len(B): break
        answer += 1
        pointer += 1
```
- **오름차순 정렬한 A 값들을 오름차순 정렬한 B와 비교하면서 얻을 수 있는 승점 체크**  
    - pointer가 B 길이까지 가거나 a보다 큰 B 값을 만날 때까지 pointer 증가  
    - pointer가 B 길이 이상이 되면 더 이상 승점을 얻을 수 없으므로 break  


## 📝 Review

흑과백 게임이랑 비슷하다고 생각하고 풀었다. 이길 때는 최대한 적게 차이나게 이기고, 질 때는 최대한 크게 차이나게 지기  
근데 다른 풀이들이랑 비교하면 생각보다 꼬아서 구현한거 같기도,,