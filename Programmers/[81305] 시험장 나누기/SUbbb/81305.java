import java.util.*;

class ExamRoom {
    int n;
    int root;
    int count = 0;
    int[] left;
    int[] right;
    int[] x; // 시험장의 응시 인원
    int[] parent;
    
    public ExamRoom(int[] num, int[][] links) {
        this.n = num.length;
        parent = new int[n];
        left = new int[n];
        right = new int[n];
        x = new int[n];
        Arrays.fill(parent, -1);
        
        init(num, links);
        setRoot();
    }
    
    private void init(int[] num, int[][] links) {
        for (int i = 0; i < n; i++) {
            int l1 = links[i][0];
            int l2 = links[i][1];
            
            // 자식 지정
            left[i] = l1;
            right[i] = l2;
            x[i] = num[i];
            
            // 부모 지정
            if (l1 != -1) parent[left[i]] = i;
            if (l2 != -1) parent[right[i]] = i;
        }
    }
    
    // parent[i]가 -1이라면 root
    private void setRoot() {
        for (int i = 0; i < n; i++) {
            if (parent[i] == -1) {
                root = i;
                return;
            }
        }
    }
    
    public int getAnswer(int k) {
        // start는 num의 최댓값
        int start = x[0];
        for (int i : x)
            start = Math.max(start, i);
        
        // end는 num의 최댓값 * num의 길이
        int end = start * n;
        while(start < end) {
            int mid = (start + end) / 2;
            if (solve(mid) <= k) end = mid;
            else start = mid + 1;
        }
        
        return start;
    }
    
    private int solve(int limit) {
        count = 0;
        dfs(root, limit);
        // 맨 마지막에 남은 인원들에 대한 그룹 하나 추가
        return count + 1;
    }
    
    private int dfs(int cur, int limit) {
        // 왼쪽과 오른쪽 자식 트리에서 넘어오는 인원 수
        int leftValue = 0, rightValue = 0;
        if (left[cur] != -1) leftValue = dfs(left[cur], limit);
        if (right[cur] != -1) rightValue = dfs(right[cur], limit);
        
        // 두 자식 트리에서 넘어오는 인원을 모두 감당할 수 있는 경우, 그룹 수는 증가하지 않음
        if (x[cur] + leftValue + rightValue <= limit) 
            return x[cur] + leftValue + rightValue;
        
        // 두 자식 트리 중 작은 값을 합쳐야 감당할 수 있는 경우, 즉 자식 노드 하나를 끊는 경우로 그룹이 1개 추가
        if (x[cur] + Math.min(leftValue, rightValue) <= limit) {
            count++;
            return x[cur] + Math.min(leftValue, rightValue);
        }
        
        // 두 자식 트리 모두 감당할 수 없어서 둘 다 끊는 경우, 그룹이 2개 추가
        count += 2;
        return x[cur];
    }
}

class Solution {
    public int solution(int k, int[] num, int[][] links) {
        ExamRoom er = new ExamRoom(num, links);
        
        return er.getAnswer(k);
    }
}