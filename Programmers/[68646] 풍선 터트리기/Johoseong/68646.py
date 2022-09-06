def solution(a):
    answer = 0
    left, right = 10 ** 10, 10 ** 10

    matrix = [[0] * len(a) for _ in range(2)]

    for i in range(len(a)):
        if left > a[i]:
            left = a[i]
        matrix[0][i] = left

    for i in range(len(a) - 1, -1, -1):
        if right > a[i]:
            right = a[i]
        matrix[1][i] = right

    for i in range(len(a)):
        if a[i] <= matrix[0][i] or a[i] <= matrix[1][i]:
            answer += 1

    return answer