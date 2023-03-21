# [150366] 표 병합
## Algorithm
- **Implementation**

## Logic
- enum 을 사용해 각 발생할 수 있는 command 들을 구분하고 공통으로 추상메서드 execute()를 구현해 각 command 에 맞는 내부 로직을 구현
  - Update 는 2가지 경우로 특정 값을 가진 셀들을 업데이트하거나, 특정 위치의 값을 업데이트 하는데
  - 이 때, 특정 위치의 값이 병합된 셀이면 다른 병합된 셀들의 값들도 업데이트한다
    ```java
    UPDATE {
        @Override
        public void execute(StringTokenizer stringTokenizer) {
            if (stringTokenizer.countTokens() == 2) {
                // 특정 값을 가진 위치들의 값을 전부 변경하는 로직
                String old = stringTokenizer.nextToken();
                String renew = stringTokenizer.nextToken();
                table.keySet().stream()
                        .filter(coordinate -> table.get(coordinate).equals(old))
                        .forEach(coordinate -> table.put(coordinate, renew));

            }
            if (stringTokenizer.countTokens() == 3) {
                // 특정 위치의 값을 변경하는 로직
                Coordinate coordinate = Coordinate.of(stringTokenizer);
                String renew = stringTokenizer.nextToken();

                table.put(coordinate, renew);
                mergedSets.stream()
                        .filter(set -> set.contains(coordinate))
                        .flatMap(Set::stream)
                        .forEach(c -> table.put(c, renew));
            }
        }
    }
    ```
  - Merge 는 두 개의 셀을 병합하는 데, 셀의 값에 여부에 따라 기준 값을 설정한다
      ```java
        MERGE {
            @Override
            public void execute(StringTokenizer stringTokenizer) {
                Coordinate coordinate_1 = Coordinate.of(stringTokenizer);
                Coordinate coordinate_2 = Coordinate.of(stringTokenizer);

                // 같은 위치일 경우 무시한다
                if (coordinate_1.equals(coordinate_2)) {
                    return;
                }
                merge(coordinate_1, coordinate_2);
            }

            private void merge(Coordinate coordinate_1, Coordinate coordinate_2) {
                Set<Coordinate> origin = getMergedSet(coordinate_1);
                Set<Coordinate> another = getMergedSet(coordinate_2);

                origin.addAll(another);
                mergedSets.remove(another);

                // 1. 두 셀 중 첫 번째가 값이 있을 때 & 두 셀 모두 값이 있을 때 첫 번째의 값을 넣음
                if (table.containsKey(coordinate_1)) {
                    for (Coordinate coordinate : origin) {
                        table.put(coordinate, table.get(coordinate_1));
                    }
                }

                // 2. 두 셀 중 두 번째가 값이 있을 때
                if (!table.containsKey(coordinate_1) && table.containsKey(coordinate_2)) {
                    for (Coordinate coordinate : origin) {
                        table.put(coordinate, table.get(coordinate_2));
                    }
                }

                if (!mergedSets.contains(origin)) {
                    mergedSets.add(origin);
                }
            }
        }
    ```
  - Unmerge 는 병합된 다른 셀들의 값들을 초기화시켜주고 병합여부를 알리는 Set을 제거한다
    ```java
        UNMERGE {
            @Override
            public void execute(StringTokenizer stringTokenizer) {
                Coordinate origin = Coordinate.of(stringTokenizer);

                Set<Coordinate> coordinates = getMergedSet(origin);
                for (Coordinate coordinate : coordinates) {
                    if (coordinate.equals(origin)) {
                        continue;
                    }
                    // 기준되는 좌표를 제외한 모든 좌표의 값을 초기 상태로 변경(제거)
                    table.remove(coordinate);
                }
                mergedSets.remove(coordinates);
            }
        }
    ```
  - Print 는 해당 셀에 값이 있다면 출력하고 없다면 "EMPTY" 를 출력하도록 한다
    ```java
        PRINT {
            @Override
            public void execute(StringTokenizer stringTokenizer) {
                Coordinate coordinate = Coordinate.of(stringTokenizer);
                printedMessage.add(table.getOrDefault(coordinate, "EMPTY"));
            }
        }
    ```

## Review
병합에 대한 로직을 작성하는게 어려웠던 것 같다  
그리고 소요시간과 메모리 사용량이 너무 많아 비효율적으로 짰다는 것을 알 수 있었다  
다른 풀이 중에 Union-Find 가 있는 걸 봤는데 좋은 아이디어 인 것 같다!  
나중에 비슷한 문제를 풀게 되면 참고하면 되겠다...