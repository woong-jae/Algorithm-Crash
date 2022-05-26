# [17685] 자동완성
## Algorithm
- **Sorting**

## Logic
- 문자열들을 사전순서대로 정렬한다 -> 다음 문자열과의 공통 부분 문자열 비교를 위해
- 공통 부분 문자열의 길이를 저장할 단어수 크기의 배열을 만든다
- 현재 문자열과 다음 문자열의 공통 부분 문자열의 길이를 구하여 현재 배열이 가지고있는 값과의 최댓값을 비교
- 다음 문자열의 공통 부분 문자열 배열에 공통 부분 문자열의 길이를 넣어준다
- 위를 `단어 개수 - 1`만큼 반복 -> 마지막 단어는 비교할 다음 단어가 없음
- 반복을 마치고 answer에다가 답을 더할 때
  - 자기 자신이 공통 부분 문자열일 경우에는 자신의 길이만큼만 answer에 더한다
  - 그 외에는 공통 부분 문자열의 수에  +1 한 값을 answer에 더한다

```java
list.sort(Comparator.naturalOrder());

for (int i = 0; i < len - 1; i++) {
    int commonLength = commonStringLength(list.get(i), list.get(i + 1));
    lengths[i] = Math.max(lengths[i], commonLength);
    lengths[i + 1] = commonLength;
}

for (int i = 0; i < len; i++) {
    answer += lengths[i];

    if(lengths[i] != list.get(i).length())
        answer += 1;
}
```

## Review
처음에는 `startsWith()`함수로 이중반복문으로 구현하여 풀이 했는데 7문제 정도 시간초과가 났다  
그래서 반복문 하나로 구현하는 방법을 생각하다가 사전순서로 정렬을 한 후에 
뒤의 문자열과 공통 부분 문자열을 구하는 방법이 생각나서 풀었더니 맞았다
많이 어렵지는 않았던 문제! 레벨4는 아닌것 같다
