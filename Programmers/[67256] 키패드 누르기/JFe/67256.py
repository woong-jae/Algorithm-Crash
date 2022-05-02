def solution(numbers, hand):
    answer = ''
    left, right = [0, 3], [2, 3]    # '*', '#' 좌표[x, y] 저장
    for n in numbers:
        if n == 0: n = 11   # 0은 계산 편하게 11로 저장
        x, y = (n-1)%3, (n-1)//3    # 해당 숫자의 키패드 상 좌표 계산
        # 키패드 위치에 따라 나눠서 처리
        if n == 1 or n == 4 or n == 7:
            answer += 'L'
            left = [x, y]
        elif n == 3 or n == 6 or n == 9:
            answer += 'R'
            right = [x, y]
        else:
            # 왼손, 오른손 거리 계산
            left_dir = abs(left[0]-x) + abs(left[1]-y)
            right_dir = abs(right[0]-x) + abs(right[1]-y)
            if left_dir < right_dir:
                answer += 'L'
                left = [x,y]
            elif left_dir > right_dir:
                answer += 'R'
                right = [x, y]
            # 거리가 같으면 hand 값에 따라서 처리
            else:
                if hand == "left": 
                    answer += 'L'
                    left = [x, y]
                else: 
                    answer += 'R'
                    right = [x, y]
    return answer