# [136797] 숫자 타자 대회
## Algorithm
- **DP**, **BFS**

## Logic
- 각 키에서 다른 키로 이동하는 가중치 2차원 배열을 BFS를 사용해 구한다
```java
private void setWeight(int x, int y) {
    Queue<Node> queue = new PriorityQueue<>(Comparator.comparing(Node::weight));
    queue.add(new Node(x, y, 0));

    while (!queue.isEmpty()) {
        Node current = queue.poll();
        int number = keys[x][y];
        if (weights[number][keys[current.x()][current.y()]] > -1) {
            continue;
        }

        weights[number][keys[current.x()][current.y()]] = current.weight();
        weights[keys[current.x()][current.y()]][number] = current.weight();

        for (int d = 0; d < 8; d++) {
            int nx = current.x() + dx[d];
            int ny = current.y() + dy[d];

            if (!isInbound(nx, ny)) {
                continue;
            }
            if (keys[nx][ny] == -1) {
                continue;
            }
            if (weights[number][keys[nx][ny]] > -1) {
                continue;
            }
            queue.add(new Node(nx, ny, current.weight() + (d % 2 == 0 ? 3 : 2)));
        }
    }
}
```
- 숫자에 따라 왼쪽과 오른쪽에 따른 가중치의 최솟값으로 DP배열을 구한다
```java
private int simulate(int index, int length, int left, int right, String numbers) {
    if (index == length) {
        return 0;
    }
    if (dp[index][left][right] != -1) {
        return dp[index][left][right];
    }

    int number = Character.getNumericValue(numbers.charAt(index));
    int result = Integer.MAX_VALUE;

    if (number != right) {
        result = Math.min(result,
                simulate(index + 1, length, number, right, numbers) + weights[left][number]);
    }
    if (number != left) {
        result = Math.min(result,
                simulate(index + 1, length, left, number, numbers) + weights[right][number]);
    }
    return dp[index][left][right] = result;
}
```

## Review
처음엔 Greedy 알고리즘을 통해 풀이했는데 절반 이상의 테케에서 틀렸다  
이후 다른 사람들의 풀이를 참고해 DP를 활용해 풀이했다  
의아했던 건 다른 사람들은 키들의 가중치를 그냥 입력하고 시작했다는 것인데, 다들 손으로 계산한 걸 넣은 건지는 모르겠지만  
가중치 배열을 구하는 코드 또한 있어야 한다고 생각한다!
