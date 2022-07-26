import java.util.HashSet;
import java.util.Set;

class Solution {
    private int size, attributeNum;
    private Set<String> uniqueSet = new HashSet<>();
    public int solution(String[][] relation) {
        size = relation.length;
        attributeNum = relation[0].length;

        for (int i = 1; i <= attributeNum; i++)
            checkUniqueness(i, 0, new boolean[attributeNum], relation);

        return uniqueSet.size();
    }

    private void checkUniqueness(int i, int depth, boolean[] visited, String[][] relation) {
        if (i == depth) {
            Set<String> testSet = new HashSet<>();

            for (String[] r : relation) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < attributeNum; j++)
                    if(visited[j])
                        builder.append(r[j]);
                testSet.add(builder.toString());
            }

            if (testSet.size() == size) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < attributeNum; j++)
                    if (visited[j])
                        builder.append(j);
                if(isMinimality(builder.toString()))
                    uniqueSet.add(builder.toString());
            }
            return;
        }

        for (int j = 0; j < attributeNum; j++)
            if ( !visited[j]) {
                visited[j] = true;
                checkUniqueness(i, depth + 1, visited, relation);
                visited[j] = false;
            }
    }

    private boolean isMinimality(String attributes) {
        for (String s : uniqueSet) {
            int count = 0;
            for (int i = 0; i < s.length(); i++)
                if(attributes.contains(String.valueOf(s.charAt(i))))
                    count++;
            if(count == s.length())
                return false;
        }
        return true;
    }
}