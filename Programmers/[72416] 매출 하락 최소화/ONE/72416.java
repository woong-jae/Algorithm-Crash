import java.util.ArrayList;
import java.util.List;

class Solution {
    private int[] sales;
    private List<Integer>[] children;
    private int[][] cost;
    public int solution(int[] sales, int[][] links) {
        this.sales = sales;
        children = new List[sales.length + 1];
        for(int i = 1; i <= sales.length; i++)
            children[i] = new ArrayList<>();
        // 학생수와 넣고/안넣고를 구분하여 총 cost를 저장할 2차원 배열
        cost = new int[sales.length + 1][2];

        for (int[] link : links)
            children[link[0]].add(link[1]);

        traversal(1);

        // 순회를 마치고 root 에서 [0]과 [1]중에 최솟값을 찾는다
        return Math.min(cost[1][0], cost[1][1]);
    }

    private void traversal(int node) {

        // 초기에 선택안하는 값은 0, 선택한값은 자기 자신의 cost로 설정
        cost[node][0] = 0;
        cost[node][1] = sales[node - 1];

        if(children[node].isEmpty())
            return;

        // extra 는 부모가 참석 안했을때 자식이 참석해서 생기는 비용
        int extra = Integer.MAX_VALUE;
        for (int child : children[node]) {
            traversal(child);
            if (cost[child][0] < cost[child][1]) {
                cost[node][0] += cost[child][0];
                cost[node][1] += cost[child][0];
                // 자식 비용들중에서 최솟값 찾기
                extra = Math.min(extra, cost[child][1] - cost[child][0]);
            } else {
                cost[node][0] += cost[child][1];
                cost[node][1] += cost[child][1];
                extra = 0;
            }
        }
        cost[node][0] += extra;
    }
}