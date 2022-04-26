# [67257] 수식 최대화
## Algorithm
- **Permutation**

## Logic
- 문자열을 숫자와 연산자로 분리하여 각각의 `ArrayList`에 넣는다
- `*,+,-`의 6가지 경우의 수를 구하기 위해 순열을 재귀를 이용해 구현
- 정해진 우선순위를 가지고 `연산자 ArrayList`에서 연산자를 비교하여
- 만약 같으면 `숫자 ArrayList`에서 해당 연산자 인덱스와 같은 인덱스의 숫자 값을 2개를 가져와 
- 연산을 진행하고 결과값을 원래 숫자가 있던 인덱스에 삽입해준다

```java
private Long calculate(int[] permArray) {
    String[] operator = {"*", "+", "-"};
    ArrayList<Long> copiedNum = new ArrayList<>(numList);
    ArrayList<String> copiedOpr = new ArrayList<>(operatorList);

    for(int i : permArray) {
        int size = copiedOpr.size();

        for (int j = 0; j < size; j++)
            if (copiedOpr.get(j).equals(operator[i])) {
                long a = copiedNum.get(j), b = copiedNum.get(j + 1);

                copiedNum.remove(j);
                copiedNum.remove(j);
                copiedOpr.remove(j);

                switch (operator[i]) {
                    case "+":
                        copiedNum.add(j, a + b);
                        break;
                    case "-":
                        copiedNum.add(j, a - b);
                        break;
                    case "*":
                        copiedNum.add(j, a * b);
                }

                j--;
                size--;
            }
    }

    return copiedNum.get(0);
}
```

## Review
아이디어 생각은 금방 났는데 코드 자체가 깔끔하게 구현하기가 어려웠던 문제였다   
나는 순열을 이용해서 구현했는데 경우의 수가 6가지로 고정되어있다보니 다들 그냥 6개를 반복하여  
최댓값을 구한걸 보면 굳이 순열을 구현했어야했나 싶기도 하다...

