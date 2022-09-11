def solution(A, B):
    answer = 0
    A.sort()
    B.sort()
    
    while A:
        if B[-1] > A[-1]:
            answer += 1
            A.pop()
            B.pop()
        else:
            A.pop()

    return answer

print(solution([5,1,3,7], [2,2,6,8]))