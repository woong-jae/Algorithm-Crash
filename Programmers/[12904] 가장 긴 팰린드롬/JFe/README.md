# [12904] 가장 긴 팰린드롬 - Python

## 🔍 Algorithm
**구현, 문자열**

## 💻 Logic

```Python
    # 팰린드롬 문자열이 홀수인 경우
        left, right, count = i - 1, i + 1, 1
        while 0 <= left and right < len(s):
            if s[left] == s[right]:
                count += 2
                left, right = left - 1, right + 1
            else: break
        answer = max(answer, count)
    # 팰린드롬 문자열이 짝수인 경우
        pivot, right, count = i, i + 1, 0
        while 0 <= pivot and right < len(s):
            if s[pivot] == s[right]:
                count += 2
                pivot, right = pivot - 1, right + 1
            else: break
        answer = max(answer, count)
```
- **팰린드롬 문자열이 홀수인 경우, 짝수인 경우 나눠서 판단**  
- **팰린드롬 문자열이 홀수인 경우**  
    기준 문자 앞, 뒤로 left, right가 같으면 count 2씩 증가시키고  
    가장 큰 count를 answer에 저장  
- **팰린드롬 문자열이 짝수인 경우**  
    기준 문자 pivot과 바로 뒷 문자 right가 같으면 count 2씩 증가시키고  
    가장 큰 count를 answer에 저장  


## 📝 Review

짝수인 경우를 생각 못했었는데 짝수인 경우도 홀수인 경우와 크게 다른 점이 없어서 쉽게 구현할 수 있었다.