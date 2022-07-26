# [17685] 자동완성 - Python

## 🔍 Algorithm
**문자열, 정렬**

## 💻 Logic

```Python
    words.sort()
    for i in range(len(words)-1):
        count = 0
        # 뒷 단어와 공통된 문자 수 체크
        for j in range(len(words[i])):
            if words[i][j] != words[i+1][j]: break
            count += 1
```
- **words 사전 순으로 정렬**  
- **뒷 단어와 공통된 문자 수 체크**  
    뒷 단어와 연속된 공통된 문자 수를 count로 체크  

```Python
# 원래 단어 수와 같으면 count 그대로
        if count == len(words[i]):
            length[i] = max(length[i], count)
# 원래 단어 수와 다르면 count + 1
        else:
            length[i] = max(length[i], count+1)
```
- **원래 단어 수와 같으면 count 그대로, 다르면 count + 1**  
    같으면 count를, 다르면 count + 1 값을 length[i]와 비교해서 max 값을 다시 저장  


## 📝 Review

트라이(Trie)를 공부했어서 트라이로 풀려고 했는데 생각하는 것처럼 잘 되지는 않아서 방향을 틀어서 간단하게 반복문을 돌면서 공통된 문자 수를 체크하는 방식으로 풀었다.  
시간 날 때, 트라이로도 다시 한번 풀어봐야 할 듯,,
