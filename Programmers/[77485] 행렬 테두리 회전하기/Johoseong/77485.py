def rotate(matrix, x1, y1, x2, y2):
    min_num = 10 ** 10
    left_tmp = matrix[x2][y1]
    right_tmp = matrix[x1][y2]
    for c in range(y2 - 1, y1 - 1, -1): # 위
        matrix[x1][c + 1] = matrix[x1][c]
        min_num = min(min_num, matrix[x1][c])
    for c in range(y1 + 1, y2 + 1): # 아래
        matrix[x2][c - 1] = matrix[x2][c]
        min_num = min(min_num, matrix[x2][c])

    for r in range(x1 + 1, x2 + 1): # 왼쪽
        matrix[r - 1][y1] = matrix[r][y1]
        min_num = min(min_num, matrix[r][y1])
    for r in range(x2 - 1, x1 - 1, -1): # 오른쪽
        matrix[r + 1][y2] = matrix[r][y2]
        min_num = min(min_num, matrix[r][y2])

    matrix[x1 + 1][y2] = right_tmp
    matrix[x2 - 1][y1] = left_tmp
    min_num = min(min_num, right_tmp)
    min_num = min(min_num, left_tmp)
    return min_num

def solution(rows, columns, queries):
    answer = []
    num = 1
    matrix = []
    for r in range(rows):
        matrix.append([])
        for _ in range(columns):
            matrix[r].append(num)
            num += 1

    for x1, y1, x2, y2 in queries:
        min_num = rotate(matrix, x1 - 1, y1 - 1, x2 - 1, y2 - 1)
        answer.append(min_num)
    return answer