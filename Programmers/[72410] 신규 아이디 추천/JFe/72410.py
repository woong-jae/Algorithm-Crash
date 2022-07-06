import re
def solution(new_id):
    new_id = new_id.lower()                                 # 1단계
    new_id = re.sub('[^a-z0-9-_.]', '', new_id)             # 2단계
    new_id = re.sub('[.]+', '.', new_id)                    # 3단계
    new_id = new_id.strip('.')                              # 4단계
    if len(new_id) == 0: new_id = 'a'                       # 5단계
    if len(new_id) >= 16: new_id = new_id[:15].rstrip('.')  # 6단계
    while len(new_id) <= 2:                                 # 7단계
        new_id += new_id[-1]
    return new_id