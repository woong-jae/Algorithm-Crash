# [67258] 보석 쇼핑 - Python

## 🔍 Algorithm
**Two Pointers**

## 💻 Logic

```Python
start = 0
current = defaultdict(int)  # 기본값 0인 dictionary 생성
count = len(set(gems))  # set을 이용해서 중복 제거하고 전체 보석 수 저장
answer = [1, len(gems)]
```
- `defaultdict`에 **int**를 인자로 주고 생성하여 기본값이 **0**인 **dictionary**를 생성  
- `set`을 이용해서 중복 제거하고 전체 보석 수 저장  

```Python
# start, end 투 포인터를 이용해서 가장 짧은 구간 찾기
    for end in range(len(gems)):
        current[gems[end]] += 1
        # 모든 보석을 하나 이상 포함하는 경우
        while len(current) == count:
            # 해당 구간의 길이가 answer 구간 길이보다 작으면 업데이트
            if end - start < answer[1] - answer[0]:
                answer[0], answer[1] = start+1, end+1
            current[gems[start]] -= 1
            # 해당 키 값이 0이 되면 dictionary에서 삭제
            if current[gems[start]] == 0:
                del current[gems[start]]
            start += 1
    return answer
```
- **start, end 투 포인터를 이용해서 가장 짧은 구간 찾기**  
  `end`가 전체 보석 리스트의 처음부터 끝까지 이동하면서 해당 보석의 **dictionary** 키 값을 **+1**  
  모든 보석을 하나 이상 포함하는 경우에는,  
  해당 구간의 길이가 `answer` 구간 길이보다 작은지 확인하고 작으면 `answer` 값 업데이트하고  
  해당 키 값을 **-1** 하고(이 때, 해당 키 값이 **0**이 되면 **dictionary**에서 삭제), `start`를 다음 위치로 옮기고 반복한다.  


## 📝 Review

처음에는 규칙이 있을 거라고 생각하고 규칙을 찾아서 해봤지만, 예외 케이스도 많이 나오고 효율성도 통과하지 못해서 아예 다른 방식을 생각해보았다.  
그러다가 예전에 배웠었던 `슬라이딩 윈도우`를 생각해냈는데 `슬라이딩 윈도우`는 고정 길이고, 여기서는 길이가 변해야 하기 때문에 `투 포인터` 알고리즘이라고 얘기하는게 맞는 것 같다.  
**dictionary**와 **투 포인터**를 이용해서 구현했는데 모든 보석을 하나 이상 포함하고 있는지 처리하는 과정이 효율적이지 않아서 이 부분은 검색을 통해 `defaultdict` 라는 것이 있다는 것을 알게 되었고, `defaultdict`를 이용해서 간단하게 구현할 수 있었다.  
