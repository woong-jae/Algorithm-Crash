def solution(n, m, x, y, queries):
    top, bottom, left, right = x, x, y, y
    for flag, dx in queries[::-1]:
        if flag == 0:
            if left != 0: left += dx
            right += dx 
            if right > m - 1: right = m - 1 
        elif flag == 1:
            if right != m - 1: right -= dx
            left -= dx
            if left < 0: left = 0
        elif flag == 2:
            if top != 0: top += dx
            bottom += dx
            if bottom > n - 1: bottom = n - 1
        elif flag == 3:
            if bottom != n - 1: bottom -= dx
            top -= dx
            if top < 0: top = 0
        if top >= n or bottom < 0 or left < 0 or right >= m: return 0
    return (bottom - top + 1) * (right - left + 1)