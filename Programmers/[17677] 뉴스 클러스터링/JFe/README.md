# [17677] 뉴스 클러스터링 - Python

## 🔍 Algorithm
**문자열**

## 💻 Logic

```Python
def make_dict(str):
    str_dict = defaultdict(int)
    pattern = re.compile('[A-Z]+')  # 영문 대문자인 경우만
    for i in range(len(str)-1):
        if not pattern.match(str[i]) or not pattern.match(str[i+1]): continue
        str_dict[str[i]+str[i+1]] += 1
    return str_dict
```
- **영문 대문자인 경우에만 두글자씩 끊어서 딕셔너리에 저장**  
    영문 대문자인 경우에만 딕셔너리에 저장하기 위해 **정규 표현식**을 이용하여 문자열 판별  

```Python
# str1 딕셔너리 기준으로 교집합, 차집합 수 계산
    for key in str1_dict.keys():
        # str2 딕셔너리에 값 있으면 교집합 계산
        if str2_dict[key] > 0:
            intersect += min(str1_dict[key], str2_dict[key])
        union += max(str1_dict[key], str2_dict[key])    # 합집합 계산
        # 계산한 key 값 0으로
        str1_dict[key] = 0
        str2_dict[key] = 0
# str2 딕셔너리 기준으로 교집합, 차집합 수 계산
    for key in str2_dict.keys():
        # 두 딕셔너리 다 0이 아닌 경우, 교집합 계산
        if str1_dict[key] > 0 and str2_dict[key] > 0:
            intersect += min(str1_dict[key], str2_dict[key])
        union += max(str1_dict[key], str2_dict[key])
```
- **str1 딕셔너리 기준으로 교집합, 차집합 수 계산**  
    str2 딕셔너리에 값이 있으면 `min`을 이용해서 **교집합** 계산  
    `max`를 이용해서 **합집합** 계산하고, 계산한 key 값은 0으로 바꿔준다.  
- **str2 딕셔너리 기준으로 교집합, 차집합 수 계산**  
    두 딕셔너리 다 0이 아닌 경우, `min`을 이용해서 **교집합** 계산  
    `max` 이용해서 **합집합** 계산  


## 📝 Review

다중집합 계산을 위해 딕셔너리에 저장하고 최솟값, 최댓값 계산을 통해 교집합, 합집합을 구하도록 했다.  
`isAlpha()`를 이용해서 구현하려고 했지만, **영문자**만 가능하게 구현하라고 해서 `정규표현식`을 사용해서 구현했다.  
