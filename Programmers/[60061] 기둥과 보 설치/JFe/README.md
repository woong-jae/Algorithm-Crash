# [60061] 기둥과 보 설치 - Python

## 🔍 Algorithm
**구현**

## 💻 Logic

```Python
    for x, y, a, b in build_frame:
        # 설치하는 경우
        if b == 1:
            answer.append([x, y, a])
            if not check(answer):
                answer.remove([x, y, a])
        # 삭제하는 경우
        elif b == 0:
            answer.remove([x, y, a])
            if not check(answer):
                answer.append([x, y, a])
```
- 설치/삭제하는 경우 모두 설치/삭제하고 조건에 걸리면 다시 롤백  

```Python
def check(answer):
    for x, y, a in answer:
        # 기둥인 경우
        if a == 0:
            # 바닥 위 or 보의 한쪽 끝 부분 위 or 다른 기둥 위인지 확인
            if y == 0 or [x-1, y, 1] in answer or [x, y, 1] in answer or [x, y-1, 0] in answer: continue
            return False
        # 보인 경우
        if a == 1:
            # 한쪽 끝 부분이 기둥 위 or 양쪽 끝 부분이 다른 보와 동시에 연결 상태인지 확인
            if [x, y-1, 0] in answer or [x+1, y-1, 0] in answer or [x-1, y, 1] in answer and [x+1, y, 1] in answer: continue
            return False
    return True
```
- 기둥인 경우 : 바닥 위 or 보의 한쪽 끝 부분 위 or 다른 기둥 위인지 확인  
- 보인 경우 : 한쪽 끝 부분이 기둥 위 or 양쪽 끝 부분이 다른 보와 동시에 연결 상태인지 확인  


## 📝 Review

구현 문제인데 생각하는 것만큼 쉽게 구현하지 못했고,  
일단 설치, 삭제를 하고 조건 확인한 후 롤백한다는 아이디어를 참고해서 풀었다..
