def solution(s):
    answer = 10 ** 11
    N = len(s)

    for n in range(1, (N // 2) + 1):
        word = s[:n]
        start = n
        cnt = 1
        str_zip = ""
        
        while (start <= (N + n)):
            if word == s[start:start + n]:
                cnt += 1
            else: # 다른 단어면?
                if cnt != 1:
                    str_zip += (str(cnt) + word)
                else:
                    str_zip += word
                word = s[start:start + n]
                cnt = 1

            start += n

        answer = min(answer, len(str_zip))

    return min(N, answer)