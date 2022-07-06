# [72410] 신규 아이디 추천 - Python

## 🔍 Algorithm
**문자열**

## 💻 Logic

```Python
new_id = new_id.lower()                                 # 1단계
```
- **1단계 : new_id의 모든 대문자를 대응되는 소문자로 치환**  

```Python
new_id = re.sub('[^a-z0-9-_.]', '', new_id)             # 2단계
```
- **2단계 : new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자 제거**  

```Python
new_id = re.sub('[.]+', '.', new_id)                    # 3단계
```
- **3단계 : new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환**  

```Python
new_id = new_id.strip('.')                              # 4단계
```
- **4단계 : new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거**  

```Python
if len(new_id) == 0: new_id = 'a'                       # 5단계
```
- **5단계 : new_id가 빈 문자열이라면, new_id에 "a"를 대입**  

```Python
if len(new_id) >= 16: new_id = new_id[:15].rstrip('.')  # 6단계
```
- **6단계 : new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거**  
    (제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거)

```Python
while len(new_id) <= 2:                                 # 7단계
    new_id += new_id[-1]
```
- **7단계 : new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 추가**  


## 📝 Review

특정 문자를 제거하고 치환하는 과정들이 있어서 정규 표현식을 사용해서 해결했다.  
주어진 단계별로 구현하면 되는 간단한 문자열 문제.  
