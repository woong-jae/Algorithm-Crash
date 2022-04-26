def length(num, hand):
    row = abs(((num - 1) // 3) - ((hand - 1) // 3))
    col = abs(((num - 1) % 3) - ((hand - 1) % 3))
    return row + col

def solution(numbers, hand):
    answer = ''

    r_hand = 12
    l_hand = 10

    for n in numbers:
        if n == 1 or n == 4 or n == 7:
            answer += 'L'
            l_hand = n
        elif n == 3 or n == 6 or n == 9:
            answer += 'R'
            r_hand = n
        else:
            tmp = n
            if tmp == 0:
                tmp = 11
            r_len = length(tmp, r_hand)
            l_len = length(tmp, l_hand)

            if r_len < l_len:
                answer += 'R'
                r_hand = tmp
            elif r_len > l_len:
                answer += 'L'
                l_hand = tmp
            else:
                if hand == 'right':
                    answer += 'R'
                    r_hand = tmp
                else:
                    answer += 'L'
                    l_hand = tmp

    return answer
