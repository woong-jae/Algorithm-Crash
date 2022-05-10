def solution(n, k, cmd):
    answer = ['O' for i in range(1, n +1)]
    linked_list = { i: [i - 1, i + 1] for i in range(1, n + 1)}
    pointer = k + 1
    stack = []

    for c in cmd:
        if c[0] == 'U':
            for _ in range(int(c[2:])): # 인덱스 2 이후까지 숫자 있는거 생각하기
                pointer = linked_list[pointer][0]
        elif c[0] == 'D':
            for _ in range(int(c[2:])):
                pointer = linked_list[pointer][1]
        elif c[0] == 'C':
            answer[pointer - 1] = 'X'
            prev, next = linked_list[pointer]
            stack.append([pointer, prev, next])

            if next == n + 1: # 표 마지막 행 삭제했으면 그 전 값 가르킴
                pointer = linked_list[pointer][0]
            else: # 디폴트는 삭제한 행 다음 행 가르킴
                pointer = linked_list[pointer][1]
            
            if prev == 0:
                linked_list[next][0] = prev
            elif next == n + 1:
                linked_list[prev][1] = next
            else:
                linked_list[prev][1] = next
                linked_list[next][0] = prev
                
        else:
            now, prev, next = stack.pop()
            answer[now - 1] = 'O'

            if prev == 0:
                linked_list[next][0] = now
            elif next == n + 1:
                linked_list[prev][1] = now
            else:
                linked_list[prev][1] = now
                linked_list[next][0] = now

    return ''.join(answer)