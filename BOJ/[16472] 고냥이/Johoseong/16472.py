import sys
input = sys.stdin.readline

N = int(input())
string = input().strip()
start = 0
end = 0
alphabet = dict()
answer = 0

while start <= end:
    if end >= len(string):
        break

    if (string[end] not in alphabet):
        if len(alphabet) < N:
            alphabet[string[end]] = end #처음발견, 끝발견 인덱스
        else:
            tmp = 100000
            for al in alphabet:
                e = alphabet[al]
                tmp = min(tmp, e)
                if tmp == e:
                    tmp_al = al
            alphabet.pop(tmp_al)
            start = tmp + 1
            alphabet[string[end]] = end

    else:
        alphabet[string[end]] = end

    answer = max(answer, end - start + 1)
    end += 1

print(answer)