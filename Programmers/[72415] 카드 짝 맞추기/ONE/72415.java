import java.util.*;

class Solution {
    static int posR, posC;
    static int[][] g_map;
    static HashMap<Integer, ArrayList<Card>> cardPos;
    public int solution(int[][] board, int r, int c) {
        posR = r;
        posC = c;
        g_map = board;

        cardPos = new HashMap<>();

        Set<Integer> set = new HashSet<>();
        for (int i=0; i<board.length; i++) {
            for (int j=0; j < board[i].length; j++) {
                int index = board[i][j] ;
                if (index == 0)
                    continue;
                set.add(index);
                if (cardPos.containsKey(index)) {
                    ArrayList<Card> list = cardPos.get(index);
                    list.add(new Card(index, i, j));
                } else {
                    ArrayList<Card> list = new ArrayList<>();
                    list.add(new Card(index, i, j));
                    cardPos.put(index, list);
                }
            }
        }

        int[] visitCase = set.stream().mapToInt(i->i).toArray();
        min = Integer.MAX_VALUE;
        permutation(visitCase, 0, visitCase.length - 1);

        return min;
    }

    private void permutation(int[] visitCase, int s, int e) {
        if (s == e) {
            int[][] map = getDuplicateMap();
            visit(map, visitCase, posR, posC, 0, 0);
        } else {
            for (int i=s; i<=e; i++) {
                swap(visitCase, s, i);
                permutation(visitCase, s + 1, e);
                swap(visitCase, s, i);
            }
        }
    }

    private void swap(int[] visitCase, int s, int i) {
        int temp = visitCase[s];
        visitCase[s] = visitCase[i];
        visitCase[i] = temp;
    }

    static int min;
    private void visit(int[][] map, int[] visitCase, int x, int y, int index, int count) {
        if (count > min)
        {
            return;
        }
        if (index == visitCase.length) {
            min = Math.min(count, min);
            return;
        }

        ArrayList<Card> cards = cardPos.get(visitCase[index]);
        Card card1 = cards.get(0);
        Card card2 = cards.get(1);


        int nextCnt;
        nextCnt = count  + getDistance(map, x, y, card1, card2);
        visit(map, visitCase, card2.x, card2.y, index + 1, nextCnt);
        map[card1.x][card1.y] = card1.index;
        map[card2.x][card2.y] = card2.index;
        nextCnt = count  + getDistance(map, x, y, card2, card1);
        visit(map, visitCase, card1.x, card1.y, index + 1, nextCnt);
        map[card1.x][card1.y] = card1.index;
        map[card2.x][card2.y] = card2.index;

    }


    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    private int getDistance(int[][] map, int x, int y, Card card1, Card card2) {
        int visitCnt = getVisitCnt(map, x, y, card1);
        visitCnt += getVisitCnt(map, card1.x, card1.y, card2);
        map[card1.x][card1.y] = 0;
        map[card2.x][card2.y] = 0;
        return visitCnt + 2;
    }

    private int getVisitCnt(int[][] map, int x, int y, Card card) {
        boolean[][] visited = new boolean[4][4];
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(x, y, 0));
        visited[x][y] = true;
        int visitCnt = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.x == card.x && cur.y == card.y) {
                visitCnt = cur.count;
                break;
            }

            for (int i=0; i<4; i++) {
                int nx;
                int ny;

                nx = cur.x;
                ny = cur.y;
                while (true) {
                    nx += dx[i];
                    ny += dy[i];
                    if (nx >=0 && nx <4 && ny >= 0 && ny <4) {
                        if (map[nx][ny] > 0) {
                            if (!visited[nx][ny]) {
                                queue.add(new Node(nx, ny, cur.count + 1));
                                visited[nx][ny] = true;
                            }
                            break;
                        }
                    } else {
                        nx -= dx[i];
                        ny -= dy[i];
                        if (!visited[nx][ny]) {
                            queue.add(new Node(nx, ny, cur.count + 1));
                            visited[nx][ny] = true;
                        }
                        break;
                    }
                }


                nx = cur.x + dx[i];
                ny = cur.y + dy[i];

                if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && !visited[nx][ny]) {
                    queue.add(new Node(nx, ny, cur.count + 1));
                    visited[nx][ny] = true;
                }

            }
        }
        return visitCnt;
    }

    private int[][] getDuplicateMap() {
        int[][] map = new int[4][4];
        for (int i=0; i<4; i++) {
            System.arraycopy(g_map[i], 0, map[i], 0, 4);
        }
        return map;
    }

    class Card {
        int index;
        int x;
        int y;

        public Card(int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }
    }

    class Node implements Comparable<Node>{
        int x;
        int y;
        int count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.count, o.count);
        }
    }
}