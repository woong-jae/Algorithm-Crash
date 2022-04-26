# [67256] 키패드 누르기 - Python

## 🔍 Algorithm
**구현**

## 💻 Logic

```Python
left, right = [0, 3], [2, 3]    # '*', '#' 좌표[x, y] 저장
for n in numbers:
    if n == 0: n = 11   # 0은 계산 편하게 11로 저장
    x, y = (n-1)%3, (n-1)//3    # 해당 숫자의 키패드 상 좌표 계산
```
- **왼손, 오른손 위치 저장**  
    `left`, `right`에 현재 왼손, 오른손 위치 저장  
    시작 위치는 `*`, `#` 좌표[x, y] 저장  
- **numbers에 있는 숫자만큼 반복문 진행**  
    키패드 상에 있는 위치에 맞게 `x = (n-1) % 3`, `y = (n-1) // 3` 계산  
    `n`이 **0**인 경우에는 계산하기 쉽게 키패드 위치처럼 **11**로 저장하고 계산  

```Python
# 키패드 위치에 따라 나눠서 처리
        if n == 1 or n == 4 or n == 7:
            answer += 'L'
            left = [x, y]
        elif n == 3 or n == 6 or n == 9:
            answer += 'R'
            right = [x, y]
        else:
            # 왼손, 오른손 거리 계산
            left_dir = abs(left[0]-x) + abs(left[1]-y)
            right_dir = abs(right[0]-x) + abs(right[1]-y)
            if left_dir < right_dir:
                answer += 'L'
                left = [x,y]
            elif left_dir > right_dir:
                answer += 'R'
                right = [x, y]
            # 거리가 같으면 hand 값에 따라서 처리
            else:
                if hand == "left": 
                    answer += 'L'
                    left = [x, y]
                else: 
                    answer += 'R'
                    right = [x, y]
```
- **키패드 위치에 따라 나눠서 처리**  
    `n`이 **2, 5, 8, 0**인 경우에는 `left`, `right` 값과의 거리를 계산해서  
    왼손이 가까우면 왼손으로, 오른손이 가까우면 오른손으로 처리  
    거리가 같으면 `hand` 값에 따라 나눠서 처리  


## 📝 Review

좌표를 통해 거리를 계산하는 것이 편하겠다고 생각해서 왼손과 오른손의 현재 위치를 좌표로 저장하고, 번호를 키패드 위치에 맞게 좌표로 계산해서 저장하는 방식으로 구현했다.  
간단하게 구현하면 되는 문제였는데 조건문이 많아져서 코드는 안이쁜 것 같음,,
