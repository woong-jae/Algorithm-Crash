import java.util.*;

class Solution {
    enum Command {
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
        },
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
        },
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
        },
        PRINT {
            @Override
            public void execute(StringTokenizer stringTokenizer) {
                Coordinate coordinate = Coordinate.of(stringTokenizer);
                printedMessage.add(table.getOrDefault(coordinate, "EMPTY"));
            }
        };

        private static final Map<Coordinate, String> table = new HashMap<>();
        private static final List<Set<Coordinate>> mergedSets = new ArrayList<>();
        private static final List<String> printedMessage = new ArrayList<>();

        public abstract void execute(StringTokenizer stringTokenizer);

        public static String[] getMessages() {
            return printedMessage.toArray(new String[0]);
        }

        private static Set<Coordinate> getMergedSet(Coordinate coordinate) {
            return mergedSets.stream()
                    .filter(set -> set.contains(coordinate))
                    .findAny()
                    .orElse(new HashSet<>(Set.of(coordinate)));
        }
    }

    static class Coordinate {
        private final int r;
        private final int c;

        private Coordinate(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public static Coordinate of(StringTokenizer stringTokenizer) {
            return new Coordinate(
                    Integer.parseInt(stringTokenizer.nextToken()),
                    Integer.parseInt(stringTokenizer.nextToken()));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return r == that.r && c == that.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    public String[] solution(String[] commands) {
        for (String command : commands) {
            StringTokenizer st = new StringTokenizer(command);
            Command.valueOf(st.nextToken()).execute(st);
        }
        return Command.getMessages();
    }
}