def solution(n):
    answer = ""
    res = n
    while res > 0:
        if res % 3 != 0:
            answer += str(res % 3)
        if res % 3 == 0:
            res = res//3 -1
            answer+="4"
        else:
            res = res // 3
            
    return answer[::-1]

