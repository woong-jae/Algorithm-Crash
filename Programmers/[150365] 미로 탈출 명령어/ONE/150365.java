class Solution {
    enum Direction {
        DOWN('d', 1, 0),
        LEFT('l', 0, -1),
        RIGHT('r', 0, 1),
        UP('u', -1, 0);

        private final char initial;
        private final int dx;
        private final int dy;

        Direction(char initial, int dx, int dy) {
            this.initial = initial;
            this.dx = dx;
            this.dy = dy;
        }
    }

    static class Maze {
        private final int n;
        private final int m;

        public Maze(int n, int m) {
            this.n = n;
            this.m = m;
        }

        public boolean isInbound(int x, int y) {
            return x >= 1 && y >= 1 && x <= n && y <= m;
        }
    }

    static class Position {
        private final int x;
        private final int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int x() {
            return x;
        }

        public int y() {
            return y;
        }

        public boolean reach(Position goal) {
            return this.x() == goal.x() && this.y() == goal.y();
        }

        public int getDistance(Position goal) {
            return Math.abs(this.x() - goal.x()) + Math.abs(this.y() - goal.y());
        }
    }

    private String answer = "";
    private Maze maze;
    private Position goal;
    private char[] route;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        maze = new Maze(n, m);
        goal = new Position(r, c);
        route = new char[k];

        Position current = new Position(x, y);
        if (!canArrive(current, k)) {
            return "impossible";
        }
        findRoute(current, 0, k);
        return answer;
    }

    private boolean canArrive(Position position, int k) {
        int minDistance = position.getDistance(goal);
        return minDistance <= k && (k - minDistance) % 2 != 1;
    }

    private void findRoute(Position current, int depth, int k) {
        if (!answer.isEmpty()) {
            return;
        }

        if (depth == k) {
            if (current.reach(goal)) {
                answer = new String(route);
            }
            return;
        }

        for (Direction direction : Direction.values()) {
            int nx = current.x() + direction.dx;
            int ny = current.y() + direction.dy;

            if (!maze.isInbound(nx, ny)) {
                continue;
            }
            Position next = new Position(nx, ny);
            if (!canArrive(next, k - depth - 1)) {
                continue;
            }
            route[depth] = direction.initial;
            findRoute(next, depth + 1, k);
        }
    }
}
