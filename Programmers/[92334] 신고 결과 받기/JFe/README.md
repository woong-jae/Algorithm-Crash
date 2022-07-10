# [92334] 신고 결과 받기 - Python

## 🔍 Algorithm
**Map**

## 💻 Logic

```Python
# 딕셔너리 추가
    for r in report:
        a, b = r.split()
        # 동일한 유저에 대한 신고 횟수는 1회로 처리
        if b not in log_dict[a]:
            log_dict[a].append(b)
            reported[b] += 1
# 결과 메일 처리
    for id in id_list:
        cnt = 0
        for key in log_dict[id]:
            if reported[key] >= k:
                cnt += 1
        answer.append(cnt)
```


## 📝 Review

딕셔너리를 사용하여 문제에서 원하는대로 구현하기만 하면 되는 문제!
