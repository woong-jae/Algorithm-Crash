def solution(n, k, cmd):
    answer = ['O' for _ in range(n)]
    current, removed = k, []
    # 연결리스트 형태의 dictionary 생성 (첫 행과 마지막 행 연결 부분은 None)
    table = {i: [i-1, i+1] for i in range(1, n-1)}
    table[0] = [None, 1]
    table[n-1] = [n-2, None]
    for c in cmd:
        if c[0] == 'D':
            for _ in range(int(c[2:])):
                current = table[current][1]
        elif c[0] == 'U':
            for _ in range(int(c[2:])):
                current = table[current][0]
        elif c[0] == 'C':
            answer[current] = 'X'
            prev, next = table[current]
            removed.append((table[current], current))   # 삭제된 값과 위치 removed에 append
            # 첫 행, 마지막 행 삭제하는 경우 나눠서 처리
            if prev == None:
                table[next][0] = None
                current = next
            elif next == None:
                table[prev][1] = None
                current = prev
            else:
                table[prev][1] = next
                table[next][0] = prev
                current = next
        elif c[0] == 'Z':
            (prev, next), i = removed.pop()
            answer[i] = 'O'
            # 첫 행, 마지막 행 복구하는 경우 나눠서 처리
            if prev == None:
                table[next][0] = i
            elif next == None:
                table[prev][1] = i
            else:
                table[prev][1] = i
                table[next][0] = i
    return ''.join(answer)