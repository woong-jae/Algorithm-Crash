import java.util.ArrayList;
import java.util.List;

class Node {
    int data, left, right;

    public Node(int data, int left, int right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    private final int INF = Integer.MAX_VALUE;
    private final int MAX_SECTION = 10001;
    private List<Node>[] list;
    private int[][] cost;
    public int solution(int k, int[] num, int[][] links) {
        int sum = 0;
        int size = num.length;
        boolean[] check = new boolean[size];

        list = new ArrayList[size];
        for (int i = 0; i < size; i++)
            list[i] = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int left = links[i][0];
            int right = links[i][1];

            if(left != -1) check[left] = true;
            if(right != -1) check[right] = true;

            list[i].add(new Node(num[i], left, right));
            sum += num[i];
        }

        int root = -1;

        for (int i = 0; i < size; i++)
            if (!check[i]) root = i;

        int left = sum / k;
        int right = sum;

        if (left == right) return right;

        else {
            while (left + 1 < right) {
                int mid = (left + right) / 2;
                cost = new int[size][2];
                traversal(root, mid);

                if(cost[root][0] <= k)
                    right = mid;
                else
                    left = mid;
            }
            return right;
        }

    }

    private void traversal(int pos, int L) {
        Node current = list[pos].get(0);

        if (current.left != -1) traversal(current.left, L);
        if (current.right != -1) traversal(current.right, L);

        // Leaf Node
        if (current.left == -1 && current.right == -1) {
            if (current.data <= L) {
                cost[pos][0] = 1;
                cost[pos][1] = current.data;
            } else {
                cost[pos][0] = MAX_SECTION;
                cost[pos][1] = INF;
            }
        }
        // Full Node
        else if (current.left != -1 && current.right != -1) {
            // 1) pos + Left Tree + Right Tree <= L
            // Section : Left + Right - 1
            if (current.data + cost[current.left][1] + cost[current.right][1] <= L) {
                cost[pos][0] = cost[current.left][0] + cost[current.right][0] - 1;
                cost[pos][1] = current.data + cost[current.left][1] + cost[current.right][1];
            }
            // 2) pos + min(Left, Right) <= L
            // Section : Left + Right
            else if (current.data + Math.min(cost[current.left][1], cost[current.right][1]) <= L) {
                cost[pos][0] = cost[current.left][0] + cost[current.right][0];
                cost[pos][1] = current.data + Math.min(cost[current.left][1], cost[current.right][1]);
            }
            // 3) pos <= L
            // Section : Left + Right + 1
            else if (current.data <= L) {
                cost[pos][0] = cost[current.left][0] + cost[current.right][0] + 1;
                cost[pos][1] = current.data;
            }

            else {
                cost[pos][0] = MAX_SECTION;
                cost[pos][1] = INF;
            }
        }

        else {
            if (current.right == -1) {
                // 1) pos + Left Tree <= L
                // Section : Left
                if (current.data + cost[current.left][1] <= L) {
                    cost[pos][0] = cost[current.left][0];
                    cost[pos][1] = current.data + cost[current.left][1];
                }
                // 2) pos <= L
                // Section : Left + 1
                else if (current.data <= L) {
                    cost[pos][0] = cost[current.left][0] + 1;
                    cost[pos][1] = current.data;
                }

                else {
                    cost[pos][0] = MAX_SECTION;
                    cost[pos][1] = INF;
                }
            }
            if (current.left == -1) {
                // 1) pos + Right Tree <= L
                // Section : Right
                if (current.data + cost[current.right][1] <= L) {
                    cost[pos][0] = cost[current.right][0];
                    cost[pos][1] = current.data + cost[current.right][1];
                }
                // 2) pos <= L
                // Section : Right + 1
                else if (current.data <= L) {
                    cost[pos][0] = cost[current.right][0] + 1;
                    cost[pos][1] = current.data;
                }

                else {
                    cost[pos][0] = MAX_SECTION;
                    cost[pos][1] = INF;
                }
            }
        }
    }
}