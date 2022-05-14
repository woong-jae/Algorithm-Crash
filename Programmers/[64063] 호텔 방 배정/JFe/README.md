# [64063] 호텔 방 배정 - Python

## 🔍 Algorithm
**DP**

## 💻 Logic

```Python
    dp = defaultdict(int)   # 원하는 방에 대해 갈 수 있는 다음 방 번호 메모이제이션 (0이면 비어있다는 뜻)
    # 한 명씩 빈 방 찾아서 배정
    for num in room_number:
        temp = []
        next_num = num
        # 빈 방을 찾을 때까지 탐색
        while dp[next_num] > 0:
            next_num = dp[next_num]
            temp.append(next_num)   # 나중에 dp 한번에 업데이트 하기 위해 따로 저장
        # 빈 방 다음 위치로 dp 업데이트
        dp[next_num] = next_num + 1
        for i in temp:
            dp[i] = next_num + 1
        # 빈 방으로 배정
        answer.append(next_num)
    return answer
```
- **메모이제이션**  
    원하는 방이 비어 있지 않는 경우에 다음 가능한 방을 빨리 찾을 수 있도록 갈 수 있는 다음 방 번호를 저장해둔다.  
    `defaultdict`를 사용해서 **dictionary** 형태로 저장하고, 기본값을 **0**으로 설정해서 **0**이면 **비어 있는 상태**  
- **빈 방을 찾을 때까지 탐색**  
    해당 방 번호의 `dp` 값이 존재하면 그 `dp` 값으로 계속 반복해서 `dp` 값이 **0**일 때까지 찾는다.  
    탐색 과정에서 방문한 방 번호들은 나중에 `dp`를 한번에 업데이트 하기 위해 따로 저장해두고,  
    빈 방 다음 위치로 방문한 `dp` 값들을 전부 업데이트한다.  
    찾은 빈 방은 `answer`에 **append**  


## 📝 Review

간단하게 딕셔너리만 사용해서 풀었는데 역시 효율성에서 다 걸렸다.  
그러다가 다음 가야 되는 위치를 메모이제이션 하면 더 빠르게 찾을 수 있겠다고 생각해서 DP로 풀었다.  

