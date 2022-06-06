import java.util.*;

class Loc {
    int x;
    int y;
    int value;

    public Loc(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}

class Node {
    Loc data;
    Node left;
    Node right;

    public Node(Loc data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class Tree {
    Node root;
    int[] list;
    int size;
    int idx;

    public Tree(int size) {
        this.root = null;
        this.size = size;
    }

    public void insert(Node input) {
        // root가 없다면 root 지정, root가 있다면, 자식 노드 탐색하여 저장
        if (root == null) root = input;
        else insert(input, root);
    }

    private void insert(Node input, Node root) {
        // 부모보다 x값이 작은 경우에는 left를 탐색, 그렇지 않으면 right 탐색
        if (input.data.x < root.data.x) {
            if (root.left == null) root.left = input;
            else insert(input, root.left);
        } else {
            if (root.right == null) root.right = input;
            else insert(input, root.right);
        }
    }

    public int[] preOrder() {
        list = new int[size];
        idx = 0;
        preOrderTraversal(root);

        return list;
    }

    private void preOrderTraversal(Node n) {
        if (n == null) return;

        list[idx++] = n.data.value;
        preOrderTraversal(n.left);
        preOrderTraversal(n.right);
    }

    public int[] postOrder() {
        list = new int[size];
        idx = 0;
        postOrderTraversal(root);

        return list;
    }

    private void postOrderTraversal(Node n) {
        if (n == null) return;

        postOrderTraversal(n.left);
        postOrderTraversal(n.right);
        list[idx++] = n.data.value;
    }
}

class Solution {
    public int[][] solution(int[][] nodeinfo) {
        ArrayList<Loc> locs = new ArrayList<>();
        int size = nodeinfo.length;

        for (int i = 0; i < size; i++)
            locs.add(new Loc(nodeinfo[i][0], nodeinfo[i][1], i + 1));

        // x값은 오름차순, y값은 내림차순으로 정렬하여 BFS 형식으로 변환
        locs.sort(((o1, o2) -> {
            if (o1.y == o2.y) return o1.x - o2.x;
            return o2.y - o1.y;
        }));

        Tree t = new Tree(size);

        for (Loc l : locs) 
            t.insert(new Node(l));
        
        return new int[][] {t.preOrder(), t.postOrder()};
    }
}