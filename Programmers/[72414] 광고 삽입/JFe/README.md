# [72414] κ΄κ³  μ½μ - Python

## π Algorithm
**Prefix Sum**

## π» Logic

```Python
# log μκ° λ³ν
    for log in logs:
        l = log.split('-')
        for i, v in enumerate(l):
            tmp = v.split(':')
            l[i] = int(tmp[0]) * 3600 + int(tmp[1]) * 60 + int(tmp[2])
        time_list[l[0]] += 1
        time_list[l[1]] -= 1
```
- **log μκ° λ³ν**  

```Python
    # κ΅¬κ°λ³ μμ²­μ μ μ μ₯
    for i in range(1, len(time_list)):
        time_list[i] += time_list[i-1]
    # λμ  μμ²­μ μ μ μ₯
    for i in range(1, len(time_list)):
        time_list[i] += time_list[i-1]
```
- **κ΅¬κ°λ³ μμ²­μ μ μ μ₯**  
- **λμ  κ΅¬κ° ν© κ΅¬ν ν μ μ₯**  


```Python
    # μμ²­μ μ κ°μ₯ λ§μ κ΅¬κ° μ²΄ν¬
    most_view = time_list[adv_time-1]
    result = 0
    for i in range(adv_time, play_time):
        if most_view < time_list[i] - time_list[i - adv_time]:
            most_view = time_list[i] - time_list[i - adv_time]
            result = i - adv_time + 1
    # κ²°κ³Ό κ° λ¬Έμμ΄ λ³ν
    hour = '0' + str(result // 3600)
    min = '0' + str(result % 3600 // 60)
    sec = '0' + str(result % 60)
    return hour[-2:] + ':' + min[-2:] + ':' + sec[-2:]
```
- **μμ²­μ μ κ°μ₯ λ§μ κ΅¬κ° μ²΄ν¬**  
- **κ²°κ³Ό κ°μ λ¬Έμμ΄λ‘ λ³ννμ¬ λ°ν**  


## π Review

μ²μμλ Sliding Windowκ° μκ°λμ κ° κ΅¬κ°μ μ²μ μμ μκ°μ κΈ°μ€μΌλ‘ ν΄κ²°μ νλ €κ³  νλλ° λμ  μκ°μ κ³μ°νλ λΆλΆμ μ΄λ»κ² ν΄μΌν μ§ λͺ°λΌμ λ€λ₯Έ μ½λλ₯Ό μ°Έκ³ νλ€..  
