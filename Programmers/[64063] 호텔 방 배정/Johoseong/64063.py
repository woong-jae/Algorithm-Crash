import sys
sys.setrecursionlimit(10 ** 6)

def union_find(num):
    if num not in rooms: # 배정 안해준 방 발견
        rooms[num] = num + 1 
        answer.append(num)
        return num + 1

    rooms[num] = union_find(rooms[num]) # 루트 방번호로 갱신
    return rooms[num]

def solution(k, room_number):
    global rooms, answer
    answer = []
    rooms = dict() # 배정해준 방 딕셔너리

    for num in room_number:
        union_find(num)
        print(rooms)
    return answer

print(solution(10, [3, 3, 3, 3]))