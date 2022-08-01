def solution(n):
    answer = []
    # n이 0이 될 때까지 반복
    while n:
        # 3으로 나눈 나머지를 answer 리스트에 추가 (나머지가 0이면 4 추가)
        r = n % 3
        if r == 0: r = 4
        answer.append(str(r))
        # n-1을 3으로 나눈 몫을 n에 저장 (3진법이기 때문에 n-1을 나눠줘야 함)
        n = (n-1) // 3
    return ''.join(answer[::-1])    # 리스트를 반대로 문자열 변환하여 반환