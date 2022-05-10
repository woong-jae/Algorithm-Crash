import java.util.ArrayList;
import java.util.Stack;

class Row {
    int previous;
    int after;

    public Row(int previous, int after) {
        this.previous = previous;
        this.after = after;
    }
}

class Solution {
    public String solution(int n, int k, String[] cmd) {
        ArrayList<Row> list = new ArrayList<>();
        Stack<Integer> deletedNum = new Stack<>();
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < n - 1; i++)
            list.add(new Row(i - 1, i + 1));
        list.add(new Row(n - 2, -1));

        for (String s : cmd) {
            String[] tokens = s.split(" ");

            switch (tokens[0]) {
                case "D":
                case "U":
                    k = move(k, Integer.parseInt(tokens[1]), tokens[0], list);
                    break;
                case "C":
                    deletedNum.add(k);

                    if(list.get(k).after == -1){
                        list.get(list.get(k).previous).after = -1;
                        k = list.get(k).previous;
                        break;
                    }

                    if(list.get(k).previous != -1)
                        list.get(list.get(k).previous).after = list.get(k).after;
                    list.get(list.get(k).after).previous = list.get(k).previous;

                    k = list.get(k).after;
                    break;
                case "Z":
                    int top = deletedNum.pop();
                    if(list.get(top).after != -1)
                        list.get(list.get(top).after).previous = top;
                    if(list.get(top).previous != -1)
                        list.get(list.get(top).previous).after = top;
            }
        }

        boolean[] status = new boolean[n];

        for(int num : deletedNum)
            status[num] = true;

        for (int i = 0; i < n; i++) {
            if (!status[i])
                answer.append("O");
            else
                answer.append("X");
        }

        return answer.toString();
    }

    private int move(int k, int num, String command, ArrayList<Row> list) {
        int index = k;

        if (command.equals("U"))
            for (int i = 0; i < num; i++)
                index = list.get(index).previous;

        else
            for (int i = 0; i < num; i++)
                index = list.get(index).after;

        return index;
    }
}