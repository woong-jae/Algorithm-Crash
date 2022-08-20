import sys, re
S = sys.stdin.readline()
K = sys.stdin.readline()
temp = re.sub('[0-9]', '', S)   # 정규표현식 사용해 숫자 제거
# temp 문자열에서 K 문자열을 찾으면 1 출력, 없으면 0 출력
if temp.find(K) != -1: print(1)
else: print(0)