import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.stream.Collectors.toList;

class Main {
    static class Parentheses {
        int open;
        int close;

        public Parentheses(int open) {
            this.open = open;
        }
    }

    private static boolean[] check;
    private static List<Parentheses> list = new ArrayList<>();
    private static Set<String> result = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();
        // 추후 문자열을 만들 때 표시를 위한 배열
        check = new boolean[expression.length()];

        // 괄호의 위치 쌍 찾기
        find(expression);

        // 순열로 사용할 괄호 쌍을 골라 문자열을 생성 후 Set에 넣는다 (중복 X)
        dfs(0, expression, new boolean[list.size()]);

        // 만들어진 문자열 중 모든 소괄호를 사용하는 문자열은 이전과 같기 때문에 Set에서 삭제
        result.remove(expression);
        // 사전순서로 정렬 후 List의 형태로 바꾸어 출력한다
        List<String> answer = result.stream().sorted().collect(toList());

        for (String s : answer)
            System.out.println(s);
    }

    private static void find(String expression) {
        Stack<Parentheses> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            // 여는 소괄호 일 때 스택에 삽입
            if (expression.charAt(i) == '(')
                stack.add(new Parentheses(i));
            // 닫는 소괄호 일 때 스택에서 꺼내어 닫는 위치를 저장하고 괄호쌍 리스트에 삽입
            else if (expression.charAt(i) == ')') {
                Parentheses parentheses = stack.pop();
                parentheses.close = i;
                list.add(parentheses);
            }
            // 일반 숫자 또는 수식은 표시만 저장
            else
                check[i] = true;
        }
    }

    private static void dfs(int depth, String expression, boolean[] visited) {
        if (depth == list.size()) {
            // 일반 숫자와 수식을 저장하고 있는 check 배열을 복사
            boolean[] copied = Arrays.copyOf(check, check.length);

            // 고른 괄호쌍들의 위치를 true 로 표시
            for (int i = 0; i < list.size(); i++)
                if (visited[i]) {
                    copied[list.get(i).open] = true;
                    copied[list.get(i).close] = true;
                }

            // 표시된 배열을 바탕으로 문자열을 만들고 set에 삽입
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < expression.length(); i++)
                if (copied[i])
                    sb.append(expression.charAt(i));
            result.add(sb.toString());
            return;
        }

        dfs(depth + 1, expression, visited);
        visited[depth] = true;
        dfs(depth + 1, expression, visited);
        visited[depth] = false;
    }
}