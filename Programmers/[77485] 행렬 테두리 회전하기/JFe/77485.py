def rotate(field, x1, y1, x2, y2):
    upper_right, lower_left, lower_right = field[x1][y2], field[x2][y1], field[x2][y2]
    num = min(upper_right, lower_left, lower_right)
    # 상단 가로
    for i in reversed(range(y1, y2)):
        field[x1][i + 1] = field[x1][i]
        num = min(num, field[x1][i])
    # 오른쪽 세로
    for i in reversed(range(x1, x2)):
        field[i + 1][y2] = field[i][y2]
        num = min(num, field[i][y2])
    field[x1 + 1][y2] = upper_right
    # 하단 가로
    for i in range(y1, y2 - 1):
        field[x2][i] = field[x2][i + 1]
        num = min(num, field[x2][i])
    field[x2][y2 - 1] = lower_right
    # 왼쪽 세로
    for i in range(x1, x2 - 1):
        field[i][y1] = field[i + 1][y1]
        num = min(num, field[i][y1])
    field[x2 - 1][y1] = lower_left
    return num
    
def solution(rows, columns, queries):
    answer = []
    field = [[j * columns + i for i in range(1, columns+1)] for j in range(rows)]
    for x1, y1, x2, y2 in queries:
        answer.append(rotate(field, x1-1, y1-1, x2-1, y2-1))
    return answer