import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    private long answer = 0;
    private ArrayList<Long> numList;
    private ArrayList<String> operatorList;
    public long solution(String expression) {
        numList = makeNumList(expression);
        operatorList = makeOperatorList(expression);

        int[] permArray = new int[3];
        boolean[] visited = new boolean[3];

        for (int i = 0; i < 3; i++){
            permArray[0] = i;
            visited[i] = true;
            permutation(0, permArray, visited);
            visited[i] = false;
        }

        return answer;
    }

    private ArrayList<Long> makeNumList(String expression) {

        ArrayList<Long> numList = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+"); // 숫자만
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find())
            numList.add(Long.parseLong(matcher.group(0)));

        return numList;
    }

    private ArrayList<String> makeOperatorList(String expression) {

        ArrayList<String> operatorList = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\D+"); // 문자만
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find())
            operatorList.add(matcher.group(0));

        return operatorList;
    }

    private Long calculate(int[] permArray) {
        String[] operator = {"*", "+", "-"};
        ArrayList<Long> copiedNum = new ArrayList<>(numList);
        ArrayList<String> copiedOpr = new ArrayList<>(operatorList);

        for(int i : permArray) {
            int size = copiedOpr.size();

            for (int j = 0; j < size; j++)
                if (copiedOpr.get(j).equals(operator[i])) {
                    long a = copiedNum.get(j), b = copiedNum.get(j + 1);

                    copiedNum.remove(j);
                    copiedNum.remove(j);
                    copiedOpr.remove(j);

                    switch (operator[i]) {
                        case "+":
                            copiedNum.add(j, a + b);
                            break;
                        case "-":
                            copiedNum.add(j, a - b);
                            break;
                        case "*":
                            copiedNum.add(j, a * b);
                    }

                    j--;
                    size--;
                }
        }

        return copiedNum.get(0);
    }

    private void permutation(int depth, int[] permArray, boolean[] visited) {
        if (depth == 2) {
            answer = Math.max(answer, Math.abs(calculate(permArray)));
            return;
        }

        for (int i = 0; i < 3; i++)
            if (!visited[i]) {
                visited[i] = true;
                permArray[depth + 1] = i;
                permutation(depth + 1, permArray, visited);
                visited[i] = false;
            }
    }
}