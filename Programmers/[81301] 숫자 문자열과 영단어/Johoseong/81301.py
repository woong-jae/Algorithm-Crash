def solution(s):
    words = ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']

    arr = s
    for i in range(10):
        arr = arr.replace(words[i], str(i))
    
    return int(arr)

print(solution("one4seveneight"))