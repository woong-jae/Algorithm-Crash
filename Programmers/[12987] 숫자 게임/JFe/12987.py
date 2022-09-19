def solution(A, B):
    answer, pointer = 0, 0
    A.sort()
    B.sort()
    # 오름차순 정렬한 A 값들을 오름차순 정렬한 B와 비교하면서 얻을 수 있는 승점 체크
    for a in A:
        # pointer가 B 길이까지 가거나 a보다 큰 B 값을 만날 때까지 pointer 증가
        while pointer < len(B) and a >= B[pointer]: pointer += 1
        # pointer가 B 길이 이상이 되면 더 이상 승점을 얻을 수 없으므로 break
        if pointer >= len(B): break
        answer += 1
        pointer += 1
    return answer