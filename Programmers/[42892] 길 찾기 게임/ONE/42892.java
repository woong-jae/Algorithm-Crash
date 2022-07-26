import java.util.ArrayList;
import java.util.List;

class Node {
    int num;
    int x;
    int y;
    Node left;
    Node right;

    public Node(int num, int x, int y, Node left, Node right) {
        this.num = num;
        this.x = x;
        this.y = y;
        this.left = left;
        this.right = right;
    }

    public void insert(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null)
                parent.left = child;
            else
                insert(parent.left, child);
        }
        else {
            if (parent.right == null)
                parent.right = child;
            else
                insert(parent.right, child);
        }
    }
}

class Solution {
    private int[][] answer;
    private int index;
    public int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];

        List<Node> tree = new ArrayList<>();
        for(int i = 0; i < nodeinfo.length; i++)
            tree.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1], null, null));

        tree.sort((o1, o2) -> {
            if (o1.y == o2.y)
                return o1.x - o2.x;
            return o2.y - o1.y;
        });

        Node root = tree.get(0);

        for(int i = 1; i < tree.size(); i++)
            root.insert(root, tree.get(i));

        postorder(root);
        index = 0;
        preorder(root);

        return answer;
    }

    private void preorder(Node node) {
        if (node != null) {
            answer[0][index++] = node.num;
            preorder(node.left);
            preorder(node.right);
        }
    }

    private void postorder(Node node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            answer[1][index++] = node.num;
        }
    }
}